package com.example.deswita.ui.mainmenu.story

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.deswita.databinding.ItemStoryLayoutBinding
import com.example.deswita.models.Story

class StoryAdapter : RecyclerView.Adapter<StoryAdapter.ViewHolder>() {

    private var stories = emptyList<Story>()

    fun setData(stories: List<Story>){
        val storyDiffUtil = com.example.deswita.utils.StoryDiffUtil(stories,this.stories)
        val diffUtilResult = DiffUtil.calculateDiff(storyDiffUtil)
        this.stories = stories
        diffUtilResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(val binding: ItemStoryLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(story: Story,position: Int) {
            with(binding) {
                tvNamePost.text = story.name
                tvDescriptionPost.text = story.description
                tvContentPost.text = story.contentText
                tvLike.text = story.likeTotal.toString()
                tvComment.text = story.commentTotal.toString()
                profileImage.load(story.profile)
                ivPost.load(story.contentImage)
            }

            if(position == stories.size - 1) {
                val params = itemView.layoutParams as ViewGroup.MarginLayoutParams
                params.setMargins(20,20,20,30)
                itemView.layoutParams = params
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemStoryLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(stories[position],position)
    }

    override fun getItemCount(): Int = stories.size
}