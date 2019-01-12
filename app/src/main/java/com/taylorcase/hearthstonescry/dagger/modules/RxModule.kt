package com.taylorcase.hearthstonescry.dagger.modules

import com.taylorcase.hearthstonescry.utils.AppSchedulerProvider
import com.taylorcase.hearthstonescry.utils.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RxModule {

    @Singleton
    @Provides
    fun createSchedulerProvider(): SchedulerProvider = AppSchedulerProvider
}
