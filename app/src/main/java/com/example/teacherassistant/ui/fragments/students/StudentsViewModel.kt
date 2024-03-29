package com.example.teacherassistant.ui.fragments.students

import androidx.lifecycle.ViewModel
import com.example.teacherassistant.DialogManager
import com.example.teacherassistant.data.TeacherAssistantDatabase
import com.example.teacherassistant.data.TeacherAssistantRepository
import com.example.teacherassistant.data.entities.Student
import com.example.teacherassistant.helpers.asSelectable
import com.example.teacherassistant.helpers.deselectAll
import com.example.teacherassistant.helpers.selected
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StudentsViewModel constructor(
    database: TeacherAssistantDatabase,
    private val repo: TeacherAssistantRepository,
    private val dialogManager: DialogManager
) : ViewModel(), IStudentCallback {
    val students = database.students.all

    var firstName: String? = null
    var surName: String? = null
    var idCardNumber: String? = null

    var subjects = database.subjects.all.value?.asSelectable().orEmpty()

    init {
        database.subjects.all.observeForever {
            subjects = it.asSelectable()
        }
        repo.editedStudent.observeForever {
            firstName = it?.firstName
            surName = it?.surName
            idCardNumber = it?.idCardNumber
            it?.let {
                GlobalScope.launch {
                    val studentSubjects = repo.getStudentSubjects(it).map { subject ->  subject.id }
                    for (subject in subjects) {
                        if (studentSubjects.contains(subject.value.id)) subject.select()
                    }
                }
            }
        }
    }

    fun insert() {
        repo.addOrEditStudent(
            Student(firstName!!, surName!!, idCardNumber!!).also { it.id = repo.editedStudent.value?.id ?: 0 },
            *subjects.selected.toTypedArray()
        )
    }

    fun clear() {
        repo.clearEditedStudent()
        subjects.deselectAll()
    }
    fun showSelectSubjects() = dialogManager.showSelectStudentSubjectsDialog()

    override fun deleteStudent(student: Student) = repo.removeStudent(student)
    override fun showEditStudent(student: Student) = dialogManager.showEditStudentDialog(student)
    override fun showStudentGrades(student: Student) = dialogManager.showGradesForStudent(student)
}
