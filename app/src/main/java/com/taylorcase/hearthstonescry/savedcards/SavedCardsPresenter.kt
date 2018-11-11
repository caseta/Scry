package com.taylorcase.hearthstonescry.savedcards

import com.taylorcase.hearthstonescry.CardRepository
import com.taylorcase.hearthstonescry.base.BasePresenter
import com.taylorcase.hearthstonescry.model.Card
import com.taylorcase.hearthstonescry.utils.SharedPreferencesHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class SavedCardsPresenter @Inject constructor(private val sharedPreferencesHelper: SharedPreferencesHelper, private val cardRepository: CardRepository) : BasePresenter<SavedCardsContract.View>(), SavedCardsContract.Presenter {

    lateinit var view: SavedCardsContract.View

    override fun getSavedCardCount() = sharedPreferencesHelper.getSavedCardsCount()

    override fun loadSavedCards() {
        bind(cardRepository.observeAllCards()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::displaySavedCards) { showError(it) })
    }

    private fun displaySavedCards(cards: List<Card>) {
        view = getView() as SavedCardsContract.View
        val savedCards = sharedPreferencesHelper.getSavedCards(cards)
        view.displayCards(savedCards)
    }
}
