package com.taylorcase.hearthstonescry

import com.taylorcase.hearthstonescry.base.CardsContract
import com.taylorcase.hearthstonescry.savedcards.SavedCardsContract
import com.taylorcase.hearthstonescry.search.SearchContract
import com.taylorcase.hearthstonescry.selecthero.SelectHeroAdapter
import com.taylorcase.hearthstonescry.selecthero.SelectHeroContract
import com.taylorcase.hearthstonescry.utils.HeroUtils
import com.taylorcase.hearthstonescry.utils.ImageLoader
import com.taylorcase.hearthstonescry.utils.SharedPreferencesHelper
import org.junit.After
import org.junit.Before
import org.robolectric.RuntimeEnvironment
import org.mockito.Mockito.validateMockitoUsage

open class InjectingTest {

    lateinit var mockSplashPresenter: SplashContract.Presenter
    lateinit var mockCardsPresenter: CardsContract.Presenter
    lateinit var mockSearchPresenter: SearchContract.Presenter
    lateinit var mockSavedCardsPresenter: SavedCardsContract.Presenter
    lateinit var mockDetailedCardPresenter: DetailedCardContract.Presenter
    lateinit var mockSelectHeroPresenter: SelectHeroContract.Presenter

    lateinit var mockSharedPreferencesHelper: SharedPreferencesHelper
    lateinit var mockSelectHeroAdapter: SelectHeroAdapter
    lateinit var mockCardsAdapter: CardsAdapter
    lateinit var mockHeroUtils: HeroUtils
    lateinit var mockImageLoader: ImageLoader

    @Before
    open fun setup() {
        val application = RuntimeEnvironment.application as TestScryApplication
        val testModule = TestAppModule(application)
        application.setApplicationModule(testModule)
        mockSplashPresenter = testModule.getMockedSplashPresenter()
        mockCardsPresenter = testModule.getMockedCardsPresenter()
        mockSearchPresenter = testModule.getMockedSearchPresenter()
        mockSavedCardsPresenter = testModule.getMockedSavedCardsPresenter()
        mockDetailedCardPresenter = testModule.getMockedDetailedCardPresenter()
        mockSelectHeroPresenter = testModule.getMockedSelectHeroPresenter()

        mockSharedPreferencesHelper = testModule.getMockedSharedPreferencesHelper()
        mockSelectHeroAdapter = testModule.getMockedSelectHeroAdapter()
        mockCardsAdapter = testModule.getMockedCardsAdapter()
        mockHeroUtils = testModule.getMockedHeroUtils()
        mockImageLoader = testModule.getMockedImageLoader()
    }

    @After
    fun validate() {
        validateMockitoUsage()
    }
}
