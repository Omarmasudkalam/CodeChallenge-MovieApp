package com.omk.presentation.ui.feed.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omk.clean.databinding.ItemSeparatorBinding
import com.omk.presentation.entities.MovieListItem


class SeparatorViewHolder(
    parent: ViewGroup,
) : RecyclerView.ViewHolder(
    ItemSeparatorBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
) {

    private val binding = ItemSeparatorBinding.bind(itemView)

    fun bind(separator: MovieListItem.Separator) {
        binding.title.text = separator.category
    }
}