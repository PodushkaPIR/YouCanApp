package com.example.youcan

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.youcan.database.firebase.AppFirebaseRepository
import com.example.youcan.database.room.AppRoomDatabase
import com.example.youcan.database.room.repository.RoomRepository
import com.example.youcan.model.Note
import com.example.youcan.utils.REPOSITORY
import com.example.youcan.utils.TYPE_FIREBASE
import com.example.youcan.utils.TYPE_ROOM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    val context = application

    fun innitDatabase(type: String, onSuccess: () -> Unit){
        Log.d("checkData", "MainViewModel innitDatabase with type $type")
        when (type){
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(context = context).getRoomDao()
                REPOSITORY = RoomRepository(dao)
                onSuccess()
            }
            TYPE_FIREBASE -> {
                REPOSITORY = AppFirebaseRepository()
                REPOSITORY.connectToDatabase(
                    {onSuccess()},
                    {Log.d("checkData", "Error: ${it}")}
                )
            }
        }
    }

    fun addNote(note: Note, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            REPOSITORY.create(note = note) {
                viewModelScope.launch(Dispatchers.Main){
                    onSuccess()
                }
            }
        }
    }

    fun updateNote(note: Note, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            REPOSITORY.update(note = note){
                viewModelScope.launch(Dispatchers.Main){
                    onSuccess()
                }
            }
        }
    }

    fun deleteNote(note: Note, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            REPOSITORY.delete(note = note){
                viewModelScope.launch(Dispatchers.Main){
                    onSuccess()
                }
            }
        }
    }

    fun readAllNotes() = REPOSITORY.readAll
}


class MainViewModelFactory(private val application: Application): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(application = application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}