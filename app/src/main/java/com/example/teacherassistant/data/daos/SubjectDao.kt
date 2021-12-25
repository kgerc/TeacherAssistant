package com.example.teacherassistant.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teacherassistant.data.entities.Subject

@Dao
interface SubjectDao {
    val all: LiveData<List<Subject>>
        @Query("SELECT * FROM subjects") get

    @Query("SELECT * FROM subjects WHERE id IN (:IDs)")
    fun getRangeByID(IDs: LongArray): List<Subject>

    @Query("SELECT * FROM subjects WHERE id = :ID")
    fun getById(ID: Long): Subject

    @Query("SELECT * FROM subjects WHERE name LIKE :name LIMIT 1")
    fun getByName(name: String): Subject

    @Insert
    fun insertRange(vararg subject: Subject)

    @Insert
    fun insert (subject: Subject): Long

    @Update
    fun update(subject: Subject)

    @Delete
    fun delete(subject: Subject)
}