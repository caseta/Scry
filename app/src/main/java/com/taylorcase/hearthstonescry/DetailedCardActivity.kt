package com.taylorcase.hearthstonescry

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.support.annotation.VisibleForTesting
import android.text.Html
import android.view.View
import android.view.View.*
import android.widget.ImageView
import com.taylorcase.hearthstonescry.CardsViewHolder.Companion.EXTRA_POSITION
import com.taylorcase.hearthstonescry.base.BaseActivity
import com.taylorcase.hearthstonescry.base.InjectLayout
import com.taylorcase.hearthstonescry.model.Card
import com.taylorcase.hearthstonescry.model.Card.CREATOR.CARD_EXTRA
import com.taylorcase.hearthstonescry.utils.ImageLoader
import kotlinx.android.synthetic.main.activity_detailed_card.*
import kotlinx.android.synthetic.main.include_toolbar.*
import javax.inject.Inject
import android.view.animation.AnimationUtils

@InjectLayout(R.layout.activity_detailed_card)
open class DetailedCardActivity : BaseActivity(), View.OnClickListener, DetailedCardContract.View {

    @Inject lateinit var imageLoader: ImageLoader
    @Inject lateinit var presenter: DetailedCardContract.Presenter

    @VisibleForTesting private val card
        get() = intent.getParcelableExtra<Card>(CARD_EXTRA)

    @VisibleForTesting private val position
        get() = intent.getIntExtra(EXTRA_POSITION, -1)

    private var cardName: String = ""
    private var isCardSaved: Boolean = false
    @VisibleForTesting var heart: Drawable? = null
    @VisibleForTesting var heartFilled: Drawable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as ScryApplication).getAppComponent()?.inject(this)
        setupToolbar(toolbar, navigationMethod = BACK_ARROW)

        presenter.attach(this)
        card_fab.setOnClickListener(this)

        setLoading(true)

        populateValues()
        populateProperHearts()

        isCardSaved = presenter.isCardSaved(card)
        toggleHeart()

        slideUpDown()
    }

    private fun populateValues() {
        val iv = findViewById<ImageView>(R.id.card_image)
        iv.transitionName = card.img
        card_container.transitionName = card.cardId
        imageLoader.loadImage(card.img, this, iv)
        card_name.text = card.name
        card_artist.text = getString(R.string.card_activity_artist, card.artist)
        card_set.text = getString(R.string.card_activity_set, card.cardSet)
        card_class.text = getString(R.string.card_activity_class, card.playerClass)

        cardName = card.name

        val flavorText = card.flavor
        if (flavorText.isNotEmpty()) {
            card_flavor_text.visibility = VISIBLE
            card_flavor_text.text = Html.fromHtml(flavorText)
        } else {
            card_flavor_text.visibility = GONE
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.card_fab -> {
                isCardSaved = if (isCardSaved) {
                    presenter.removeCard(card)
                    false
                } else {
                    presenter.saveCard(card)
                    true
                }

                displaySnackbarMessage()
                toggleHeart()
            }
        }
    }

    private fun displaySnackbarMessage() {
        var message = String.format(getString(R.string.detailed_card_activity_removed), cardName)
        if (isCardSaved) {
            message = String.format(getString(R.string.detailed_card_activity_saved), cardName)
        }
        displaySnackbar(message)
    }

    private fun toggleHeart() {
        if (isCardSaved) {
            card_fab.setImageDrawable(heartFilled)
        } else {
            card_fab.setImageDrawable(heart)
        }
    }

    private fun populateProperHearts() {
        if (heroUtils.shouldAssetsBeWhite()) {
            heart = resources.getDrawable(R.drawable.ic_favorite_border_white_24dp, theme)
            heartFilled = resources.getDrawable(R.drawable.ic_favorite_white_24dp, theme)
        } else {
            heart = resources.getDrawable(R.drawable.ic_favorite_border_black_24dp, theme)
            heartFilled = resources.getDrawable(R.drawable.ic_favorite_black_24dp, theme)
        }
    }

    override fun onBackPressed() {
        slideUpDown()
        setResultAndFinish()
    }

    private fun setResultAndFinish() {
        val resultData = Intent().apply { putExtra(EXTRA_POSITION, position) }
        setResult(RESULT_OK, resultData)
        finishAfterTransition()
    }

    public override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

    private fun slideUpDown() {
        if (!isPanelShown()) {
            val bottomUp = AnimationUtils.loadAnimation(this, R.anim.bottom_up)

            card_text_container.startAnimation(bottomUp)
            card_text_container.visibility = View.VISIBLE
            val handler = Handler()
            handler.postDelayed({ card_fab.show() }, 700)
        } else {
            card_fab.hide()
            val bottomDown = AnimationUtils.loadAnimation(this, R.anim.bottom_down)

            card_text_container.startAnimation(bottomDown)
            card_text_container.visibility = View.GONE
        }
    }

    private fun isPanelShown() = card_text_container.visibility == View.VISIBLE
}
