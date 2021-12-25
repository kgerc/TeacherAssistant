package com.example.teacherassistant.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teacherassistant.data.models.StudentSubjectRelation

@Dao
interface StudentSubjectRelationDao {
    val all: LiveData<List<StudentSubjectRelation>>
        @Query("SELECT * FROM student_to_subject_rel") get

    @Query("SELECT * FROM student_to_subject_rel WHERE id IN (:IDs)")
    fun getRangeByID(IDs: Long): List<StudentSubjectRelation>

    @Query("SELECT * FROM student_to_subject_rel WHERE id = :ID")
    fun getById(ID: Long): StudentSubjectRelation

    @Query("SELECT * FROM student_to_subject_rel where student_id = :studentId")
    fun getByStudent(studentId: Long): List<StudentSubjectRelation>

    @Query("SELECT * FROM student_to_subject_rel where subject_id = :subjectId")
    fun getBySubject(subjectId: Long): List<StudentSubjectRelation>

    @Query("select * from student_to_subject_rel where student_id = :studentId and subject_id = :subjectId limit 1")
    fun findForStudentAndSubject(studentId: Long, subjectId: Long): StudentSubjectRelation

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg student_to_subject: StudentSubjectRelation)

    @Delete
    fun delete(student_to_subject: StudentSubjectRelation)

    @Update
    fun update(student_to_subject: StudentSubjectRelation)
}