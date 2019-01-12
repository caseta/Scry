package com.taylorcase.hearthstonescry.utils

import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer
import javax.inject.Inject

class SchedulerComposer @Inject constructor(
        private val schedulerProvider: SchedulerProvider
) {
    private val singleIoComposer = SingleTransformer<Any, Any> {
        it.subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.ui())
    }

    private val singleComputationComposer = SingleTransformer<Any, Any> {
        it.subscribeOn(schedulerProvider.computation()).observeOn(schedulerProvider.ui())
    }

    private val observableIoComposer = ObservableTransformer<Any, Any> {
        it.subscribeOn(schedulerProvider.computation()).observeOn(schedulerProvider.ui())
    }

    fun <I> singleIoComposer(): SingleTransformer<I, I> = singleIoComposer as SingleTransformer<I, I>

    fun <I> singleComputationComposer(): SingleTransformer<I, I> = singleComputationComposer as SingleTransformer<I, I>

    fun <I> observableIoComposer(): ObservableTransformer<I, I> = observableIoComposer as ObservableTransformer<I, I>
}
