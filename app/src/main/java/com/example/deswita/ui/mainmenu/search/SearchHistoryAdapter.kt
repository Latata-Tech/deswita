package com.example.deswita.ui.mainmenu.search

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.deswita.databinding.ItemSearchHistoryBinding
import com.example.deswita.models.Event
import com.example.deswita.models.SearchHistory

class SearchHistoryAdapter : RecyclerView.Adapter<SearchHistoryAdapter.ViewHolder>() {
    private var searchHistories = emptyList<SearchHistory>()

    fun setData(searchHistories : List<SearchHistory>){
        this.searchHistories = searchHistories
        notifyDataSetChanged()
    }


    inner class ViewHolder(private val binding: ItemSearchHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(searchHistories : SearchHistory){
            binding.textSearch.text = searchHistories.name + " " + searchHistories.location
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSearchHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(searchHistories[position])
    }

    override fun getItemCount(): Int {
        return searchHistories.size
    }
}