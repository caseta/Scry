package com.taylorcase.hearthstonescry.search

import com.taylorcase.hearthstonescry.base.MvpPresenter
import com.taylorcase.hearthstonescry.base.MvpView
import com.taylorcase.hearthstonescry.model.Card
import java.util.ArrayList

interface SearchContract {

    interface View : MvpView {

        fun navigateToProperDetailedCard(card: Card)

        fun setCardNames(names: List<String>)
    }

    interface Presenter : MvpPresenter<View> {

        fun populateCardNames()

        fun performSearch(suggestion: String)
    }

}