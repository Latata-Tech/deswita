package com.example.deswita.ui.mainmenu.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.deswita.databinding.ItemEventGridLayoutBinding
import com.example.deswita.models.Event
import com.example.deswita.utils.EventDiffUtil

class EventGridAdapter : RecyclerView.Adapter<EventGridAdapter.ViewHolder>() {

    private var events = emptyList<Event>()

    fun setData(events: List<Event>){
        val eventDiffUtil = EventDiffUtil(events,this.events)
        val diffUtilResult = DiffUtil.calculateDiff(eventDiffUtil)
        this.events = events
        diffUtilResult.dispatchUpdatesTo(this)
    }


    inner class ViewHolder(private val binding: ItemEventGridLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Event) {
            binding.imageView.load(event.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemEventGridLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(events[position])
    }

    override fun getItemCount(): Int = events.size


}