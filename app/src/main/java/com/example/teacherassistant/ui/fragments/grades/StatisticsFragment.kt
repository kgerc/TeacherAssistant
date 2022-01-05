package com.example.teacherassistant.ui.fragments.grades

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.R
import org.koin.android.viewmodel.ext.android.viewModel

class StatisticsFragment : Fragment() {

    private val viewModel: StatsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.stats_fragment, container, false)

        val gradeList: RecyclerView = view.findViewById(R.id.stats_rv)
        gradeList.layoutManager = LinearLayoutManager(context)
        gradeList.adapter = StatsAdapter(viewLifecycleOwner, viewModel.grades)

        return view
    }
}