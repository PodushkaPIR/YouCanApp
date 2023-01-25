package com.example.youcan.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.youcan.utils.Constants.Keys.NOTES_TABLE

@Entity(tableName = NOTES_TABLE)
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val title: String = "",
    @ColumnInfo
    val proteins: Int = 0,
    @ColumnInfo
    val fats: Int = 0,
    @ColumnInfo
    val carbs: Int = 0,
    @ColumnInfo
    val subtitle: String = "",
    val firebaseId: String = ""
)
