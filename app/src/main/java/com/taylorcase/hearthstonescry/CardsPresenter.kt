package com.taylorcase.hearthstonescry

import com.taylorcase.hearthstonescry.base.BasePresenter
import com.taylorcase.hearthstonescry.base.CardsContract
import com.taylorcase.hearthstonescry.model.Card
import com.taylorcase.hearthstonescry.utils.HeroUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class CardsPresenter @Inject constructor(private val heroUtils: HeroUtils, private val cardRepository: CardRepository) : BasePresenter<CardsContract.View>(), CardsContract.Presenter {

    val view: CardsContract.View?
        get() = getView() as? CardsContract.View

    override fun loadCards() {
        bind(cardRepository.observeCardsWithHero(heroUtils.getFavoriteHero())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::displayCards) { showError(it) })
    }

    override fun refreshAllCards() {
        bind(cardRepository.observeAllCardsWithApi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).doOnError { showError(it) }
                .subscribe(Consumer { resetSuccess() }))
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
