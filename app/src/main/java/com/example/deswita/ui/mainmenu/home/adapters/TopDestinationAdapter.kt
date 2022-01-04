package com.example.deswita.ui.mainmenu.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.deswita.databinding.DestinationCardLayoutBinding
import com.example.deswita.models.Destination
import com.example.deswita.utils.CapitalizeAllWord
import com.example.deswita.utils.Utils

class TopDestinationAdapter(private val context: Context): RecyclerView.Adapter<TopDestinationAdapter.ViewHolder>() {

    private var onClickItemCallback: OnClickItemCallback? = null

    fun setOnClickItemCallback(onClickItemCallback: OnClickItemCallback) {
        this.onClickItemCallback = onClickItemCallback
    }

    interface OnClickItemCallback {
        fun onClick(destination: Destination)
    }

    private var destinations = emptyList<Destination>()

    fun setData(destinations: List<Destination>) {
        this.destinations = destinations
        notifyDataSetChanged()
    }


    inner class ViewHolder(private val binding: DestinationCardLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(destination: Destination,position: Int) {
            with(binding) {
                ivDestination.load(Utils.getImageDrawable(context,destination.image))

                tvTitleDestination.text = destination.name.CapitalizeAllWord()
                tvLocationDestination.text = destination.location.CapitalizeAllWord()

                val params = itemView.layoutParams as ViewGroup.MarginLayoutParams
                params.setMargins(32,0, if(position == destinations.size - 1) 32 else 0,0)
                itemView.layoutParams = params

                itemView.setOnClickListener {
                    onClickItemCallback?.onClick(destination)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(DestinationCardLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(destinations[position],position)
    }

    override fun getItemCount(): Int = destinations.size

}