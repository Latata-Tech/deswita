package com.example.deswita.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.deswita.models.Event

class EventDiffUtil(
    private val oldList: List<Event>,
    private val newList: List<Event>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] === newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]

}