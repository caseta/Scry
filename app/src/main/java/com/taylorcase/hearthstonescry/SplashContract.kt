package com.taylorcase.hearthstonescry

import com.taylorcase.hearthstonescry.base.MvpPresenter
import com.taylorcase.hearthstonescry.base.MvpView

interface SplashContract {

    interface View : MvpView {

        fun cardsLoadedSuccessfully()
    }

    interface Presenter : MvpPresenter<View> {

        fun loadCards()

    }
}
