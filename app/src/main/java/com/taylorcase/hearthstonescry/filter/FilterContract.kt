package com.taylorcase.hearthstonescry.filter

import com.taylorcase.hearthstonescry.base.MvpPresenter
import com.taylorcase.hearthstonescry.base.MvpView

interface FilterContract {

    interface View : MvpView

    interface Presenter : MvpPresenter<View> {

    }
}
