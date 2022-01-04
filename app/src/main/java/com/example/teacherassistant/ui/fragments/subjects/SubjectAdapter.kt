package com.example.teacherassistant.ui.fragments.subjects

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.R
import com.example.teacherassistant.data.entities.Subject
import org.koin.core.component.KoinComponent

class SubjectHolder(view: View): RecyclerView.ViewHolder(view) {
    val subjectNameTextView: TextView = view.findViewById(R.id.subject_row_name_tv)
    val removeSubjectBtn: ImageButton = view.findViewById(R.id.row_subject_remove_btn)
    val editSubjectBtn: ImageButton = view.findViewById(R.id.row_subject_edit_btn)
    val subjectDayOfWeekTv: TextView = view.findViewById(R.id.subject_row_day_of_week_tv)
    val subjectHoursBlockTv: TextView = view.findViewById(R.id.subject_row_hours_block_tv)
}

class SubjectAdapter(private var data: LiveData<List<Subject>>, private val callback: ISubjectCallback): RecyclerView.Adapter<SubjectHolder>(), KoinComponent {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.subject_row, parent,false)
        return SubjectHolder(v)
    }

    override fun getItemCount(): Int = data.value?.size ?: 0

    override fun onBindViewHolder(holder: SubjectHolder, position: Int) {
        val subject = data.value?.get(position) as Subject

        holder.subjectNameTextView.text = subject.name
        holder.subjectDayOfWeekTv.text = subject.dayOfWeek
        holder.subjectHoursBlockTv.text = subject.hoursBlock
        holder.removeSubjectBtn.setOnClickListener {
            callback.deleteSubject(subject)
        }
        holder.editSubjectBtn.setOnClickListener {
            callback.editSubject(subject)
        }
        holder.itemView.setOnClickListener {
            callback.showGradesForSubject(subject)
        }
    }
}