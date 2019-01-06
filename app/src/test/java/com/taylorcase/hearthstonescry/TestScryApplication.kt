package com.taylorcase.hearthstonescry

import com.taylorcase.hearthstonescry.dagger.components.AppComponent

open class TestScryApplication : ScryApplication() {

    override fun createComponent() : AppComponent {
        return DaggerTestAppComponent.builder()
                .testAppModule(TestAppModule())
                .build()
    }
}
