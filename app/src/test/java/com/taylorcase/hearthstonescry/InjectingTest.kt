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

    var mockSplashPresenter: SplashContract.Presenter? = null
    var mockCardsPresenter: CardsContract.Presenter? = null
    var mockSearchPresenter: SearchContract.Presenter? = null
    var mockSavedCardsPresenter: SavedCardsContract.Presenter? = null
    var mockDetailedCardPresenter: DetailedCardContract.Presenter? = null
    var mockSelectHeroPresenter: SelectHeroContract.Presenter? = null

    var mockSharedPreferencesHelper: SharedPreferencesHelper? = null
    var mockSelectHeroAdapter: SelectHeroAdapter? = null
    var mockCardsAdapter: CardsAdapter? = null
    var mockHeroUtils: HeroUtils? = null
    var mockImageLoader: ImageLoader? = null

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
