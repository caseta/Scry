package com.taylorcase.hearthstonescry.savedcards

import android.os.Bundle
import com.taylorcase.hearthstonescry.base.CardsGridActivity
import com.taylorcase.hearthstonescry.R
import com.taylorcase.hearthstonescry.ScryApplication
import com.taylorcase.hearthstonescry.model.Card
import com.taylorcase.hearthstonescry.utils.makeGone
import com.taylorcase.hearthstonescry.utils.makeVisible
import kotlinx.android.synthetic.main.activity_cards.*
import kotlinx.android.synthetic.main.include_toolbar.*
import javax.inject.Inject

open class SavedCardsActivity : CardsGridActivity(), SavedCardsContract.View {

    @Inject lateinit var presenter: SavedCardsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as ScryApplication).getComponent().inject(this)
        setupToolbar(toolbar)

        presenter.attach(this)

        cards_swipe_refresh.isEnabled = false

        card_sort_filter_container.makeGone()
    }

    override fun provideContentLayoutId(): Int {
        return R.layout.activity_cards
    }

    public override fun loadCards() {
        if (presenter.getSavedCardCount() > 0) {
            presenter.loadSavedCards()
        } else {
            showCards(emptyList())
            cards_no_saves.makeVisible()
            cards_recycler_view.makeGone()
        }
    }

    override fun displayCards(cards: List<Card>) = showCards(cards)

    public override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }
}
