package edu.quacke.teacherassistant.ui.fragments.summary

import androidx.lifecycle.ViewModel
import com.example.teacherassistant.data.TeacherAssistantDatabase
import com.example.teacherassistant.data.TeacherAssistantRepository
import com.example.teacherassistant.data.entities.Grade
import com.example.teacherassistant.data.entities.Student
import com.example.teacherassistant.data.entities.Subject
import com.example.teacherassistant.ui.fragments.grades.IGradeCallback

class SummaryViewModel(database: TeacherAssistantDatabase, private val repo: TeacherAssistantRepository): ViewModel(),
    IGradeCallback {
    val gradeEvents = database.grades.all
    override fun deleteGrade(grade: Grade) {
        TODO("Not yet implemented")
    }

    override fun showEditGrade(grade: Grade) {
        TODO("Not yet implemented")
    }

    override fun getStudentAndSubject(grade: Grade): Pair<Subject, Student> = repo.getGradeSubjectAndStudent(grade)
}