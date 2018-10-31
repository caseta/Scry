package com.taylorcase.hearthstonescry

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.util.Pair
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.taylorcase.hearthstonescry.model.Card
import com.taylorcase.hearthstonescry.model.Card.CREATOR.CARD_EXTRA
import com.taylorcase.hearthstonescry.utils.ImageLoader
import android.support.v4.app.ActivityOptionsCompat

open class CardsViewHolder(itemView: View, var imageLoader: ImageLoader) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    companion object {
        const val EXTRA_POSITION = "position"
    }

    private val context: Context = itemView.context
    private val cardImageView: ImageView = itemView.findViewById(R.id.card_image)
    private val cardContainer: LinearLayout = itemView.findViewById(R.id.card_container)

    private var position: Int? = null
    private lateinit var card: Card

    open fun loadCard(card: Card, position: Int) {
        itemView.setOnClickListener(this)
        itemView.isEnabled = true
        itemView.isClickable = true
        this.card = card
        this.position = position
        cardImageView.transitionName = card.img
        cardContainer.transitionName = card.cardId
        imageLoader.loadImage(card.img, cardImageView)
    }

    override fun onClick(v: View?) {
        itemView.isEnabled = false
        itemView.isClickable = false
        val backgroundPair = Pair.create(cardContainer as View, cardContainer.transitionName)
        val imagePair = Pair.create(cardImageView as View, cardImageView.transitionName)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, backgroundPair, imagePair)
        context.startActivity(Intent(context, DetailedCardActivity::class.java).putExtra(EXTRA_POSITION, position).putExtra(CARD_EXTRA, card), options.toBundle())
    }
}