package com.taylorcase.hearthstonescry.selecthero

import com.taylorcase.hearthstonescry.base.MvpPresenter
import com.taylorcase.hearthstonescry.base.MvpView

interface SelectHeroContract {

    interface View : MvpView {

        fun finishAndStartCardsActivity()

    }

    interface Presenter : MvpPresenter<View> {

        fun getHeroAndTheme(selectedHero: Int)

    }

}
