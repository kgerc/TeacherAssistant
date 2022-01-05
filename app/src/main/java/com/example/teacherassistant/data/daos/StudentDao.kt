package com.example.teacherassistant.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teacherassistant.data.entities.Student

@Dao
interface StudentDao {
    val all: LiveData<List<Student>>
        @Query("SELECT * FROM students") get

    @Query("SELECT * FROM students WHERE id IN (:IDs)")
    fun getRangeById(IDs: LongArray): List<Student>

    @Query("SELECT * FROM students WHERE id = :ID")
    fun getById(ID: Long): Student

    @Query("SELECT s.firstName, s.surName, s.idCardNumber, s.id FROM students s JOIN student_to_subject_rel stos ON s.id = stos.student_id WHERE stos.subject_id = :subjectID" )
    fun getBySubject(subjectID: Long): List<Student>

    @Insert
    fun insert(student: Student): Long

    @Insert
    fun insertRange(vararg student: Student)

    @Delete
    fun delete(student: Student)

    @Query("DELETE FROM students")
    fun deleteAll()

    @Update
    fun update(student: Student)
}