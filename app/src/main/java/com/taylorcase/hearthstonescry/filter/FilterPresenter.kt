package com.taylorcase.hearthstonescry.filter

import com.taylorcase.hearthstonescry.CardRepository
import com.taylorcase.hearthstonescry.base.BasePresenter
import javax.inject.Inject

open class FilterPresenter @Inject constructor(
        private val cardRepository: CardRepository
) : BasePresenter<FilterContract.View>(), FilterContract.Presenter {

}
