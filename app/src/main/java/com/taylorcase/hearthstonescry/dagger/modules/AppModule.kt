package com.taylorcase.hearthstonescry.dagger.modules

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.taylorcase.hearthstonescry.*
import com.taylorcase.hearthstonescry.savedcards.SavedCardsContract
import com.taylorcase.hearthstonescry.savedcards.SavedCardsPresenter
import com.taylorcase.hearthstonescry.api.RetroClient
import com.taylorcase.hearthstonescry.api.HearthstoneApi
import com.taylorcase.hearthstonescry.base.CardsContract
import com.taylorcase.hearthstonescry.search.SearchContract
import com.taylorcase.hearthstonescry.search.SearchPresenter
import com.taylorcase.hearthstonescry.selecthero.SelectHeroContract
import com.taylorcase.hearthstonescry.selecthero.SelectHeroPresenter
import com.taylorcase.hearthstonescry.utils.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class AppModule(private val application: Application) {

    @Provides
    @Singleton
    open fun provideContext(): Application = application

    @Provides
    @Singleton
    internal open fun providesHearthstoneApi(): HearthstoneApi = RetroClient.apiService

    @Provides
    @Singleton
    internal open fun providesImageLoader(): ImageLoader = GlideImageLoader()

    @Provides
    @Singleton
    internal open fun providesNetworkManager(): NetworkManager = NetworkManager(application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)

    @Provides
    @Singleton
    internal open fun providesSplashPresenter(presenter: SplashPresenter): SplashContract.Presenter = presenter

    @Provides
    @Singleton
    internal open fun providesCardsPresenter(presenter: CardsPresenter): CardsContract.Presenter = presenter

    @Provides
    @Singleton
    internal open fun providesDetailedCardPresenter(presenter: DetailedCardPresenter): DetailedCardContract.Presenter = presenter

    @Provides
    @Singleton
    internal open fun providesSavedCardsPresenter(presenter: SavedCardsPresenter): SavedCardsContract.Presenter = presenter

    @Provides
    @Singleton
    internal open fun providesSearchPresenter(presenter: SearchPresenter): SearchContract.Presenter = presenter

    @Provides
    @Singleton
    internal open fun providesSelectHeroPresenter(presenter: SelectHeroPresenter): SelectHeroContract.Presenter = presenter

    @Provides
    @Singleton
    internal open fun providesSharedPreferencesHelper(application: Application): SharedPreferencesHelper = SharedPreferencesHelper(application)

}
