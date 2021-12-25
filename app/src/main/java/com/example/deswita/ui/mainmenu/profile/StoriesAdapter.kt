package com.example.deswita.ui.mainmenu.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.deswita.databinding.ItemStoryGridLayoutBinding
import com.example.deswita.models.Story

class StoriesAdapter : RecyclerView.Adapter<StoriesAdapter.ViewHolder>() {

    private var stories = emptyList<Story>()

    fun setData(stories: List<Story>){
        this.stories = stories
        notifyDataSetChanged()
    }

    inner class ViewHolder( private val binding: ItemStoryGridLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(story: Story) {
            with(binding) {
                ivProfile.load(story.profile)
                tvName.text = story.name
                tvLocation.text = story.description
                tvContent.text = story.contentText
                tvLike.text = story.likeTotal.toString()
                tvComment.text = story.commentTotal.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemStoryGridLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(stories[position])
    }

    override fun getItemCount(): Int = stories.size
}