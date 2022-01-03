package com.example.teacherassistant.ui.fragments.subjects.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.R
import com.example.teacherassistant.data.entities.Student
import com.example.teacherassistant.helpers.SelectableList
import com.example.teacherassistant.ui.fragments.students.dialog.SelectStudentSubjectsDialog
import com.example.teacherassistant.ui.fragments.subjects.SubjectsViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class SelectSubjectStudentsDialog : DialogFragment() {

    val vm: SubjectsViewModel by sharedViewModel()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction

            val view = LayoutInflater.from(context).inflate(R.layout.select_subject_students_dialog, null)

            val recyclerView: RecyclerView = view.findViewById(R.id.subject_students_rv)

            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = SelectSubjectStudentsAdapter(vm.students)


            val builder = AlertDialog.Builder(it)
            builder
                .setView(view)
                .setTitle(R.string.choose_students_for_subject)
                .setPositiveButton(
                    R.string.accept
                ) { _, _ -> /* no-op */ }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SelectStudentSubjectsDialog()
    }
}