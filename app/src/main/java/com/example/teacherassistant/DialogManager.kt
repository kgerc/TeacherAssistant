package com.example.teacherassistant

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import com.example.teacherassistant.data.TeacherAssistantRepository
import com.example.teacherassistant.data.entities.Student
import com.example.teacherassistant.data.entities.Subject
import kotlinx.coroutines.DelicateCoroutinesApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@DelicateCoroutinesApi
class DialogManager(private val fragmentManager: FragmentManager, private val navController: NavController): KoinComponent {
    private val repo: TeacherAssistantRepository by inject()

    private fun showDialog(dialog: DialogFragment, tag: String) {
        dialog.show(fragmentManager, tag)
    }

    //fun showAddStudentDialog() = showDialog(AddStudentDialog(), "AddStudentDialog")
    //fun showEditStudentDialog(student: Student) {
     //   repo.editedStudent.value = student
     //   showAddStudentDialog()
    //}
    //fun showAddSubjectDialog() = showDialog(AddSubjectDialog(), "AddStudentDialog")
    //fun showEditSubjectDialog(subject: Subject) {
    //    repo.editedSubject.value = subject
    //    showAddSubjectDialog()
    //}
}