package com.example.teacherassistant.helpers

import android.view.View

abstract class SelectableAdapter<VH, M>(
    private val items: SelectableList<M>,
    layout: Int,
    viewHolderFactory: (View) -> VH): SimpleAdapterBase<VH,M>(
    items.map { it.value },
    layout,
    viewHolderFactory) where VH : SimpleViewHolder<M> {
    abstract fun showItemSelected(holder: VH, item: M)
    abstract fun showItemDeselected(holder: VH, item: M)

    open fun onItemSelected(holder: VH, item: M) {}
    open fun onItemDeselected(holder: VH, item: M) {}

    open fun canSelect(item: M): Boolean = true
    open fun canDeselect(item: M): Boolean = true

    open fun onCannotSelectItem(holder: VH, item: M) {}
    open fun onCannotDeselectItem(holder: VH, item: M) {}

    open fun showCannotSelectItem(holder: VH, item: M) {}
    open fun showCannotDeselectItem(holder: VH, item: M) {}


    final override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]

        if (item.isSelected) {
            showItemSelected(holder, item.value)
        }

        holder.itemView.setOnClickListener {
            if (!item.isSelected) {
                if (canSelect(item.value)) {
                    showItemSelected(holder, item.value)
                    onItemSelected(holder, item.value)
                    item.isSelected = true
                } else {
                    showCannotSelectItem(holder, item.value)
                    onCannotSelectItem(holder, item.value)
                }
            } else {
                if (canDeselect(item.value)) {
                    showItemDeselected(holder, item.value)
                    onItemDeselected(holder, item.value)
                    item.isSelected = false
                } else {
                    showCannotDeselectItem(holder, item.value)
                    onCannotDeselectItem(holder, item.value)
                }
            }
        }

        super.onBindViewHolder(holder, position)
    }

    val selected get() = items.selected
}