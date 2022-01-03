package com.example.teacherassistant.ui.fragments.subjects.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.teacherassistant.R
import com.example.teacherassistant.databinding.AddEditSubjectDialogBinding
import com.example.teacherassistant.ui.fragments.subjects.SubjectsViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class AddSubjectDialog : DialogFragment() {
    class SelectedListener<T>(private val selectionDataReference: MutableLiveData<T?>): AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            selectionDataReference.value = parent?.getItemAtPosition(position) as T?
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            selectionDataReference.value = null
        }
    }

    val viewModel: SubjectsViewModel by sharedViewModel()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val binding: AddEditSubjectDialogBinding = AddEditSubjectDialogBinding.inflate(LayoutInflater.from(context))
        binding.viewModel = viewModel

        val dayOfWeekSpnr: Spinner = binding.root.findViewById(R.id.subject_day_of_week_spnr)

        viewModel.subjects.observe(this, Observer {
            val adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.days_of_week,
                android.R.layout.simple_spinner_item)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            dayOfWeekSpnr.adapter = adapter
        })

        dayOfWeekSpnr.onItemSelectedListener = AddSubjectDialog.SelectedListener(viewModel.selectedDayOfWeek)

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