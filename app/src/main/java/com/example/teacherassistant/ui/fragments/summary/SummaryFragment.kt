package edu.quacke.teacherassistant.ui.fragments.summary

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.teacherassistant.R
import org.koin.androidx.viewmodel.ext.android.viewModel
/**
 * A fragment representing a list of Items.
 */
class SummaryFragment : Fragment() {

    val viewModel: SummaryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.summary_fragment, container, false)

        val gradeList: RecyclerView = view.findViewById(R.id.summary_rv)

        gradeList.layoutManager = LinearLayoutManager(context)
        gradeList.adapter = SummaryAdapter(viewLifecycleOwner, viewModel.gradeEvents, viewModel)

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = SummaryFragment()
    }
}