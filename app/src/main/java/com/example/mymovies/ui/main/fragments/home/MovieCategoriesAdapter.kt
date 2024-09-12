package com.example.mymovies.ui.main.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovies.databinding.MovieCategoryItemBinding
import com.example.mymovies.network.response.Genre
import com.example.mymovies.shared.listeners.CategoryClick

class MovieCategoriesAdapter :
    ListAdapter<Genre, MovieCategoriesAdapter.ViewHolder>(GenreDiffCallback()) {

    private var categoryClickListener: CategoryClick? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MovieCategoryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
    fun setListener(listener: CategoryClick){
        categoryClickListener = listener
    }

    inner class ViewHolder(private val binding: MovieCategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Genre) {
            binding.tvCategoryName.text = item.name
            itemView.isSelected = item.isClicked

            binding.root.setOnClickListener {
                categoryClickListener?.onCategoryClick(item)
            }
        }
    }
}

class GenreDiffCallback : DiffUtil.ItemCallback<Genre>() {
    override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return oldItem == newItem
    }
}
