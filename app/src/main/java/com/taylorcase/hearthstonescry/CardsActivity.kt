package com.taylorcase.hearthstonescry

import android.os.Bundle
import android.view.View
import com.taylorcase.hearthstonescry.base.CardsContract
import com.taylorcase.hearthstonescry.base.CardsGridActivity
import com.taylorcase.hearthstonescry.base.InjectLayout
import com.taylorcase.hearthstonescry.model.Card
import kotlinx.android.synthetic.main.activity_cards.*
import kotlinx.android.synthetic.main.include_toolbar.*
import javax.inject.Inject

@InjectLayout(R.layout.activity_cards)
open class CardsActivity : CardsGridActivity(), CardsContract.View, View.OnClickListener  {

    @Inject lateinit var presenter: CardsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as ScryApplication).getAppComponent()?.inject(this)
        setupToolbar(toolbar)
        presenter.attach(this)

        setupOnRefreshListener()

        askToRateApp()
    }

    private fun askToRateApp() {
        if (presenter.shouldAskToRateApp()) {
            ReviewAppDialog().show(supportFragmentManager, ReviewAppDialog.REVIEW_APP_TAG)
            presenter.userWasAskedToRateApp()
        }
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

    override fun displayNetworkError() {
        cards_swipe_refresh.isRefreshing = false
        displaySnackbar(getString(R.string.cards_activity_network_error))
    }

    public override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }
}
