package com.taylorcase.hearthstonescry

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.google.firebase.analytics.FirebaseAnalytics
import com.taylorcase.hearthstonescry.dagger.components.AppComponent
import com.taylorcase.hearthstonescry.dagger.components.DaggerAppComponent
import com.taylorcase.hearthstonescry.dagger.modules.AppModule
import com.taylorcase.hearthstonescry.dagger.modules.RoomModule
import com.taylorcase.hearthstonescry.dagger.modules.RxModule
import io.fabric.sdk.android.Fabric
import timber.log.Timber

open class ScryApplication : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Fabric.with(this, Crashlytics())
            FirebaseAnalytics.getInstance(this)
        }

        appComponent = createComponent()
    }

    open fun createComponent(): AppComponent {
        return DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .roomModule(RoomModule(this))
                .rxModule(RxModule())
                .build()
    }

    fun getComponent(): AppComponent {
        return appComponent
    }
}
