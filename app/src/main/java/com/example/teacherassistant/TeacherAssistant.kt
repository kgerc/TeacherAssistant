package com.example.teacherassistant

import android.app.Application
import com.example.teacherassistant.data.TeacherAssistantDatabase
import com.example.teacherassistant.data.TeacherAssistantRepository
import com.example.teacherassistant.ui.fragments.grades.GradesViewModel
import com.example.teacherassistant.ui.fragments.students.StudentsViewModel
import com.example.teacherassistant.ui.fragments.subjects.SubjectsViewModel
import edu.quacke.teacherassistant.ui.fragments.summary.SummaryViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel

@DelicateCoroutinesApi
class TeacherAssistant: Application() {
    override fun onCreate() {
        super.onCreate()

        addModules{
            single { buildDatabase<TeacherAssistantDatabase>("teacher-assistant")}
            single { TeacherAssistantRepository(get()) }
            viewModel { GradesViewModel(get(), get(), get()) }
            viewModel { SubjectsViewModel(get(), get(), get()) }
            viewModel { StudentsViewModel(get(), get(), get()) }
            viewModel { SummaryViewModel(get(), get()) }
        }
    }
}
