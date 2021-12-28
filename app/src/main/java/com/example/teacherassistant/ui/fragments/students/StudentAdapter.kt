package com.example.teacherassistant.ui.fragments.students

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.R
import com.example.teacherassistant.data.entities.Student
import com.example.teacherassistant.helpers.LiveDataAdapter
import org.koin.core.component.KoinComponent

class StudentAdapter(owner: LifecycleOwner, data: LiveData<List<Student>>, private val callback: IStudentCallback): LiveDataAdapter<StudentAdapter.StudentHolder>(owner, data),
    KoinComponent {

    class StudentHolder(view: View): RecyclerView.ViewHolder(view) {
        val studentNameTv: TextView = view.findViewById(R.id.row_student_name_tv)
        val studentSurnameTv: TextView = view.findViewById(R.id.row_student_surname_tv)
        val studentIdCardNumberTv: TextView = view.findViewById(R.id.row_student_id_card_number_tv)
        val removeStudentBtn: ImageButton = view.findViewById(R.id.student_row_remove_btn)
        val editStudentBtn: ImageButton = view.findViewById(R.id.student_row_edit_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.student_row, parent,false)
        return StudentHolder(v)
    }

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        val student = data.value?.get(position) as Student

        holder.studentNameTv.text = student.firstName
        holder.studentSurnameTv.text = student.surName
        holder.studentIdCardNumberTv.text = student.idCardNumber
        holder.removeStudentBtn.setOnClickListener {
            callback.deleteStudent(student)
        }
        holder.editStudentBtn.setOnClickListener {
            callback.showEditStudent(student)
        }
    }

    override fun getItemCount(): Int = data.value?.size ?: 0

}