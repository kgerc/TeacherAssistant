package com.example.teacherassistant.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teacherassistant.data.entities.Grade

@Dao
interface GradeDao {
    val all: LiveData<List<Grade>>
        @Query("SELECT * FROM grades ORDER BY last_modification_time DESC") get

    val current: LiveData<List<Grade>>
        @Query("SELECT * FROM grades WHERE status < 2 ORDER BY last_modification_time DESC") get

    @Query("SELECT * FROM grades WHERE id IN (:IDs)")
    fun getByID(IDs: IntArray): List<Grade>

    @Query("SELECT * FROM grades WHERE id = :ID")
    fun getByID(ID: Long): Grade

    @Query("SELECT * FROM grades JOIN student_to_subject_rel stos ON student_subject_id = stos.id WHERE stos.student_id = :studentID")
    fun getByStudent(studentID: Long): List<Grade>

    @Insert
    fun insert(vararg grade: Grade)

    @Delete
    fun delete(grade: Grade)

    @Query("DELETE FROM grades")
    fun deleteAll()

    @Update
    fun update(grade: Grade)
}