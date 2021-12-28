package com.example.teacherassistant.ui.fragments.grades

import com.example.teacherassistant.data.entities.Grade
import com.example.teacherassistant.data.entities.Student
import com.example.teacherassistant.data.entities.Subject

interface IGradeCallback {
    fun deleteGrade(grade: Grade)
    fun showEditGrade(grade: Grade)
    fun getStudentAndSubject(grade: Grade): Pair<Subject, Student>
}