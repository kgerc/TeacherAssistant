package com.example.teacherassistant.ui.fragments.grades

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.R
import com.example.teacherassistant.data.entities.Grade
import com.example.teacherassistant.helpers.LiveDataAdapter


class StatsAdapter(owner: LifecycleOwner, data: LiveData<List<Grade>>): LiveDataAdapter<StatsAdapter.StatsHolder>(owner, data) {

    class StatsHolder(view: View): RecyclerView.ViewHolder(view) {
        var gradeTwoPercentage: TextView = view.findViewById(R.id.two_value)
        var gradeTwoHalfPercentage: TextView = view.findViewById(R.id.two_half_value)
        var gradeThrePercentage: TextView = view.findViewById(R.id.three_value)
        var gradeThreeHalfPercentage: TextView = view.findViewById(R.id.three_half_value)
        var gradeFourPercentage: TextView = view.findViewById(R.id.four_value)
        var gradeFourHalfPercentage: TextView = view.findViewById(R.id.four_half_value)
        var gradeFiveHalfPercentage: TextView = view.findViewById(R.id.five_value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.stats_row, parent,false)
        return StatsHolder(v)
    }

    override fun getItemCount(): Int = data.value?.size ?: 0

    override fun onBindViewHolder(holder: StatsHolder, position: Int) {
        var twoCount = 0;
        var twoAndAHalfCount = 0;
        var threeCount = 0;
        var threeAndAHalfCount = 0;
        var fourCount = 0;
        var fourAndAHalfCount = 0;
        var fiveCount = 0;
        if(data.value?.size!! > 0){
            for (item in data.value!! as List<Grade>) {
                when (item.value) {
                    2f -> twoCount++
                    2.5f -> twoAndAHalfCount++
                    3f -> threeCount++
                    3.5f -> threeAndAHalfCount++
                    4f -> fourCount++
                    4.5f -> fourAndAHalfCount++
                    5f -> fiveCount++
                }
            }
            val twoPercentage = twoCount  * 100 / itemCount
            val twoHalfPercentage = twoAndAHalfCount  * 100 / itemCount
            val threePercentage = threeCount  * 100 / itemCount
            val threeHalfPercentage = threeAndAHalfCount  * 100 / itemCount
            val fourPercentage = fourCount  * 100 / itemCount
            val fourHalfPercentage = fourAndAHalfCount  * 100 / itemCount
            val fivePercentage = fiveCount  * 100 / itemCount
            holder.gradeTwoPercentage.text = "$twoPercentage %"
            holder.gradeTwoHalfPercentage.text = "$twoHalfPercentage %"
            holder.gradeThrePercentage.text = "$threePercentage %"
            holder.gradeThreeHalfPercentage.text = "$threeHalfPercentage %"
            holder.gradeFourPercentage.text = "$fourPercentage %"
            holder.gradeFourHalfPercentage.text = "$fourHalfPercentage %"
            holder.gradeFiveHalfPercentage.text = "$fivePercentage %"
        }


    }
}