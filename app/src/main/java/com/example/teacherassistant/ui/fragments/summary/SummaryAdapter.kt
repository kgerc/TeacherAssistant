package edu.quacke.teacherassistant.ui.fragments.summary

import android.os.Build
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.example.teacherassistant.R
import com.example.teacherassistant.data.entities.Grade
import com.example.teacherassistant.helpers.LiveDataAdapter
import com.example.teacherassistant.ui.fragments.grades.IGradeCallback
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import java.time.format.DateTimeFormatter

class SummaryAdapter(
    owner: LifecycleOwner,
    data: LiveData<List<Grade>>,
    private val callback: IGradeCallback
) : LiveDataAdapter<SummaryAdapter.SummaryHolder>(owner, data), KoinComponent {

    inner class SummaryHolder(view: View) : RecyclerView.ViewHolder(view) {
        val eventTypeTV: TextView = view.findViewById(R.id.row_summary_type_tv)
        val gradeTV: TextView = view.findViewById(R.id.row_summary_value_tv)
        val studentTV: TextView = view.findViewById(R.id.row_summary_student_tv)
        val subjectTV: TextView = view.findViewById(R.id.row_summary_subject_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummaryHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.summary_row, parent, false)
        return SummaryHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: SummaryHolder, position: Int) {
        val grade = data.value?.get(position) as Grade

        val modificationTime = grade.lastModifitation.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        holder.gradeTV.text = grade.value.toString()

        holder.eventTypeTV.text = when (grade.status) {
            Grade.Status.ADDED -> ("Added $modificationTime")
            Grade.Status.EDITED -> ("Edited $modificationTime")
            Grade.Status.DEPRECATED -> ("Deprecated $modificationTime")
            Grade.Status.DELETED -> ("Deleted $modificationTime")
        }

        GlobalScope.launch {
            val relation = callback.getStudentAndSubject(grade)
            val student = relation.second
            val subject = relation.first

            MainScope().launch {
                holder.studentTV.text = student.toString()
                holder.subjectTV.text = subject.name
            }
        }
    }

}