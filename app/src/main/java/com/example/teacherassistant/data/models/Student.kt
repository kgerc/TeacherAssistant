package com.example.teacherassistant.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
class Student(
    val name: String,
    val surname: String,
    val idCardNumber: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    override fun toString(): String = "$name $surname"
}