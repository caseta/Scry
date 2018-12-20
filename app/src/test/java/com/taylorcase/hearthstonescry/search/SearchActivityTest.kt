package com.taylorcase.hearthstonescry.search

import com.nhaarman.mockito_kotlin.verify
import com.taylorcase.hearthstonescry.DetailedCardActivity
import com.taylorcase.hearthstonescry.InjectingTest
import com.taylorcase.hearthstonescry.model.Card
import com.taylorcase.hearthstonescry.model.Card.CREATOR.CARD_EXTRA
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.decor_nav_drawer.*
import kotlinx.android.synthetic.main.include_toolbar_search.*
import org.assertj.android.api.Assertions.*
import org.assertj.core.api.Assertions
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric.*
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment.*
import org.robolectric.shadows.ShadowApplication
import java.util.*

@RunWith(RobolectricTestRunner::class)
open class SearchActivityTest : InjectingTest() {

    private var activity: SearchActivity? = null

    @Test fun testOnCreateCallsPresenterPopulateCardNames() {
        buildActivity(SearchActivity::class.java).create().get()

        verify(mockSearchPresenter).populateCardNames()
    }

    @Test fun testOnCreateSetsLoadingToTrue() {
        activity = buildActivity(SearchActivity::class.java).create().get()

        assertThat(activity!!.progress_bar).isVisible
    }

    @Test fun testOnCreateActivityPresenterCallsAttach() {
        activity = buildActivity(SearchActivity::class.java).create().get()

        verify(mockSearchPresenter).attach(activity!!)
    }

    @Test fun testOnCreateSearchInputHasFocus() {
        activity = buildActivity(SearchActivity::class.java).create().get()

        assertThat(activity!!.search_input_text).hasFocus()
    }

    @Test fun testOnCreateSetsUpRecyclerView() {
        activity = buildActivity(SearchActivity::class.java).create().get()

        Assertions.assertThat(activity!!.adapter).isNotNull()
        Assertions.assertThat(activity!!.search_suggestions_recycler.adapter).isEqualTo(activity!!.adapter)
        Assertions.assertThat(activity!!.search_suggestions_recycler.layoutManager).isNotNull()
    }

    @Test fun testOnSetCardNamesAddsAllNames() {
        val names = ArrayList<String>()
        names.add("Jimmy")
        activity = buildActivity(SearchActivity::class.java).create().get()

        activity!!.setCardNames(names)

        Assertions.assertThat(activity!!.allNames).contains("Jimmy")
    }

    @Test fun testOnSetCardNamesSetsLoadingToFalse() {
        val names = ArrayList<String>()
        names.add("Jimmy")
        activity = buildActivity(SearchActivity::class.java).create().get()

        activity!!.setCardNames(names)

        assertThat(activity!!.progress_bar).isNotVisible
    }

    @Test fun testOnSuggestionClickedCallsPresenterPerformSearch() {
        activity = buildActivity(SearchActivity::class.java).create().get()

        activity!!.onSuggestionClicked("Suggestion")

        verify(mockSearchPresenter).performSearch("Suggestion")
    }

    @Test fun testNavigateToDetailedActivity() {
        val card = Card()
        activity = buildActivity(SearchActivity::class.java).create().get()

        activity!!.navigateToProperDetailedCard(card)

        val intent = ShadowApplication.getInstance().nextStartedActivity
        assertThat(intent).isNotNull
        assertThat(intent).hasComponent(application, DetailedCardActivity::class.java).hasExtra(CARD_EXTRA, card)
    }

    @Test fun testOnDestroyDetachesPresenter() {
        activity = buildActivity(SearchActivity::class.java).create().get()

        activity!!.onDestroy()

        verify(mockSearchPresenter).detach()
    }

    @After
    fun destroyActivity() {
        activity?.finish()
        activity = null
    }
}
