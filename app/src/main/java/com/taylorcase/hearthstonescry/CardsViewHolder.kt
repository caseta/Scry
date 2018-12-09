package com.taylorcase.hearthstonescry

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.annotation.VisibleForTesting
import android.support.v4.util.Pair
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.taylorcase.hearthstonescry.model.Card
import com.taylorcase.hearthstonescry.model.Card.CREATOR.CARD_EXTRA
import com.taylorcase.hearthstonescry.utils.ImageLoader
import android.support.v4.app.ActivityOptionsCompat
import com.taylorcase.hearthstonescry.utils.DeviceUtils

open class CardsViewHolder(itemView: View, private val imageLoader: ImageLoader) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    companion object {
        const val EXTRA_POSITION = "position"
    }

    @VisibleForTesting var context: Context = itemView.context
    private val cardImageView: ImageView = itemView.findViewById(R.id.card_image)
    private val cardContainer: LinearLayout = itemView.findViewById(R.id.card_container)

    private var position: Int? = null
    @VisibleForTesting lateinit var card: Card

    open fun loadCard(card: Card, position: Int) {
        itemView.setOnClickListener(this)
        setClickable(true)

        this.card = card
        this.position = position

        cardImageView.transitionName = card.img
        cardContainer.transitionName = card.cardId
        imageLoader.loadImage(card.img, cardImageView)
    }

    override fun onClick(v: View?) {
        setClickable(false)

        val detailedCardIntent = Intent(context, DetailedCardActivity::class.java).putExtra(CARD_EXTRA, card)

        if (DeviceUtils.isSamsungDevice()) {
            context.startActivity(detailedCardIntent)
        } else {
            val backgroundPair = Pair.create(cardContainer as View, cardContainer.transitionName)
            val imagePair = Pair.create(cardImageView as View, cardImageView.transitionName)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, backgroundPair, imagePair)
            detailedCardIntent.putExtra(EXTRA_POSITION, position)
            context.startActivity(detailedCardIntent, options.toBundle())
        }
    }

    private fun setClickable(clickable: Boolean) {
        itemView.isEnabled = clickable
        itemView.isClickable = clickable
    }
}
