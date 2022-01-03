package com.example.teacherassistant.ui.fragments.students

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.R
import kotlinx.coroutines.DelicateCoroutinesApi
import org.koin.android.viewmodel.ext.android.sharedViewModel

@DelicateCoroutinesApi
class StudentsFragment : Fragment() {
    private val vm: StudentsViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.students_fragment, container, false)

        val studentList: RecyclerView = view.findViewById(R.id.students_rv)
        studentList.layoutManager = LinearLayoutManager(context)
        studentList.adapter = StudentAdapter(viewLifecycleOwner, vm.students, vm)

        return view
    }

}