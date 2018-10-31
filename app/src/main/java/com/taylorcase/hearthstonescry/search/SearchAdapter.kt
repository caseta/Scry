package com.taylorcase.hearthstonescry.search

import android.support.annotation.VisibleForTesting
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.taylorcase.hearthstonescry.R

open class SearchAdapter : RecyclerView.Adapter<SearchViewHolder>() {

    private var suggestions: List<String> = emptyList()
    @VisibleForTesting var listener: SearchViewHolder.OnSuggestionClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_suggestion, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.onSuggestionClickListener = listener
        holder.searchSuggestionTextView?.text = suggestions[position]
    }

    override fun getItemCount() = suggestions.size

    fun swapData(suggestions: List<String>) {
        this.suggestions = suggestions
        notifyDataSetChanged()
    }
}
