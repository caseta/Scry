package com.taylorcase.hearthstonescry

import android.app.Application
import android.content.Context
import com.taylorcase.hearthstonescry.api.HearthstoneApi
import com.taylorcase.hearthstonescry.base.CardsContract
import com.taylorcase.hearthstonescry.dagger.modules.AppModule
import com.taylorcase.hearthstonescry.savedcards.SavedCardsContract
import com.taylorcase.hearthstonescry.savedcards.SavedCardsPresenter
import com.taylorcase.hearthstonescry.search.SearchContract
import com.taylorcase.hearthstonescry.search.SearchPresenter
import com.taylorcase.hearthstonescry.selecthero.SelectHeroAdapter
import com.taylorcase.hearthstonescry.selecthero.SelectHeroContract
import com.taylorcase.hearthstonescry.selecthero.SelectHeroPresenter
import com.taylorcase.hearthstonescry.utils.GlideImageLoader
import com.taylorcase.hearthstonescry.utils.HeroUtils
import com.taylorcase.hearthstonescry.utils.ImageLoader
import com.taylorcase.hearthstonescry.utils.SharedPreferencesHelper
import dagger.Module
import dagger.Provides
import org.mockito.Mockito.*
import org.robolectric.RuntimeEnvironment
import javax.inject.Singleton

@Module
class TestAppModule(application: Application) : AppModule(application) {

    private lateinit var mockSplashPresenter: SplashContract.Presenter
    private lateinit var mockCardsPresenter: CardsContract.Presenter
    private lateinit var mockSearchPresenter: SearchContract.Presenter
    private lateinit var mockSavedCardsPresenter: SavedCardsContract.Presenter
    private lateinit var mockDetailedCardPresenter: DetailedCardContract.Presenter
    private lateinit var mockSelectHeroPresenter: SelectHeroContract.Presenter
    private lateinit var mockSelectHeroAdapter: SelectHeroAdapter
    private lateinit var mockCardsAdapter: CardsAdapter
    private lateinit var mockSharedPreferencesHelper: SharedPreferencesHelper
    private lateinit var mockHeroUtils: HeroUtils
    private lateinit var mockImageLoader: ImageLoader

    @Provides
    @Singleton
    override
    fun provideContext(): Application = RuntimeEnvironment.application as ScryApplication

    @Provides
    @Singleton
    override fun providesHearthstoneApi(): HearthstoneApi = mock(HearthstoneApi::class.java)

    @Provides
    @Singleton
    override fun providesSplashPresenter(presenter: SplashPresenter): SplashContract.Presenter = mockSplashPresenter

    fun getMockedSplashPresenter(): SplashContract.Presenter {
        mockSplashPresenter = mock(SplashContract.Presenter::class.java)
        return mockSplashPresenter
    }

    @Provides
    @Singleton
    override fun providesCardsPresenter(presenter: CardsPresenter): CardsContract.Presenter = mockCardsPresenter

    fun getMockedCardsPresenter(): CardsContract.Presenter {
        mockCardsPresenter = mock(CardsContract.Presenter::class.java)
        return mockCardsPresenter
    }

    @Provides
    @Singleton
    override fun providesDetailedCardPresenter(presenter: DetailedCardPresenter): DetailedCardContract.Presenter = mockDetailedCardPresenter

    fun getMockedDetailedCardPresenter(): DetailedCardContract.Presenter {
        mockDetailedCardPresenter = mock(DetailedCardContract.Presenter::class.java)
        return mockDetailedCardPresenter
    }

    @Provides
    @Singleton
    override fun providesSavedCardsPresenter(presenter: SavedCardsPresenter): SavedCardsContract.Presenter = mockSavedCardsPresenter

    fun getMockedSavedCardsPresenter(): SavedCardsContract.Presenter {
        mockSavedCardsPresenter = mock(SavedCardsContract.Presenter::class.java)
        return mockSavedCardsPresenter
    }

    @Provides
    @Singleton
    override fun providesSearchPresenter(presenter: SearchPresenter): SearchContract.Presenter = mockSearchPresenter

    fun getMockedSearchPresenter(): SearchContract.Presenter {
        mockSearchPresenter = mock(SearchContract.Presenter::class.java)
        return mockSearchPresenter
    }

    @Provides
    @Singleton
    override fun providesSelectHeroPresenter(presenter: SelectHeroPresenter): SelectHeroContract.Presenter = mockSelectHeroPresenter

    fun getMockedSelectHeroPresenter(): SelectHeroContract.Presenter {
        mockSelectHeroPresenter = mock(SelectHeroContract.Presenter::class.java)
        return mockSelectHeroPresenter
    }

    @Provides
    @Singleton
    override fun providesSharedPreferencesHelper(application: Application): SharedPreferencesHelper = mockSharedPreferencesHelper

    fun getMockedSharedPreferencesHelper(): SharedPreferencesHelper {
        mockSharedPreferencesHelper = mock(SharedPreferencesHelper::class.java)
        return mockSharedPreferencesHelper
    }

    @Provides
    @Singleton
    override fun providesHeroUtils(sharedPreferencesHelper: SharedPreferencesHelper, application: Application): HeroUtils = mockHeroUtils

    fun getMockedHeroUtils(): HeroUtils {
        mockHeroUtils = mock(HeroUtils::class.java)
        return mockHeroUtils
    }

    @Provides
    @Singleton
    override fun providesImageLoader(): ImageLoader = mockImageLoader

    fun getMockedImageLoader(): ImageLoader {
        mockImageLoader = mock(ImageLoader::class.java)
        return mockImageLoader
    }

    @Provides
    @Singleton
    override fun providesSelectHeroAdapter(imageLoader: ImageLoader, context: Application, heroUtils: HeroUtils): SelectHeroAdapter = mockSelectHeroAdapter

    fun getMockedSelectHeroAdapter(): SelectHeroAdapter {
        mockSelectHeroAdapter = mock(SelectHeroAdapter::class.java)
        return mockSelectHeroAdapter
    }

    @Provides
    @Singleton
    override fun providesCardsAdapter(imageLoader: ImageLoader): CardsAdapter = mockCardsAdapter

    fun getMockedCardsAdapter(): CardsAdapter {
        mockCardsAdapter = mock(CardsAdapter::class.java)
        return mockCardsAdapter
    }
}
