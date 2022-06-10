package com.example.admin

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.admin.databinding.ItemEventBinding
import com.example.deswita.models.Event

class EventAdapter(private val context: Context) :RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    private var onClickItemCallback: OnClickItemCallback? = null

    fun setOnClickItemCallback(onClickItemCallback: OnClickItemCallback) {
        this.onClickItemCallback = onClickItemCallback
    }

    interface OnClickItemCallback {
        fun onClick(event: Event)
    }

    private var events = emptyList<Event>()

    fun setData(events: List<Event>){
        this.events = events
        notifyDataSetChanged()
    }

    inner class ViewHolder (private val binding: ItemEventBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Event,position: Int){
            with(binding) {
                tvNameEventItem.text = event.name
                tvDateEventItem.text = event.date
                tvLocationEventItem.text = event.location
                tvDescEventItem.text = event.description
                ivEventItem.load(Utils.getImageDrawable(context,event.image))
            }

            if(position == events.size - 1) {
                val params = itemView.layoutParams as ViewGroup.MarginLayoutParams
                params.setMargins(36,20,36,50)
                itemView.layoutParams = params
            }

            itemView.setOnClickListener {
                onClickItemCallback?.onClick(event)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(events[position],position)
    }

    override fun getItemCount(): Int = events.size



}