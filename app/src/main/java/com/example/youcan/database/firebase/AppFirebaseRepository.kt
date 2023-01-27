package com.example.youcan.database.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.youcan.database.DatabaseRepository
import com.example.youcan.model.Note
import com.example.youcan.utils.Constants
import com.example.youcan.utils.EMAIL
import com.example.youcan.utils.FIREBASE_ID
import com.example.youcan.utils.PASSWORD
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AppFirebaseRepository : DatabaseRepository {

    private val mAuth = FirebaseAuth.getInstance()
    private val database = Firebase.database.reference
        .child(mAuth.currentUser?.uid.toString())

    override val readAll: LiveData<List<Note>> = AllNotesLiveData()

    override suspend fun create(note: Note, onSuccess: () -> Unit) {
        val noteId = database.push().key.toString()
        val mapNotes = hashMapOf<String, Any>()

        mapNotes[FIREBASE_ID] = noteId
        mapNotes[Constants.Keys.TITLE] = note.title
        mapNotes[Constants.Keys.NAME] = note.name
        mapNotes[Constants.Keys.CALORIES] = note.calories
        mapNotes[Constants.Keys.PROTEINS] = note.proteins
        mapNotes[Constants.Keys.FATS] = note.fats
        mapNotes[Constants.Keys.CARBS] = note.carbs
        mapNotes[Constants.Keys.COMMENT] = note.comment

        database.child(noteId)
            .updateChildren(mapNotes)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { Log.d("checkData", "Failed to add new note") }
    }

    override suspend fun update(note: Note, onSuccess: () -> Unit) {
        val noteId = note.firebaseId
        val mapNotes = hashMapOf<String, Any>()

        mapNotes[FIREBASE_ID] = noteId
        mapNotes[Constants.Keys.TITLE] = note.title
        mapNotes[Constants.Keys.NAME] = note.name
        mapNotes[Constants.Keys.CALORIES] = note.calories
        mapNotes[Constants.Keys.PROTEINS] = note.proteins
        mapNotes[Constants.Keys.FATS] = note.fats
        mapNotes[Constants.Keys.CARBS] = note.carbs
        mapNotes[Constants.Keys.COMMENT] = note.comment

        database.child(noteId)
            .updateChildren(mapNotes)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { Log.d("checkData", "Failed to update note") }
    }

    override suspend fun delete(note: Note, onSuccess: () -> Unit) {
        database.child(note.firebaseId).removeValue()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { Log.d("checkData", "Failed to delete note") }
    }

    override fun signOut() {
        mAuth.signOut()
    }

    override fun connectToDatabase(onSuccess: () -> Unit, onFail: (String) -> Unit) {
        mAuth.signInWithEmailAndPassword(EMAIL, PASSWORD)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener {
                mAuth.createUserWithEmailAndPassword(EMAIL, PASSWORD)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { onFail(it.message.toString()) }
            }
    }
}