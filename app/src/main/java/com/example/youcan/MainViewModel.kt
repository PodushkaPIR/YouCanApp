package com.example.youcan

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.youcan.database.room.AppRoomDatabase
import com.example.youcan.database.room.repository.RoomRepository
import com.example.youcan.model.Note
import com.example.youcan.utils.REPOSITORY
import com.example.youcan.utils.TYPE_FIREBASE
import com.example.youcan.utils.TYPE_ROOM

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
        }
    }
}


class MainViewModelFactory(private val application: Application): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(application = application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}