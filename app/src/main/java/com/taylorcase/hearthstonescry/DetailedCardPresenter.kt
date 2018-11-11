package com.taylorcase.hearthstonescry

import com.taylorcase.hearthstonescry.base.BasePresenter
import com.taylorcase.hearthstonescry.model.Card
import com.taylorcase.hearthstonescry.utils.SharedPreferencesHelper
import javax.inject.Inject

open class DetailedCardPresenter @Inject constructor(private val sharedPreferencesHelper: SharedPreferencesHelper) : BasePresenter<DetailedCardContract.View>(), DetailedCardContract.Presenter {

    lateinit var view: DetailedCardContract.View

    override fun isCardSaved(card: Card) = sharedPreferencesHelper.isCardSaved(card)

    override fun saveCard(card: Card) = sharedPreferencesHelper.saveCard(card)

    override fun removeCard(card: Card) = sharedPreferencesHelper.removeCard(card)
}
