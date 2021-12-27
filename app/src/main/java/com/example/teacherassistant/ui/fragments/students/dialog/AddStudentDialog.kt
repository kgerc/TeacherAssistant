package com.example.teacherassistant.ui.fragments.students.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.example.teacherassistant.DialogManager
import com.example.teacherassistant.R
import com.example.teacherassistant.ui.fragments.students.StudentsViewModel
import org.koin.android.compat.SharedViewModelCompat.sharedViewModel
import org.koin.android.ext.android.inject

class AddStudentDialog : DialogFragment() {

    private val vm: StudentsViewModel by sharedViewModel()
    private val dialogManager: DialogManager by inject()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
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