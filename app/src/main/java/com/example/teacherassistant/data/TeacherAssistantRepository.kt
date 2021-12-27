package com.example.teacherassistant.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.teacherassistant.data.entities.Student
import com.example.teacherassistant.data.entities.StudentSubjectRelation
import com.example.teacherassistant.data.entities.Subject
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@DelicateCoroutinesApi
class TeacherAssistantRepository(private val database: TeacherAssistantDatabase) {
    //ADD & UPDATE
    fun addStudentSubjectRelation(student: Student, subject: Subject) {
        GlobalScope.launch {
            database.studentSubjectRels.insert(
                StudentSubjectRelation(student.id, subject.id)
            )
        }
    }

    fun addSubjectWithStudents(subject: Subject, vararg students: Student) {
        GlobalScope.launch {
            subject.id = database.subjects.insert(subject)
            val relations = students.map { StudentSubjectRelation(it.id, subject.id) }.toTypedArray()
            database.studentSubjectRels.insert(*relations)
        }
    }

    fun addStudentWithSubjects(student: Student, vararg subjects: Subject) {
        GlobalScope.launch {
            student.id = database.students.insert(student)
            val relations = subjects.map { StudentSubjectRelation(student.id, it.id) }.toTypedArray()
            database.studentSubjectRels.insert(*relations)
        }
    }

    fun addOrEditStudent(student: Student, vararg subjects: Subject) {
        GlobalScope.launch {
            if (student.id == 0L) {
                addStudentWithSubjects(student, *subjects)
            } else {
                database.students.update(student)

                val relations = database.studentSubjectRels.getByStudent(student.id)
                val newRelations = subjects.filter { subj -> !relations.any { rel -> rel.subjectId == subj.id } }.map { StudentSubjectRelation(student.id, it.id) }
                val removedRelations = relations.filter { rel -> !subjects.any { subj -> rel.subjectId == subj.id } }

                database.studentSubjectRels.insert(*newRelations.toTypedArray())

                removedRelations.forEach { database.studentSubjectRels.delete(it) }
            }
        }
    }

    fun addOrEditSubject(subject: Subject, vararg students: Student) {
        GlobalScope.launch {
            if (subject.id == 0L) {
                addSubjectWithStudents(subject, *students)
            } else {
                database.subjects.update(subject)

                val relations = database.studentSubjectRels.getBySubject(subject.id)
                val newRelations = students.filter { std -> !relations.any { rel -> rel.studentId == std.id } }.map { StudentSubjectRelation(it.id, subject.id) }
                val removedRelations = relations.filter { rel -> !students.any { subj -> rel.subjectId == subj.id } }


                for (relation in newRelations) {
                    Log.i("Repo","${database.subjects.getById(relation.subjectId).name} -> ${database.students.getById(relation.studentId)}")
                }

                database.studentSubjectRels.insert(*newRelations.toTypedArray())
                removedRelations.forEach { database.studentSubjectRels.delete(it) }
            }
        }
    }

    //GET
    fun getStudentSubjects(student: Student): Set<Subject> {
        GlobalScope.run {
            val relations = database.studentSubjectRels.getByStudent(student.id)
            return database.subjects.getRangeByID( relations.map { it.subjectId }.toLongArray() ).toHashSet()
        }
    }

    fun getSubjectStudents(subject: Subject): Set<Student> {
        GlobalScope.run {
            val relations = database.studentSubjectRels.getBySubject(subject.id)
            return database.students.getRangeById( relations.map { it.studentId }.toLongArray() ).toHashSet()
        }
    }

    //DELETE
    fun removeSubject(subject: Subject) {
        GlobalScope.launch {
            database.subjects.delete(subject)
        }
    }

    fun removeStudent(student: Student) {
        GlobalScope.launch {
            database.students.delete(student)
        }
    }

    // live data objects
    val editedStudent = MutableLiveData<Student>()
    val editedSubject = MutableLiveData<Subject>()
}