package com.example.teacherassistant.ui.fragments.grades

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class GradesFragment : Fragment() {

    private val viewModel: GradesViewModel by viewModel()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_grades, container, false)

        val gradeList: RecyclerView = view.findViewById(R.id.grades_rv)
        gradeList.layoutManager = LinearLayoutManager(context)
        gradeList.adapter = GradeAdapter(viewLifecycleOwner, viewModel.grades, viewModel)

        return view
    }
}