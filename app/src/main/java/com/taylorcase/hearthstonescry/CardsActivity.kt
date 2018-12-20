package com.taylorcase.hearthstonescry

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.taylorcase.hearthstonescry.base.CardsContract
import com.taylorcase.hearthstonescry.base.CardsGridActivity
import com.taylorcase.hearthstonescry.base.InjectLayout
import com.taylorcase.hearthstonescry.filter.FilterActivity
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
        cards_filter_button.setOnClickListener(this)

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

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.cards_filter_button -> {
                val intent = Intent(this, FilterActivity::class.java)
                startActivityForResult(intent, FILTER_DATA_REQUEST)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_CANCELED) {
            return
        }

        if (resultCode == Activity.RESULT_OK && requestCode == FILTER_DATA_REQUEST) {
            adapter.swapData(emptyList())
            setLoading(true)
            presenter.loadCardsWithFilters(data?.getParcelableExtra(FilterActivity.FILTER_DATA)!!)
        } else {
            showError()
        }
    }

    override fun displayNetworkError() {
        cards_swipe_refresh.isRefreshing = false
        displaySnackbar(getString(R.string.cards_activity_network_error))
    }

    public override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

    companion object {
        const val FILTER_DATA_REQUEST = 19
    }
}
