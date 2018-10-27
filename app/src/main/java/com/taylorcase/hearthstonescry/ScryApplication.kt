package com.taylorcase.hearthstonescry

import android.app.Application
import com.taylorcase.hearthstonescry.dagger.components.AppComponent
import com.taylorcase.hearthstonescry.dagger.components.DaggerAppComponent
import com.taylorcase.hearthstonescry.dagger.modules.AppModule
import com.taylorcase.hearthstonescry.dagger.modules.RoomModule
import timber.log.Timber

open class ScryApplication : Application() {

    private var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()

        if (!BuildConfig.DEBUG) {
            // TODO: Set up Fabric
            //Fabric.with(this, new Crashlytics());
        } else {
            Timber.plant(Timber.DebugTree())
        }
        initComponent()
    }

    open fun getAppModule() : AppModule {
        return AppModule(this)
    }

    fun initComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(getAppModule())
                .roomModule(RoomModule(this))
                .build()
    }

    fun getAppComponent(): AppComponent? {
        return appComponent
    }
}
