package com.taylorcase.hearthstonescry

import com.taylorcase.hearthstonescry.base.BasePresenter
import com.taylorcase.hearthstonescry.base.CardsContract
import com.taylorcase.hearthstonescry.model.Card
import com.taylorcase.hearthstonescry.model.FilterItem
import com.taylorcase.hearthstonescry.model.enums.Hero
import com.taylorcase.hearthstonescry.model.enums.Rarity
import com.taylorcase.hearthstonescry.model.enums.Sets
import com.taylorcase.hearthstonescry.utils.HeroUtils
import com.taylorcase.hearthstonescry.utils.NetworkManager
import com.taylorcase.hearthstonescry.utils.SharedPreferencesHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class CardsPresenter @Inject constructor(
        private val heroUtils: HeroUtils,
        private val cardRepository: CardRepository,
        private val networkManager: NetworkManager,
        private val sharedPreferencesHelper: SharedPreferencesHelper
) : BasePresenter<CardsContract.View>(), CardsContract.Presenter {

    val view: CardsContract.View?
        get() = getView() as? CardsContract.View

    override fun shouldAskToRateApp(): Boolean = sharedPreferencesHelper.shouldAskUserToRateApp()

    override fun userWasAskedToRateApp() = sharedPreferencesHelper.userWasAskedToRateApp()

    override fun loadCards() {
        bind(cardRepository.observeCardsWithHero(heroUtils.getFavoriteHero())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::displayCards) { showError(it) })
    }

    override fun loadCardsWithFilters(filterItem: FilterItem) {
        bind(cardRepository.observeCardsWithFilters(filterItem)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::displayCards) { showError(it) })
    }

    override fun refreshAllCards() {
        if (networkManager.isConnected()) {
            bind(cardRepository.observeAllCardsWithApi()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).doOnError { showError(it) }
                    .subscribe(Consumer { resetSuccess() }))
        } else {
            view?.displayNetworkError()
        }
    }

    // TODO: Combine this with above
    private fun resetSuccess() {
        bind(cardRepository.observeCardsWithHero(heroUtils.getFavoriteHero())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::displayCards) { showError(it) })
    }

    private fun displayCards(cards: List<Card>) {
        view?.displayCards(cards)
    }
}
