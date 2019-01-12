package com.taylorcase.hearthstonescry

import com.taylorcase.hearthstonescry.base.BasePresenter
import com.taylorcase.hearthstonescry.utils.SchedulerComposer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class SplashPresenter @Inject constructor(
        private val cardRepository: CardRepository,
        private val schedulerComposer: SchedulerComposer
) : BasePresenter<SplashContract.View>(), SplashContract.Presenter {

    lateinit var view: SplashContract.View

    override fun loadCards() {
        view = getView() as SplashContract.View
        if (cardRepository.refreshCards()) {
            bind(cardRepository.observeAllCardsWithApi()
                    .compose(schedulerComposer.singleIoComposer())
                    .subscribe({ view.cardsLoadedSuccessfully() }, { showError(it) }))
        } else {
            view.cardsLoadedSuccessfully()
        }
    }
}
