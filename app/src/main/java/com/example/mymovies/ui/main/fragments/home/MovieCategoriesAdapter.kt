package com.example.mymovies.ui.main.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovies.databinding.MovieCategoryItemBinding
import com.example.mymovies.network.response.Genre
import com.example.mymovies.shared.listeners.CategoryClick
import com.example.mymovies.shared.utils.MyDiffUtil

class MovieCategoriesAdapter : RecyclerView.Adapter<MovieCategoriesAdapter.ViewHolder>() {
    private lateinit var binding: MovieCategoryItemBinding
    private lateinit var onItemClick: CategoryClick

    private var categoryList = mutableListOf<Genre>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding =
            MovieCategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount() = categoryList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = categoryList[position]

        binding.tvCategoryName.text = item.name
        holder.itemView.isSelected = item.isClicked


        binding.root.setOnClickListener {
            onItemClick.onCategoryClick(item)
        }

    }

    inner class ViewHolder(private val binding: MovieCategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun setData(categoryList: List<Genre>) {
        this.categoryList.clear()
        this.categoryList.addAll(categoryList)
        notifyDataSetChanged()
    }
    fun setListener(listener: CategoryClick){
        this.onItemClick = listener
    }

}