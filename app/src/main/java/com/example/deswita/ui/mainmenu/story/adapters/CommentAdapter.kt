package com.example.deswita.ui.mainmenu.story.adapters

import com.example.deswita.databinding.ItemCommentBinding
import com.example.deswita.models.Comment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CommentAdapter(private val context: Context) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

//    private var onClickItemCallback: OnClickItemCallback? = null
//
//    fun setOnClickItemCallback(onClickItemCallback: OnClickItemCallback) {
//        this.onClickItemCallback = onClickItemCallback
//    }
//
//    interface OnClickItemCallback {
//        fun onClick(destination: Destination)
//    }

    private var comments = emptyList<Comment>()

    fun setData(comments: List<Comment>){
        this.comments = comments
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: Comment,position: Int){

            with(binding) {
                tvName.text = comment.name
                tvComment.text = comment.comment
                tvDate.text = comment.date
            }

            if(position == comments.size - 1) {
                val params = itemView.layoutParams as ViewGroup.MarginLayoutParams
                params.setMargins(0,0,0,50)
                itemView.layoutParams = params
            }

//            itemView.setOnClickListener {
//                onClickItemCallback?.onClick(comment)
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(comments[position],position)
    }

    override fun getItemCount(): Int = comments.size
}