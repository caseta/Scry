package com.taylorcase.hearthstonescry.base

interface MvpPresenter<V : MvpView> {

    fun attach(view: V)

    fun detach()
}