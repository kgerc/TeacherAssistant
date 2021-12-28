package com.example.teacherassistant.ui.fragments.subjects.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.example.teacherassistant.R
import com.example.teacherassistant.databinding.AddEditSubjectDialogBinding
import com.example.teacherassistant.ui.fragments.subjects.SubjectsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AddSubjectDialog : DialogFragment() {

    val viewModel: SubjectsViewModel by sharedViewModel()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val binding: AddEditSubjectDialogBinding = AddEditSubjectDialogBinding.inflate(LayoutInflater.from(context))
        binding.viewModel = viewModel

        val dayOfWeekSpnr = binding.root.findViewById<Spinner>(R.id.subject_day_of_week_spnr)

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder
                .setView(binding.root)
                .setTitle(R.string.add_subject_title)
                .setPositiveButton(R.string.ok
                ) { _, _ ->
                    if (viewModel.name != null) {
                        viewModel.insert()
                        viewModel.clear()
                    }
                }
                .setNegativeButton(R.string.cancel
                ) { _, _ ->
                    viewModel.clear()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun getDialog(): AlertDialog? {
        return super.getDialog() as AlertDialog?
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        viewModel.clear()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            AddSubjectDialog()
    }
}