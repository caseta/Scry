package com.taylorcase.hearthstonescry

import com.taylorcase.hearthstonescry.dagger.modules.AppModule

open class TestScryApplication : ScryApplication() {

    private var appModule: AppModule? = null

    override fun getAppModule() : AppModule {
        if (appModule == null) {
            return super.getAppModule()
        }
        return appModule as AppModule
    }

    fun setApplicationModule(appModule: AppModule) {
        this.appModule = appModule
        initComponent()
    }
}
