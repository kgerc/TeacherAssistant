package com.example.teacherassistant.ui.fragments.subjects

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.teacherassistant.DialogManager
import com.example.teacherassistant.data.TeacherAssistantDatabase
import com.example.teacherassistant.data.TeacherAssistantRepository
import com.example.teacherassistant.data.entities.Subject
import com.example.teacherassistant.helpers.asSelectable
import com.example.teacherassistant.helpers.deselectAll
import com.example.teacherassistant.helpers.selected
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class SubjectsViewModel(
    database: TeacherAssistantDatabase,
    private val repository: TeacherAssistantRepository
) : ViewModel(), ISubjectCallback, KoinComponent {
    private val dialogManager: DialogManager by inject()
    var name: String? = null
    var dayOfWeek: String? = null
    var hoursBlock: String? = null
    val selectedDayOfWeek: MutableLiveData<String?> = MutableLiveData(null)

    fun insert() {
        repository.addOrEditSubject(
            Subject(name!!,selectedDayOfWeek.value!!,hoursBlock!!).also { it.id = repository.editedSubject.value?.id ?: 0L },
            *students.selected.toTypedArray()
        )
    }

    fun clear() {
        repository.clearEditedSubject()
        students.deselectAll()
    }

    init {
        database.students.all.observeForever {
            students = it.asSelectable()
        }
        repository.editedSubject.observeForever {
            name = it?.name
            it?.let {
                GlobalScope.launch {
                    val subjectStudents = repository.getSubjectStudents(it).map { student ->  student.id }
                    for (student in students) {
                        if (subjectStudents.contains(student.value.id)) student.select()
                    }
                }
            }
        }
    }

    var students = database.students.all.value?.asSelectable().orEmpty()
    val subjects = database.subjects.all

    fun showSelectStudents() = dialogManager.showSelectSubjectStudentsDialog()

    override fun deleteSubject(subject: Subject) {
        repository.removeSubject(subject)
    }

    override fun editSubject(subject: Subject) {
        dialogManager.showEditSubjectDialog(subject)
    }

    override fun showGradesForSubject(subject: Subject) {
        dialogManager.showGradesForSubject(subject)
    }
}