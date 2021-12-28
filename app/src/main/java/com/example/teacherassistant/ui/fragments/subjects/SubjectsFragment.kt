package com.example.teacherassistant.ui.fragments.subjects

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.R
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class SubjectsFragment : Fragment() {

    private val viewModel: SubjectsViewModel by sharedViewModel()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.subjects_fragment, container, false)

        val subjectsList: RecyclerView = view.findViewById(R.id.subjects_rv)
        subjectsList.layoutManager = LinearLayoutManager(context)
        subjectsList.adapter = SubjectAdapter(viewModel.subjects, viewModel)

        viewModel.subjects.observe(viewLifecycleOwner, Observer {
            subjectsList.adapter?.notifyDataSetChanged()
        })

        return view
    }
}