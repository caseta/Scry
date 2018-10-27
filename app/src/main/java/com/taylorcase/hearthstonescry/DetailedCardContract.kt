package com.taylorcase.hearthstonescry

import com.taylorcase.hearthstonescry.base.MvpPresenter
import com.taylorcase.hearthstonescry.base.MvpView
import com.taylorcase.hearthstonescry.model.Card

interface DetailedCardContract {

    interface View : MvpView

    interface Presenter : MvpPresenter<View> {

        fun isCardSaved(card: Card): Boolean

        fun saveCard(card: Card)

        fun removeCard(card: Card)
    }
}
