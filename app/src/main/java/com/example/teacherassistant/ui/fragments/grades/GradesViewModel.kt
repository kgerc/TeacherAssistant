package com.example.teacherassistant.ui.fragments.grades

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.teacherassistant.DialogManager
import com.example.teacherassistant.data.TeacherAssistantDatabase
import com.example.teacherassistant.data.TeacherAssistantRepository
import com.example.teacherassistant.data.entities.Grade
import com.example.teacherassistant.data.entities.Student
import com.example.teacherassistant.data.entities.Subject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GradesViewModel(database: TeacherAssistantDatabase, private val repo: TeacherAssistantRepository, private val dialogManager: DialogManager) : ViewModel(), IGradeCallback {

    val subjects = database.subjects.all
    val students = database.students.all

    val allGrades = database.grades.current

    val grades = MutableLiveData<List<Grade>>()

    val selectedStudent: MutableLiveData<Student?> = MutableLiveData(null)
    val selectedSubject: MutableLiveData<Subject?> = MutableLiveData(null)
    val selectedValue = MutableLiveData(0)
    var note: String = ""

    val studentsForSubject: MutableLiveData<List<Student>> = MutableLiveData(emptyList())

    init {
        allGrades.observeForever { newGrades ->
            grades.value = repo.gradeFilter.value?.let { value -> newGrades.filter(value) } ?: allGrades.value
        }

        selectedSubject.observeForever {
            GlobalScope.launch {
                val newStudentList = it?.id?.let {
                    database.students.getBySubject(it) } ?: emptyList()
                studentsForSubject.postValue(newStudentList)
            }
        }

        repo.editedGrade.observeForever {
            GlobalScope.launch {
                selectedValue.postValue((it?.value?.times(2))?.toInt()?.minus(4) ?: 0)
                it?.let {
                    repo.getGradeSubjectAndStudent(it)
                }?.let {
                    selectedSubject.postValue(it.first)
                    selectedStudent.postValue(it.second)
                }
                note = it?.note.orEmpty()
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun insert() {
        repo.addOrEditGrade(
            selectedSubject.value!!,
            selectedStudent.value!!,
            (selectedValue.value!! + 4).toFloat()/2,
            note
        )
    }

    fun clear() = repo.clearEditedGrade()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun deleteGrade(grade: Grade) {
        repo.removeGrade(grade)
    }
    override fun showEditGrade(grade: Grade) {
        dialogManager.showEditGradeDialog(grade)
    }

    override fun getStudentAndSubject(grade: Grade): Pair<Subject, Student> = repo.getGradeSubjectAndStudent(grade)
}