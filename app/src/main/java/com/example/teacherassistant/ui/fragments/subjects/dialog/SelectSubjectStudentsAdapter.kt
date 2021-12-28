package com.example.teacherassistant.ui.fragments.subjects.dialog

import android.graphics.Color
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.teacherassistant.R
import com.example.teacherassistant.data.entities.Student
import com.example.teacherassistant.helpers.SelectableAdapter
import com.example.teacherassistant.helpers.SelectableList
import com.example.teacherassistant.helpers.SimpleViewHolder

class SimpleStudentHolder(view: View): SimpleViewHolder<Student>(view) {
    private val studentNameTv: TextView = view.findViewById(R.id.row_student_name_tv)
    private val studentSurnameTv: TextView = view.findViewById(R.id.row_student_surname_tv)
    private val studentIdCardNumberTv: TextView = view.findViewById(R.id.row_student_id_card_number_tv)

    init {
        view.findViewById<ImageButton>(R.id.student_row_edit_btn).apply { isVisible = false }
        view.findViewById<ImageButton>(R.id.student_row_remove_btn).apply { isVisible = false }
    }

    override fun bind(model: Student) {
        studentNameTv.text = model.firstName
        studentSurnameTv.text = model.surName
        studentIdCardNumberTv.text = model.idCardNumber
    }
}

class SelectSubjectStudentsAdapter(data: SelectableList<Student>)
    : SelectableAdapter<SimpleStudentHolder, Student>(
    data,
    R.layout.student_row,
    { SimpleStudentHolder(it) }) {
    override fun showItemSelected(holder: SimpleStudentHolder, item: Student) {
        holder.itemView.setBackgroundColor(Color.CYAN)
    }

    override fun showItemDeselected(holder: SimpleStudentHolder, item: Student) {
        holder.itemView.setBackgroundColor(Color.WHITE)
    }
}