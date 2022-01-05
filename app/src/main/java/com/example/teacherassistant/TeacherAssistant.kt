package com.example.teacherassistant

import android.app.Application
import com.example.teacherassistant.data.TeacherAssistantDatabase
import com.example.teacherassistant.data.TeacherAssistantRepository
import com.example.teacherassistant.ui.fragments.grades.GradesViewModel
import com.example.teacherassistant.ui.fragments.grades.StatsViewModel
import com.example.teacherassistant.ui.fragments.students.StudentsViewModel
import com.example.teacherassistant.ui.fragments.subjects.SubjectsViewModel
import org.koin.android.viewmodel.dsl.viewModel

class TeacherAssistant: Application() {
    override fun onCreate() {
        super.onCreate()

        addModules{
            single { buildDatabase<TeacherAssistantDatabase>("teacher-assistant")}
            single { TeacherAssistantRepository(get()) }
            viewModel { GradesViewModel(get(), get(), get()) }
            viewModel { SubjectsViewModel(get(), get()) }
            viewModel { StudentsViewModel(get(), get(), get()) }
            viewModel { StatsViewModel(get(), get(), get()) }
        }
    }
}
