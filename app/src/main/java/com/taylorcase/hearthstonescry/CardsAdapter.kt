package com.taylorcase.hearthstonescry

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.taylorcase.hearthstonescry.model.Card
import com.taylorcase.hearthstonescry.utils.ImageLoader
import javax.inject.Inject

open class CardsAdapter @Inject constructor(var imageLoader: ImageLoader) : RecyclerView.Adapter<CardsViewHolder>() {

    var cards: List<Card> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder {
        return CardsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false), imageLoader)
    }

    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) {
        cards[position].let { holder.loadCard(it, position) }
    }

    override fun getItemCount() = cards.size

    fun swapData(cards: List<Card>) {
        this.cards = cards
        notifyDataSetChanged()
    }
}
