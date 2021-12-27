package com.example.teacherassistant.ui.fragments.students

import com.example.teacherassistant.data.entities.Student

interface IStudentCallback {
    fun deleteStudent(student: Student)
    fun showEditStudent(student: Student)
}