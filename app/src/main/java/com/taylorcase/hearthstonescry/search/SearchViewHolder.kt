package com.taylorcase.hearthstonescry.search

import android.support.annotation.VisibleForTesting
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.taylorcase.hearthstonescry.R

open class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    @VisibleForTesting var onSuggestionClickListener: OnSuggestionClickListener? = null
    @VisibleForTesting var searchSuggestionTextView: TextView? = null

    interface OnSuggestionClickListener {
        fun onSuggestionClicked(suggestion: String)
    }

    init {
        searchSuggestionTextView = itemView.findViewById(R.id.search_suggestion_text_view)
        searchSuggestionTextView?.setOnClickListener { onSuggestionClicked() }
    }

    private fun onSuggestionClicked() {
        onSuggestionClickListener?.onSuggestionClicked(searchSuggestionTextView?.text.toString())
    }
}
