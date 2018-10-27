package com.taylorcase.hearthstonescry

import android.os.Bundle
import com.rengwuxian.materialedittext.MaterialEditText
import com.taylorcase.hearthstonescry.base.CardsContract
import com.taylorcase.hearthstonescry.base.CardsGridActivity
import com.taylorcase.hearthstonescry.base.InjectLayout
import com.taylorcase.hearthstonescry.model.Card
import kotlinx.android.synthetic.main.activity_cards.*
import kotlinx.android.synthetic.main.include_toolbar.*
import timber.log.Timber
import javax.inject.Inject

@InjectLayout(R.layout.activity_cards)
open class CardsActivity : CardsGridActivity(), CardsContract.View {

    @Inject lateinit var presenter: CardsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as ScryApplication).getAppComponent()?.inject(this)
        setupToolbar(toolbar)
        presenter.attach(this)

        setupOnRefreshListener()
    }

    private fun setupOnRefreshListener() {
        cards_swipe_refresh.setOnRefreshListener {
            presenter.refreshAllCards()
        }
    }

    public override fun loadCards() {
        presenter.loadCards()
    }

    override fun displayCards(cards: List<Card>?) {
        cards_swipe_refresh.isRefreshing = false
        cards?.let { showCards(it) }
    }

    public override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }
}
