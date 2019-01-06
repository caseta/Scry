package com.taylorcase.hearthstonescry.search

import com.nhaarman.mockito_kotlin.verify
import com.taylorcase.hearthstonescry.*
import com.taylorcase.hearthstonescry.model.Card
import com.taylorcase.hearthstonescry.model.Card.Companion.CARD_EXTRA
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.decor_nav_drawer.*
import kotlinx.android.synthetic.main.include_toolbar_search.*
import org.assertj.android.api.Assertions.*
import org.assertj.core.api.Assertions
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric.*
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.RuntimeEnvironment.*
import org.robolectric.shadows.ShadowApplication
import java.util.*
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
open class SearchActivityTest {

    @Inject lateinit var mockSearchPresenter: SearchContract.Presenter

    private lateinit var activity: SearchActivity

    @Before
    fun setUp() {
        ((RuntimeEnvironment.application as TestScryApplication).getComponent() as TestAppComponent).inject(this)
        activity = buildActivity(SearchActivity::class.java).create().get()
    }

    @Test
    fun testOnCreateCallsPresenterPopulateCardNames() {
        verify(mockSearchPresenter).populateCardNames()
    }

    @Test
    fun testOnCreateSetsLoadingToTrue() {
        assertThat(activity.progress_bar).isVisible
    }

    @Test
    fun testOnCreateActivityPresenterCallsAttach() {
        verify(mockSearchPresenter).attach(activity)
    }

    @Test
    fun testOnCreateSearchInputHasFocus() {
        assertThat(activity.search_input_text).hasFocus()
    }

    @Test
    fun testOnCreateSetsUpRecyclerView() {
        Assertions.assertThat(activity.adapter).isNotNull()
        Assertions.assertThat(activity.search_suggestions_recycler.adapter).isEqualTo(activity.adapter)
        Assertions.assertThat(activity.search_suggestions_recycler.layoutManager).isNotNull()
    }

    @Test
    fun testOnSetCardNamesAddsAllNames() {
        val names = ArrayList<String>()
        names.add("Jimmy")

        activity.setCardNames(names)

        Assertions.assertThat(activity.allNames).contains("Jimmy")
    }

    @Test
    fun testOnSetCardNamesSetsLoadingToFalse() {
        val names = ArrayList<String>()
        names.add("Jimmy")

        activity.setCardNames(names)

        assertThat(activity.progress_bar).isNotVisible
    }

    @Test
    fun testOnSuggestionClickedCallsPresenterPerformSearch() {
        activity.onSuggestionClicked("Suggestion")

        verify(mockSearchPresenter).performSearch("Suggestion")
    }

    @Test
    fun testNavigateToDetailedActivity() {
        val card = Card()

        activity.navigateToProperDetailedCard(card)

        val intent = ShadowApplication.getInstance().nextStartedActivity
        assertThat(intent).isNotNull
        assertThat(intent).hasComponent(application, DetailedCardActivity::class.java).hasExtra(CARD_EXTRA, card)
    }

    @Test
    fun testOnDestroyDetachesPresenter() {
        activity.onDestroy()

        verify(mockSearchPresenter).detach()
    }

    @After
    fun destroyActivity() {
        activity.finish()
    }
}
