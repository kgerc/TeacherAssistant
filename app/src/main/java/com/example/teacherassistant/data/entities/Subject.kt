package com.example.teacherassistant.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subjects")
class Subject(
    val name: String,
    val dayOfWeek: String,
    val hoursBlock: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    override fun toString(): String = "$name $dayOfWeek $hoursBlock"
}