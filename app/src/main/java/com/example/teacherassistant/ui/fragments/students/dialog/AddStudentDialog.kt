package com.example.teacherassistant.ui.fragments.students.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.example.teacherassistant.DialogManager
import com.example.teacherassistant.R
import com.example.teacherassistant.databinding.AddEditStudentDialogBinding
import com.example.teacherassistant.ui.fragments.students.StudentsViewModel
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel

class AddStudentDialog : DialogFragment() {

    private val vm: StudentsViewModel by sharedViewModel()
    private val dialogManager: DialogManager by inject()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction

            val binding = AddEditStudentDialogBinding.inflate(LayoutInflater.from(context))
            binding.viewModel = vm

            val builder = AlertDialog.Builder(it)
            builder
                .setView(binding.root)
                .setTitle(R.string.add_student_title)
                .setPositiveButton(R.string.ok
                ) { _, _ ->
                    vm.insert()
                    vm.clear()
                }
                .setNegativeButton(R.string.cancel
                ) { _, _ ->
                    vm.clear()
                }
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        vm.clear()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            AddStudentDialog()
    }
}