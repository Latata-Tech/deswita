package com.example.deswita.ui.mainmenu.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.deswita.databinding.EventCardLayoutBinding
import com.example.deswita.models.Event
import com.example.deswita.utils.*

class TopEventAdapter(private val context: Context): RecyclerView.Adapter<TopEventAdapter.ViewHolder>() {

    private var events = emptyList<Event>()

    fun setData(events: List<Event>) {
        this.events = events
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: EventCardLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Event,position: Int) {
            with(binding) {
                ivEvent.load(Utils.getImageDrawable(context,event.image))
                tvTitle.text = event.name.CapitalizeFirstWord()
                tvDate.text = event.date.CapitalizeAllWord()

                val params = itemView.layoutParams as ViewGroup.MarginLayoutParams
                params.setMargins(32,0,if(position == events.size - 1) 32 else 0,0)
                itemView.layoutParams = params
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = EventCardLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(events[position],position)
    }

    override fun getItemCount(): Int = events.size
}