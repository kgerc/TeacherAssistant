package com.example.teacherassistant

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import com.example.teacherassistant.data.TeacherAssistantRepository
import com.example.teacherassistant.data.entities.Grade
import com.example.teacherassistant.data.entities.Student
import com.example.teacherassistant.data.entities.Subject
import com.example.teacherassistant.ui.fragments.grades.dialog.AddGradeDialog
import com.example.teacherassistant.ui.fragments.students.dialog.AddStudentDialog
import com.example.teacherassistant.ui.fragments.students.dialog.SelectStudentSubjectsDialog
import com.example.teacherassistant.ui.fragments.subjects.dialog.AddSubjectDialog
import com.example.teacherassistant.ui.fragments.subjects.dialog.SelectSubjectStudentsDialog
import kotlinx.coroutines.DelicateCoroutinesApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@DelicateCoroutinesApi
class DialogManager(private val fragmentManager: FragmentManager, private val navController: NavController): KoinComponent {
    private val repo: TeacherAssistantRepository by inject()

    private fun showDialog(dialog: DialogFragment, tag: String) {
        dialog.show(fragmentManager, tag)
    }
    fun showAddStudentDialog() = showDialog(AddStudentDialog(), "AddStudentDialog")
    fun showEditStudentDialog(student: Student) {
        repo.editedStudent.value = student
        showAddStudentDialog()
    }
    fun showAddSubjectDialog() = showDialog(AddSubjectDialog(), "AddStudentDialog")
    fun showEditSubjectDialog(subject: Subject) {
        repo.editedSubject.value = subject
        showAddSubjectDialog()
    }
    fun showAddGradeDialog() = showDialog(AddGradeDialog(), "AddStudentDialog")
    fun showEditGradeDialog(grade: Grade) {
        repo.editedGrade.value = grade
        showAddGradeDialog()
    }

    fun showGradesForStudent(student: Student) {
        repo.filterGradesByStudent(student)
        navController.navigate(R.id.action_nav_students_to_nav_grades)
    }

    fun showGradesForSubject(subject: Subject) {
        repo.filterGradesBySubject(subject)
        navController.navigate(R.id.action_nav_subjects_to_nav_grades)
    }

    fun showSelectStudentSubjectsDialog() = showDialog(SelectStudentSubjectsDialog(), "SelectStudentSubjects")
    fun showSelectSubjectStudentsDialog() = showDialog(SelectSubjectStudentsDialog(), "SelectSubjectStudents")
}