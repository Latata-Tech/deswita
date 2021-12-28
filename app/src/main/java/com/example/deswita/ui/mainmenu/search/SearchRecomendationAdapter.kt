package com.example.deswita.ui.mainmenu.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.deswita.databinding.ItemSearchBinding
import com.example.deswita.models.SearchRecomendation

class SearchRecomendationAdapter : RecyclerView.Adapter<SearchRecomendationAdapter.ViewHolder>() {
    private var searchRecomendations = emptyList<SearchRecomendation>()

    fun setData(searchRecomendations : List<SearchRecomendation>){
        this.searchRecomendations = searchRecomendations
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding : ItemSearchBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(searchRecomendation: SearchRecomendation){
            binding.ivSearchRecomendation.load(searchRecomendation.image)
            binding.tvSearchRecomendationDestination.text = searchRecomendation.name
            binding.tvSearchRecomendationLocation.text = searchRecomendation.location
            binding.tvSearchRecomendationDistance.text = searchRecomendation.distance[0].toString() + "km - " + searchRecomendation.distance[1].toString() + "km"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(searchRecomendations[position])
    }

    override fun getItemCount(): Int = searchRecomendations.size
}