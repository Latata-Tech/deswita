package com.example.deswita.ui.reviews.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.deswita.databinding.ItemReviewLayoutBinding
import com.example.deswita.models.Review
import com.example.deswita.utils.Utils

class ReviewAdapter(private val context: Context): RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    private var reviews = emptyList<Review>()

    fun setData(reviews: List<Review>) {
        this.reviews = reviews
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemReviewLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(review: Review,position: Int) {
            with(binding) {
                civReview.load(Utils.getImageDrawable(context,review.image))
                tvNameReview.text = review.name
                tvContentReview.text = review.content
                tvDateReview.text = review.date
                ratingBar.rating = review.rating
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemReviewLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(reviews[position],position)
    }

    override fun getItemCount(): Int = reviews.size
}