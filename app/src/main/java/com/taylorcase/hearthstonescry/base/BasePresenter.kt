package com.taylorcase.hearthstonescry.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber
import java.lang.ref.WeakReference

abstract class BasePresenter<V : MvpView> : MvpPresenter<V> {

    var viewReference: WeakReference<Any>? = null

    private var compositeDisposable = CompositeDisposable()

    override fun attach(view: V) {
        viewReference = WeakReference(view)
    }

    override fun detach() {
        compositeDisposable.clear()
        viewReference?.clear()
        viewReference = null
    }

    fun getView(): Any? {
        return if (viewReference == null) null else viewReference?.get()
    }

    protected fun showError(error: Throwable) {
        Timber.e(error)
        val view = getView()
        if (view != null && view is MvpView) view.showError()
    }

    fun bind(disposable: Disposable?) {
        compositeDisposable.addAll(disposable)
    }
}
