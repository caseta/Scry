package com.taylorcase.hearthstonescry.search

import com.taylorcase.hearthstonescry.CardRepository
import com.taylorcase.hearthstonescry.base.BasePresenter
import com.taylorcase.hearthstonescry.model.Card
import com.taylorcase.hearthstonescry.utils.SchedulerComposer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class SearchPresenter @Inject constructor(
        private val cardRepository: CardRepository,
        private val schedulerComposer: SchedulerComposer
) : BasePresenter<SearchContract.View>(), SearchContract.Presenter {

    lateinit var view: SearchContract.View

    override fun populateCardNames() {
        bind(cardRepository.observeAllCardNames()
                .compose(schedulerComposer.singleIoComposer())
                .subscribe({ populateNames(it) }, { showError(it) }))
    }

    private fun populateNames(names: List<String>) {
        view = getView() as SearchContract.View
        view.setCardNames(names)
    }

    override fun performSearch(suggestion: String) {
        bind(cardRepository.observeCard(suggestion)
                .compose(schedulerComposer.observableIoComposer())
                .subscribe({ navigateToProperDetailedCard(it) }, { showError(it) }))
    }

    private fun navigateToProperDetailedCard(cards: List<Card>) {
        if (cards.isNotEmpty()) {
            view = getView() as SearchContract.View
            view.navigateToProperDetailedCard(cards.first())
        }
    }
}
