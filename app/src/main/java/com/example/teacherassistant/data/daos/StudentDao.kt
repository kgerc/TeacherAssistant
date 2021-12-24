package com.example.teacherassistant.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teacherassistant.data.models.Student

interface StudentDao {
    val all: LiveData<List<Student>>
        @Query("SELECT * FROM students") get

    @Query("SELECT * FROM students WHERE id IN (:IDs)")
    fun getRangeById(IDs: LongArray): List<Student>

    @Query("SELECT * FROM students WHERE id = :ID")
    fun getById(ID: Long): Student

    @Insert
    fun insert(student: Student): Long

    @Insert
    fun insertRange(vararg student: Student)

    @Delete
    fun delete(student: Student)

    @Update
    fun update(student: Student)
}