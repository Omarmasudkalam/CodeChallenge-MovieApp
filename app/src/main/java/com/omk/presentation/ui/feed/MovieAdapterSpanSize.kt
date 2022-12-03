package com.omk.presentation.ui.feed

import androidx.recyclerview.widget.GridLayoutManager
import com.omk.clean.R


class MovieAdapterSpanSize {


    data class Config(val gridSpanSize: Int) {
        val movieColumnSpanSize: Int = 1
        val separatorColumnSpanSize: Int = gridSpanSize
    }

    class Lookup(
        private val config: Config,
        private val adapter: MovieAdapter,
    ) : GridLayoutManager.SpanSizeLookup() {

        init {
            isSpanIndexCacheEnabled = true
        }

        override fun getSpanSize(position: Int): Int = when (adapter.getItemViewType(position)) {
            R.layout.item_movie -> config.movieColumnSpanSize
            else -> config.separatorColumnSpanSize
        }
    }
}