package com.example.teacherassistant.ui.fragments.students.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.R
import com.example.teacherassistant.data.entities.Subject
import com.example.teacherassistant.helpers.SelectableList
import com.example.teacherassistant.ui.fragments.students.StudentsViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class SelectStudentSubjectsDialog : DialogFragment() {

    val vm: StudentsViewModel by sharedViewModel()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction

            val view = LayoutInflater.from(context).inflate(R.layout.select_student_subjects_dialog, null)

            val recyclerView: RecyclerView = view.findViewById(R.id.student_subjects_rv)

            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = AddStudentSubjectsAdapter(vm.subjects as SelectableList<Subject>)

            val builder = AlertDialog.Builder(it)
            builder
                .setView(view)
                .setTitle(R.string.choose_subjects_for_student)
                .setPositiveButton(R.string.accept
                ) { _, _ ->
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SelectStudentSubjectsDialog()
    }
}