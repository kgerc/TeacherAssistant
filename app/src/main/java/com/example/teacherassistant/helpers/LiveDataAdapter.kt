package com.example.teacherassistant.helpers

import android.annotation.SuppressLint
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView

@SuppressLint("NotifyDataSetChanged")
abstract class LiveDataAdapter<VH>(owner: LifecycleOwner, protected val data: LiveData<out List<*>>):
    RecyclerView.Adapter<VH>() where VH: RecyclerView.ViewHolder{
    init {
        data.observe(owner, Observer {
            notifyDataSetChanged()
        })
    }

    override fun getItemCount() = data.value?.size ?: 0
}