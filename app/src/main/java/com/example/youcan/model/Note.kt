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
    val name: String = "",

    @ColumnInfo
    val calories: Double = 0.0,
    @ColumnInfo
    val proteins: Double = 0.0,
    @ColumnInfo
    val fats: Double = 0.0,
    @ColumnInfo
    val carbs: Double = 0.0,

    @ColumnInfo
    val subtitle: String = "",

    val firebaseId: String = ""
)
