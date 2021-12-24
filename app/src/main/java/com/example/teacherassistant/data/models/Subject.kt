package com.example.teacherassistant.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subjects")
class Subject(
    val name: String,
    val dayOfWeek: DayOfWeek,
    val hoursBlock: String
) {
    enum class DayOfWeek(val value: Int) {
        Monday(1),
        Tuesday(2),
        Wednesday(3),
        Thursday(4),
        Friday(5),
        Saturday(6),
        Sunday(7)
    }
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    override fun toString(): String = name
}