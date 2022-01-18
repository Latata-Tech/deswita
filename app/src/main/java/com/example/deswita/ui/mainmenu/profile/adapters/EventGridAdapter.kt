package com.example.deswita.ui.mainmenu.profile.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.deswita.databinding.ItemEventGridLayoutBinding
import com.example.deswita.models.Event
import com.example.deswita.utils.EventDiffUtil
import com.example.deswita.utils.Utils

class EventGridAdapter (private val context: Context): RecyclerView.Adapter<EventGridAdapter.ViewHolder>() {

    private var onClickItemCallback: OnClickItemCallback? = null

    fun setOnClickItemCallback(onClickItemCallback: OnClickItemCallback) {
        this.onClickItemCallback = onClickItemCallback
    }

    interface OnClickItemCallback {
        fun onClick(event: Event)
    }

    private var events = emptyList<Event>()

    fun setData(events: List<Event>){
        val eventDiffUtil = EventDiffUtil(events,this.events)
        val diffUtilResult = DiffUtil.calculateDiff(eventDiffUtil)
        this.events = events
        diffUtilResult.dispatchUpdatesTo(this)
    }


    inner class ViewHolder(private val binding: ItemEventGridLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Event) {
            binding.imageView.load(Utils.getImageDrawable(context,event.image))

            itemView.setOnClickListener {
                onClickItemCallback?.onClick(event)
            }
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