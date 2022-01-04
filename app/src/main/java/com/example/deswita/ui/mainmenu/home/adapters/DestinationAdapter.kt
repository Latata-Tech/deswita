package com.example.deswita.ui.mainmenu.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.deswita.databinding.DestinationCardLayout2Binding
import com.example.deswita.models.Destination
import com.example.deswita.utils.*

class DestinationAdapter(private val context: Context): RecyclerView.Adapter<DestinationAdapter.ViewHolder>() {

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

    inner class ViewHolder(private val binding: DestinationCardLayout2Binding): RecyclerView.ViewHolder(binding.root) {
        fun bind(destination: Destination,position: Int) {
            with(binding) {
                ivDestination.load(Utils.getImageDrawable(context,destination.image))
                tvName.text = destination.name.CapitalizeAllWord()
                tvLocation.text = destination.location.CapitalizeFirstWord()
                tvRating.text = destination.rating.toString()
                tvDistance.text = "${destination.distance} Km"

                if(position == destinations.size-1) {
                    val params = itemView.layoutParams as ViewGroup.MarginLayoutParams
                    params.setMargins( 10,30,10,60 )
                    itemView.layoutParams = params
                }

                itemView.setOnClickListener {
                    onClickItemCallback?.onClick(destination)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = DestinationCardLayout2Binding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(destinations[position],position)
    }

    override fun getItemCount(): Int = destinations.size


}