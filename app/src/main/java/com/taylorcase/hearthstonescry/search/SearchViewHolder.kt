package com.taylorcase.hearthstonescry.search

import android.support.annotation.VisibleForTesting
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.taylorcase.hearthstonescry.R

open class SearchViewHolder(view: View?) : RecyclerView.ViewHolder(view), View.OnClickListener {

    @VisibleForTesting var listener: OnSuggestionClickListener? = null
    @VisibleForTesting var searchSuggestionTextView: TextView? = null

    interface OnSuggestionClickListener {
        fun onSuggestionClicked(suggestion: String)
    }

    init {
        searchSuggestionTextView = itemView?.findViewById(R.id.search_suggestion_text_view)
        searchSuggestionTextView?.setOnClickListener(this)
    }

    open fun setOnSuggestionClickListener(listener: OnSuggestionClickListener) {
        this.listener = listener
    }

    open fun setText(text: String) {
        searchSuggestionTextView?.text = text
    }

    override fun onClick(v: View?) {
        listener?.onSuggestionClicked(searchSuggestionTextView?.text.toString())
    }
}
