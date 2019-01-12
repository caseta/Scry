package com.taylorcase.hearthstonescry

import com.taylorcase.hearthstonescry.base.BasePresenter
import com.taylorcase.hearthstonescry.base.CardsContract
import com.taylorcase.hearthstonescry.model.Card
import com.taylorcase.hearthstonescry.model.FilterItem
import com.taylorcase.hearthstonescry.model.enums.Hero
import com.taylorcase.hearthstonescry.model.enums.Rarity
import com.taylorcase.hearthstonescry.model.enums.Sets
import com.taylorcase.hearthstonescry.utils.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class CardsPresenter @Inject constructor(
        private val heroUtils: HeroUtils,
        private val cardRepository: CardRepository,
        private val networkManager: NetworkManager,
        private val sharedPreferencesHelper: SharedPreferencesHelper,
        private val schedulerComposer: SchedulerComposer
) : BasePresenter<CardsContract.View>(), CardsContract.Presenter {

    val view: CardsContract.View?
        get() = getView() as? CardsContract.View

    override fun shouldAskToRateApp(): Boolean = sharedPreferencesHelper.shouldAskUserToRateApp()

    override fun userWasAskedToRateApp() = sharedPreferencesHelper.userWasAskedToRateApp()

    override fun loadCards() {
        bind(cardRepository.observeCardsWithHero(heroUtils.getFavoriteHero())
                .compose(schedulerComposer.singleIoComposer())
                .subscribe(::displayCards) { showError(it) })
    }

    override fun loadCardsWithFilters(filterItem: FilterItem) {
        bind(cardRepository.observeCardsWithFilters(filterItem)
                .compose(schedulerComposer.singleComputationComposer())
                .subscribe(::displayCards) { showError(it) })
    }

    override fun refreshAllCards() {
        if (networkManager.isConnected()) {
            bind(cardRepository.observeAllCardsWithApi()
                    .compose(schedulerComposer.singleIoComposer())
                    .subscribe({ resetSuccess() }, { showError(it) }))

        } else {
            view?.displayNetworkError()
        }
    }

    private fun resetSuccess() {
        bind(cardRepository.observeCardsWithHero(heroUtils.getFavoriteHero())
                .compose(schedulerComposer.singleIoComposer())
                .subscribe(::displayCards) { showError(it) })
    }

    private fun displayCards(cards: List<Card>) {
        view?.displayCards(cards)
    }
}
