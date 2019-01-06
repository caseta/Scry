package com.taylorcase.hearthstonescry

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import android.net.ConnectivityManager
import com.taylorcase.hearthstonescry.api.HearthstoneApi
import com.taylorcase.hearthstonescry.base.CardsContract
import com.taylorcase.hearthstonescry.dagger.modules.AppModule
import com.taylorcase.hearthstonescry.room.AppDatabase
import com.taylorcase.hearthstonescry.room.CardDao
import com.taylorcase.hearthstonescry.savedcards.SavedCardsContract
import com.taylorcase.hearthstonescry.savedcards.SavedCardsPresenter
import com.taylorcase.hearthstonescry.search.SearchContract
import com.taylorcase.hearthstonescry.search.SearchPresenter
import com.taylorcase.hearthstonescry.selecthero.SelectHeroAdapter
import com.taylorcase.hearthstonescry.selecthero.SelectHeroContract
import com.taylorcase.hearthstonescry.selecthero.SelectHeroPresenter
import com.taylorcase.hearthstonescry.utils.*
import dagger.Module
import dagger.Provides
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.robolectric.RuntimeEnvironment
import javax.inject.Singleton

@Module
class TestAppModule {

    @Provides
    @Singleton
    fun provideContext(): Application = RuntimeEnvironment.application as ScryApplication

    @Provides
    @Singleton
    fun providesHearthstoneApi(): HearthstoneApi = mock(HearthstoneApi::class.java)

    @Provides
    @Singleton
    fun providesAppDatabase(): AppDatabase = mock(AppDatabase::class.java)

    @Provides
    @Singleton
    fun providesConnectivityManager(): ConnectivityManager = mock(ConnectivityManager::class.java)

    @Provides
    @Singleton
    fun providesNetworkManager(): NetworkManager = mock(NetworkManager::class.java)

    @Provides
    @Singleton
    fun providesCardDao(): CardDao = mock(CardDao::class.java)

    @Provides
    @Singleton
    fun providesSplashPresenter(): SplashContract.Presenter = mock(SplashContract.Presenter::class.java)

    @Provides
    @Singleton
    fun providesCardsPresenter(): CardsContract.Presenter = mock(CardsContract.Presenter::class.java)

    @Provides
    @Singleton
    fun providesDetailedCardPresenter(): DetailedCardContract.Presenter = mock(DetailedCardContract.Presenter::class.java)

    @Provides
    @Singleton
    fun providesSavedCardsPresenter(): SavedCardsContract.Presenter = mock(SavedCardsContract.Presenter::class.java)

    @Provides
    @Singleton
    fun providesSearchPresenter(): SearchContract.Presenter = mock(SearchContract.Presenter::class.java)

    @Provides
    @Singleton
    fun providesSelectHeroPresenter(): SelectHeroContract.Presenter = mock(SelectHeroContract.Presenter::class.java)

    @Provides
    @Singleton
    fun providesSharedPreferencesHelper(): SharedPreferencesHelper = mock(SharedPreferencesHelper::class.java)

    @Provides
    @Singleton
    fun providesHeroUtils(): HeroUtils = mock(HeroUtils::class.java)

    @Provides
    @Singleton
    fun providesImageLoader(): ImageLoader = mock(ImageLoader::class.java)

    @Provides
    @Singleton
    fun providesSelectHeroAdapter(): SelectHeroAdapter = mock(SelectHeroAdapter::class.java)

    @Provides
    @Singleton
    fun providesCardsAdapter(): CardsAdapter = mock(CardsAdapter::class.java)

}
