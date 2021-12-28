package com.example.teacherassistant.ui.fragments.students.dialog

import android.graphics.Color
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.teacherassistant.R
import com.example.teacherassistant.data.entities.Subject
import com.example.teacherassistant.helpers.SelectableAdapter
import com.example.teacherassistant.helpers.SelectableList
import com.example.teacherassistant.helpers.SimpleViewHolder

class SimpleSubjectHolder(view: View): SimpleViewHolder<Subject>(view) {
    private val subjectNameTextView: TextView = view.findViewById(R.id.subject_row_name_tv)

    init {
        view.findViewById<ImageButton>(R.id.row_subject_edit_btn).apply { isVisible = false }
        view.findViewById<ImageButton>(R.id.row_subject_remove_btn).apply { isVisible = false }
    }

    override fun bind(model: Subject) {
        subjectNameTextView.text = model.name
    }
}

class AddStudentSubjectsAdapter(data: SelectableList<Subject>): SelectableAdapter<SimpleSubjectHolder, Subject>(data,R.layout.subject_row, { SimpleSubjectHolder(it) }) {
    override fun showItemSelected(holder: SimpleSubjectHolder, item: Subject) {
        holder.itemView.setBackgroundColor(Color.CYAN)
    }

    override fun showItemDeselected(holder: SimpleSubjectHolder, item: Subject) {
        holder.itemView.setBackgroundColor(Color.WHITE)
    }
}