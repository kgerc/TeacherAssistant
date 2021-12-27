package com.example.teacherassistant.helpers

typealias SelectableList<T> = List<Selectable<T>>
typealias SelectIterable<T> = Iterable<Selectable<T>>

class Selectable<T>(val value: T) {
    var isSelected = false

    fun select() {isSelected = true}
    fun deselect() {isSelected = false}
}

val<T> SelectIterable<T>.selected: List<T>
get() = this.filter { it.isSelected }.map { it.value }

fun<T> selectListOf(vararg items: T): SelectableList<T> {
    val itemsAsSelectable = items.map { Selectable(it) }.toTypedArray()
    return listOf(*itemsAsSelectable)
}

fun<T> Iterable<T>.asSelectable(): SelectableList<T> {
    return this.map { Selectable(it) }
}

fun<T> SelectIterable<T>.deselectAll() {
    forEach { it.deselect() }
}