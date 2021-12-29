package com.example.teacherassistant.ui.fragments.grades

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.R
import com.example.teacherassistant.data.entities.Grade
import com.example.teacherassistant.helpers.LiveDataAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class GradeAdapter(owner: LifecycleOwner, data: LiveData<List<Grade>>, private val callback: IGradeCallback): LiveDataAdapter<GradeAdapter.GradeHolder>(owner, data) {

    class GradeHolder(view: View): RecyclerView.ViewHolder(view) {
        val gradeTv: TextView = view.findViewById(R.id.row_grade_value_tv)
        val studentTv: TextView = view.findViewById(R.id.row_grade_student_tv)
        val subjectTv: TextView = view.findViewById(R.id.row_grade_subject_tv)
        val noteTv: TextView = view.findViewById(R.id.row_grade_note_tv)

        val removeGradeBtn: ImageButton = view.findViewById(R.id.row_grade_remove_btn)
        val editGradeBtn: ImageButton = view.findViewById(R.id.row_grade_edit_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradeHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.grade_row, parent,false)
        return GradeHolder(v)
    }

    override fun getItemCount(): Int = data.value?.size ?: 0

    override fun onBindViewHolder(holder: GradeHolder, position: Int) {
        val grade = data.value?.get(position) as Grade

        holder.gradeTv.text = grade.value.toString()
        holder.noteTv.apply {
            text = grade.note
            isVisible = !text.isNullOrBlank()
        }

        GlobalScope.launch {
            val relation = callback.getStudentAndSubject(grade)
            val subject = relation.first
            val student = relation.second

            MainScope().launch {
                holder.studentTv.text = student.toString()
                holder.subjectTv.text = subject.name
            }
        }

        holder.removeGradeBtn.setOnClickListener {
            callback.deleteGrade(grade)
        }

        holder.editGradeBtn.setOnClickListener {
            callback.showEditGrade(grade)
        }
    }
}