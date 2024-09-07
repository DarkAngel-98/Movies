package com.example.mymovies.shared.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.mymovies.network.response.Genre

class MyDiffUtil(
    private val oldList: List<Genre>,
    private val newList: List<Genre>
): DiffUtil.ItemCallback<Genre>() {
//    override fun getOldListSize(): Int {
//        return oldList.size
//    }
//
//    override fun getNewListSize(): Int {
//        return newList.size
//    }

//    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//        return oldList[oldItemPosition].id == newList[newItemPosition].id
//    }
//
//    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//        return when{
//            oldList[oldItemPosition].id != newList[newItemPosition].id -> false
//            oldList[oldItemPosition].name != newList[newItemPosition].name -> false
//            oldList[oldItemPosition].isClicked != newList[newItemPosition].isClicked -> false
//            else -> true
//        }
//    }

    override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return (oldItem.id == newItem.id && oldItem.isClicked == newItem.isClicked)
    }
}