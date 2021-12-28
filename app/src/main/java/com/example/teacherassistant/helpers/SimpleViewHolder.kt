package com.example.teacherassistant.helpers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.lang.reflect.Constructor

abstract class SimpleViewHolder<M>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(model: M)
}

private fun <T> newInstance(constructor: Constructor<T>, vararg args: Any) : T {
    return constructor.newInstance(*args)
}

inline fun<reified VH> getConstructor(): Constructor<VH> {
    return VH::class.java.getConstructor(View::class.java)
}

abstract class SimpleAdapterBase<VH, M>(protected val data: List<M>, private val layout: Int, private val viewHolderFactory: (View) -> VH): RecyclerView.Adapter<VH>() where VH: SimpleViewHolder<M> {

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = data[position]
        holder.bind(item)
        onBindingViewHolder(holder, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return viewHolderFactory(v)
    }

    open fun onBindingViewHolder(holder: VH, item: M) {}

    override fun getItemCount(): Int = data.size
}

abstract class SimpleAdapter<VH, M>(data: List<M>, layout: Int, viewHolderFactory: (View) -> VH): SimpleAdapterBase<VH, M>(data,
    layout, viewHolderFactory
) where VH: SimpleViewHolder<M> {
    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return super.onCreateViewHolder(parent, viewType)
    }

    final override fun onBindViewHolder(holder: VH, position: Int) {
        super.onBindViewHolder(holder, position)
    }

    final override fun getItemCount(): Int = super.getItemCount()
}
