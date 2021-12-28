package com.example.teacherassistant.ui.fragments.grades.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer


class AddGradeDialog : DialogFragment() {

    class SelectedListener<T>(private val selectionDataReference: MutableLiveData<T?>): AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            selectionDataReference.value = parent?.getItemAtPosition(position) as T?
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            selectionDataReference.value = null
        }
    }

    val vm: GradesVM by sharedViewModel()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val v = edu.quacke.teacherassistant.databinding.DialogAddEditGradeBinding.inflate(
            LayoutInflater.from(context))
        v.viewModel = vm;

        val subjectSpnr = v.root.findViewById<Spinner>(R.id.subject_spnr)

        vm.subjects.observe(this, Observer {
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, it.orEmpty())
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            subjectSpnr.adapter = adapter
        })

        subjectSpnr.onItemSelectedListener = SelectedListener(vm.selectedSubject)

        val studentSpnr: Spinner = v.root.findViewById(R.id.student_spnr)

        vm.studentsForSubject.observe(this, Observer {
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, it.orEmpty())
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            studentSpnr.adapter = adapter
        })

        studentSpnr.onItemSelectedListener = SelectedListener(vm.selectedStudent)

        val gradeSpnr = v.root.findViewById<NumberPicker>(R.id.grade_spnr).apply {
            minValue = 0
            maxValue = 6
            displayedValues = arrayOf("2", "2.5", "3", "3.5", "4", "4.5", "5")
            value = vm.selectedValue.value ?: 0
            setOnValueChangedListener { _, _, newVal -> vm.selectedValue.value = newVal }
        }

        vm.selectedValue.observe(this, Observer {
            gradeSpnr.value = it
        })

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder
                .setView(v.root)
                .setTitle(R.string.add_grade_title)
                .setPositiveButton(R.string.ok
                ) { _, _ ->
                    vm.insert()
                }
                .setNegativeButton(R.string.cancel
                ) { _, _ ->
                    vm.clear()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        vm.clear()
    }

    companion object {
        @JvmStatic
        fun newInstance() = AddGradeDialog()
    }
}
