package com.example.deswita.ui.notification

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.deswita.databinding.ItemNotificationLayoutBinding
import com.example.deswita.models.Notification
import com.example.deswita.utils.*

class NotificationAdapter(private val context: Context): RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    private var notifications = emptyList<Notification>()

    fun setData(notification: List<Notification>) {
        this.notifications = notification
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemNotificationLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(notification: Notification,position: Int) {
            with(binding) {
                civProfileNotif.load(Utils.getImageDrawable(context,notification.image))
                tvTitleNotif.text = notification.title.CapitalizeAllWord()

                if(position == notifications.size - 1) {
                    val params = itemView.layoutParams as ViewGroup.MarginLayoutParams
                    params.setMargins(16,20,16,50)
                    itemView.layoutParams = params
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNotificationLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(notifications[position],position)
    }

    override fun getItemCount(): Int = notifications.size
}