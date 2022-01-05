package com.example.teacherassistant.ui.fragments.grades

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.teacherassistant.DialogManager
import com.example.teacherassistant.data.TeacherAssistantDatabase
import com.example.teacherassistant.data.TeacherAssistantRepository
import com.example.teacherassistant.data.entities.Grade

class StatsViewModel(database: TeacherAssistantDatabase, private val repo: TeacherAssistantRepository, private val dialogManager: DialogManager) : ViewModel() {
    val allGrades = database.grades.current
    val grades = MutableLiveData<List<Grade>>()
    var note: String = ""

    init {
        allGrades.observeForever { newGrades ->
            grades.value = repo.gradeFilter.value?.let { value -> newGrades.filter(value) } ?: allGrades.value
        }
    }
}