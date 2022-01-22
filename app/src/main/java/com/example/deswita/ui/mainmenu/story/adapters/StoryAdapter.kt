package com.example.deswita.ui.mainmenu.story.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.deswita.databinding.ItemStoryLayoutBinding
import com.example.deswita.models.Story
import com.example.deswita.utils.*

class StoryAdapter(private val context: Context) : RecyclerView.Adapter<StoryAdapter.ViewHolder>() {

    companion object {
        const val CLICK_IMAGE = "CLICK_IMAGE"
        const val CLICK_COMMENT = "CLICK_COMMENT"
        const val CLICK_USER = "CLICK_USER"
    }

    private var stories = emptyList<Story>()

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onClick(story: Story,state: String)
    }

    fun setData(stories: List<Story>){
        val storyDiffUtil = StoryDiffUtil(stories,this.stories)
        val diffUtilResult = DiffUtil.calculateDiff(storyDiffUtil)
        this.stories = stories
        diffUtilResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(val binding: ItemStoryLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(story: Story,position: Int) {
            with(binding) {
                tvNamePost.text = story.name.CapitalizeAllWord()
                tvDescriptionPost.text = story.description.CapitalizeFirstWord()
                tvContentPost.text = story.contentText.CapitalizeFirstWord()
                tvLike.text = story.likeTotal.toString()
                tvComment.text = story.commentTotal.toString()
                profileImage.load(Utils.getImageDrawable(context,story.profile))
                ivPost.load(Utils.getImageDrawable(context,story.contentImage))

                tvNamePost.setOnClickListener {
                    onItemClickCallback.onClick(story, CLICK_USER)
                }
                profileImage.setOnClickListener {
                    onItemClickCallback.onClick(story, CLICK_USER)
                }
                tvDescriptionPost.setOnClickListener {
                    onItemClickCallback.onClick(story, CLICK_USER)
                }

                ivPost.setOnClickListener {
                    onItemClickCallback.onClick(story, CLICK_IMAGE)
                }

                tvComment.setOnClickListener {
                    onItemClickCallback.onClick(story, CLICK_COMMENT)
                }
                ivComment.setOnClickListener {
                    onItemClickCallback.onClick(story, CLICK_COMMENT)
                }
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