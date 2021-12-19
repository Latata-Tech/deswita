package com.example.deswita.ui.mainmenu.story

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.deswita.databinding.ItemStoryLayoutBinding
import com.example.deswita.models.Story
import com.example.deswita.utils.StoryDiffUtil

class StoryAdapter : RecyclerView.Adapter<StoryAdapter.ViewHolder>() {

    private var stories = emptyList<Story>()

    fun setData(stories: List<Story>){
        val storyDiffUtil = StoryDiffUtil(stories,this.stories)
        val diffUtilResult = DiffUtil.calculateDiff(storyDiffUtil)
        this.stories = stories
        diffUtilResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(val binding: ItemStoryLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(story: Story) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemStoryLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(stories[position])
    }

    override fun getItemCount(): Int = stories.size
}