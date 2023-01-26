package com.example.youcan.utils

import androidx.compose.runtime.mutableStateOf
import com.example.youcan.database.DatabaseRepository

const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"
const val FIREBASE_ID = "firebaseId"

lateinit var REPOSITORY : DatabaseRepository
lateinit var EMAIL : String
lateinit var PASSWORD : String
var DB_TYPE = mutableStateOf("")

object Constants {
    object Keys {
        const val NOTES_DATABASE = "notes_database"
        const val NOTES_TABLE = "notes_table"
        const val ADD_NEW_NOTE = "Add new note"
        const val NOTE_TITLE = "Note title"
        const val NOTE_FOOD_NAME = "Food name"
        const val NOTE_COMMENT = "Note comment"
        const val ADD_NOTE = "Add note"
        const val TITLE = "title"
        const val NAME = "name"
        const val SUBTITLE = "subtitle"
        const val ROOM_DATABASE = "Room database"
        const val FIREBASE_DATABASE = "Firebase database"
        const val WHAT_WE_WILL_USE = "What we will use"
        const val ID = "Id"
        const val NONE = "none"
//        const val UPDATE = "UPDATE"
//        const val DELETE = "DELETE"
//        const val NAV_BACK = "NAV_BACK"
        const val EDIT_NOTE = "Edit note"
        const val LOG_IN = "Log in"
        const val EMAIL_TEXT = "Email"
        const val PASSWORD_TEXT = "Password"
        const val EMPTY = ""
        const val UPDATE_NOTE = "Update note"
        const val SIGN_IN = "Sign in"
    }
    object Screens {
        const val START_SCREEN = "start_screen"
        const val MAIN_SCREEN = "main_screen"
        const val ADD_SCREEN = "add_screen"
        const val NOTE_SCREEN = "note_screen"
    }
}