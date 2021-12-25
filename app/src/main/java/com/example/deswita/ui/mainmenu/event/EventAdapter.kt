package com.example.deswita.ui.mainmenu.event

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.deswita.databinding.ItemEventLayoutBinding
import com.example.deswita.models.Event
import com.example.deswita.utils.EventDiffUtil

class EventAdapter :RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    private var events = emptyList<Event>()

    fun setData(events: List<Event>){
        val eventDiffUtil = EventDiffUtil(events,this.events)
        val diffUtilResult = DiffUtil.calculateDiff(eventDiffUtil)
        this.events = events
        diffUtilResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder (private val binding: ItemEventLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Event,position: Int){
            with(binding) {
                tvNameEventItem.text = event.name
                tvDateEventItem.text = event.date
                tvLocationEventItem.text = event.location
                tvDescEventItem.text = event.description
                ivEventItem.load(event.image)
            }

            if(position == events.size - 1) {
                val params = itemView.layoutParams as ViewGroup.MarginLayoutParams
                params.setMargins(36,20,36,50)
                itemView.layoutParams = params
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemEventLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(events[position],position)
    }

    override fun getItemCount(): Int = events.size



}