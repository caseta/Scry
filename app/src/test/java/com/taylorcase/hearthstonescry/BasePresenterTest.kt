package com.taylorcase.hearthstonescry

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.taylorcase.hearthstonescry.utils.SchedulerComposer
import com.taylorcase.hearthstonescry.utils.SchedulerProvider
import io.reactivex.schedulers.Schedulers
import org.junit.Before

open class BasePresenterTest {

    private val mockSchedulerProvider = mock<SchedulerProvider>()
    val mockScheduleComposer: SchedulerComposer = SchedulerComposer(mockSchedulerProvider)

    @Before
    fun setup() {
        doReturn(Schedulers.trampoline()).whenever(mockSchedulerProvider).io()
        doReturn(Schedulers.trampoline()).whenever(mockSchedulerProvider).ui()
        doReturn(Schedulers.trampoline()).whenever(mockSchedulerProvider).computation()
    }
}
