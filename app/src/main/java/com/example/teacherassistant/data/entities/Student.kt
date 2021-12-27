package com.example.teacherassistant.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
class Student(
    val firstName: String,
    val surName: String,
    val idCardNumber: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    override fun toString(): String = "$firstName $surName"
}