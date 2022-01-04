package com.example.deswita.ui.mainmenu.profile.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.deswita.databinding.ItemStoryGridLayoutBinding
import com.example.deswita.models.Story
import com.example.deswita.utils.*

class StoriesAdapter (private val context: Context): RecyclerView.Adapter<StoriesAdapter.ViewHolder>() {

    private var stories = emptyList<Story>()

    fun setData(stories: List<Story>){
        this.stories = stories
        notifyDataSetChanged()
    }

    inner class ViewHolder( private val binding: ItemStoryGridLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(story: Story) {
            with(binding) {
                ivProfile.load(Utils.getImageDrawable(context,story.profile))
                tvName.text = story.name.CapitalizeAllWord()
                tvLocation.text = story.description.CapitalizeFirstWord()
                tvContent.text = story.contentText.CapitalizeFirstWord()
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