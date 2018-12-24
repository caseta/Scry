package com.taylorcase.hearthstonescry

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.view.View
import com.taylorcase.hearthstonescry.base.CardsContract
import com.taylorcase.hearthstonescry.base.CardsGridActivity
import com.taylorcase.hearthstonescry.base.InjectLayout
import com.taylorcase.hearthstonescry.filter.FilterActivity
import com.taylorcase.hearthstonescry.model.Card
import com.taylorcase.hearthstonescry.model.FilterItem
import kotlinx.android.synthetic.main.activity_cards.*
import kotlinx.android.synthetic.main.include_toolbar.*
import javax.inject.Inject

@InjectLayout(R.layout.activity_cards)
open class CardsActivity : CardsGridActivity(), CardsContract.View, View.OnClickListener  {

    @Inject lateinit var presenter: CardsContract.Presenter

    @VisibleForTesting var filterItem: FilterItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as ScryApplication).getAppComponent()?.inject(this)
        setupToolbar(toolbar)
        presenter.attach(this)

        setupOnRefreshListener()
        cards_filter_button.setOnClickListener(this)

        askToRateApp()

        filterItem = savedInstanceState?.getParcelable(FilterItem.FILTER_EXTRA)

        if (filterItem == null) filterItem = FilterItem()
    }

    override fun onResume() {
        super.onResume()
        cards_filter_button.isEnabled = true
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
        val filterItem = this.filterItem
        if (filterItem == null) {
            presenter.loadCards()
        } else {
            if (filterItem.isFilterEmpty()) {
                presenter.loadCards()
            } else {
                presenter.loadCardsWithFilters(filterItem)
            }
        }
    }

    override fun displayCards(cards: List<Card>?) {
        cards_swipe_refresh.isRefreshing = false
        cards?.let { showCards(it) }
    }

    override fun onSaveInstanceState(bundle: Bundle?) {
        super.onSaveInstanceState(bundle)

        bundle?.putParcelable(FilterItem.FILTER_EXTRA, filterItem)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.cards_filter_button -> {
                cards_filter_button.isEnabled = false
                val intent = Intent(this, FilterActivity::class.java)
                if (filterItem != null) {
                    intent.putExtra(FilterItem.FILTER_EXTRA, filterItem)
                }
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
            filterItem = data?.getParcelableExtra(FilterActivity.FILTER_DATA)!!
            adapter.swapData(emptyList())
            setLoading(true)
            if (filterItem!!.isFilterEmpty()) {
                presenter.loadCards()
            } else {
                filterItem = data.getParcelableExtra(FilterActivity.FILTER_DATA)!!
                presenter.loadCardsWithFilters(filterItem!!)
            }
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
        filterItem = null
    }

    companion object {
        const val FILTER_DATA_REQUEST = 19
    }
}
