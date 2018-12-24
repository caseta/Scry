package com.taylorcase.hearthstonescry

import android.content.Intent
import com.nhaarman.mockito_kotlin.*
import com.taylorcase.hearthstonescry.model.Card
import com.taylorcase.hearthstonescry.model.Card.Companion.CARD_EXTRA
import com.taylorcase.hearthstonescry.utils.makeGone
import com.taylorcase.hearthstonescry.utils.makeVisible
import kotlinx.android.synthetic.main.activity_detailed_card.*
import org.assertj.android.api.Assertions
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.Robolectric.*
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment.*

@RunWith(RobolectricTestRunner::class)
open class DetailedCardActivityTest : InjectingTest() {

    private lateinit var activity: DetailedCardActivity

    @Test fun testOnCreateActivityPresenterCallsAttach() {
        activity = demandActivityWithCard(Card())

        verify(mockDetailedCardPresenter).attach(activity)
    }

    @Test fun testOnCreateSetsIsCardSavedFromPresenter() {
        val card = Card()
        activity = demandActivityWithCard(card)

        verify(mockDetailedCardPresenter).isCardSaved(card)
    }

    @Test fun testOnCreatePopulatesWhiteHearts() {
        val card = Card()
        activity = demandActivityWithCard(card)
        doReturn(true).whenever(mockHeroUtils)?.shouldAssetsBeWhite()

        verify(mockHeroUtils, times(2)).shouldAssetsBeWhite()
        Assertions.assertThat(activity.heart).isEqualTo(application.resources.getDrawable(R.drawable.ic_favorite_border_white_24dp))
        Assertions.assertThat(activity.heartFilled).isEqualTo(application.resources.getDrawable(R.drawable.ic_favorite_white_24dp))
    }

    @Test fun testOnCreatePopulatesBlackHearts() {
        val card = Card()
        activity = demandActivityWithCard(card)

        verify(mockHeroUtils, times(2)).shouldAssetsBeWhite()
        Assertions.assertThat(activity.heart).isEqualTo(application.resources.getDrawable(R.drawable.ic_favorite_border_black_24dp))
        Assertions.assertThat(activity.heartFilled).isEqualTo(application.resources.getDrawable(R.drawable.ic_favorite_black_24dp))
    }

    @Test fun testPopulateValuesSetsFlavorText() {
        val card = Card()
        card.flavor = "Flavor"
        activity = demandActivityWithCard(card)

        Assertions.assertThat(activity.card_flavor_text).isVisible
        org.assertj.core.api.Assertions.assertThat(activity.card_flavor_text.text.toString()).isEqualTo("Flavor")
    }

    @Test fun testPopulateValuesEmptyFlavorTextIsGone() {
        val card = Card()
        activity = demandActivityWithCard(card)

        Assertions.assertThat(activity.card_flavor_text).isGone
    }

    @Test fun testOnClickCardIsSavedCallsPresenterRemove() {
        val card = Card()
        doReturn(true).whenever(mockDetailedCardPresenter)?.isCardSaved(card)
        activity = demandActivityWithCard(card)

        activity.onClick(activity.card_fab)

        verify(mockDetailedCardPresenter).removeCard(card)
        verify(mockDetailedCardPresenter).isCardSaved(card)
    }

    @Test fun testOnClickCardIsNotSavedCallsPresenterSaveCard() {
        val card = Card()
        activity = demandActivityWithCard(card)

        activity.onClick(activity.card_fab)

        verify(mockDetailedCardPresenter).saveCard(card)
        verify(mockDetailedCardPresenter).isCardSaved(card)
    }

    @Test fun testOnBackPressedSlideAnimationDownFires() {
        val card = Card()
        activity = demandActivityWithCard(card)
        activity.card_text_container.makeGone()

        activity.onBackPressed()

        Assertions.assertThat(activity.card_text_container).isVisible
    }

    @Test fun testOnBackPressedSlideAnimationUpFires() {
        val card = Card()
        activity = demandActivityWithCard(card)
        activity.card_text_container.makeVisible()

        activity.onBackPressed()

        Assertions.assertThat(activity.card_text_container).isGone
    }

    @Test fun testOnBackPressedSetsResultAndFinishedAfterTransition() {
        val card = Card()
        activity = demandActivityWithCard(card)
        val spy = Mockito.spy(activity)

        spy!!.onBackPressed()

        verify(spy).finishAfterTransition()
    }

    @Test fun testOnDestroyDetachesPresenter() {
        activity = demandActivityWithCard(Card())

        activity.onDestroy()

        verify(mockDetailedCardPresenter).detach()
    }

    private fun demandActivityWithCard(card: Card): DetailedCardActivity {
        val intent = Intent().putExtra(CARD_EXTRA, card)
        return buildActivity(DetailedCardActivity::class.java, intent).create().get()
    }

    @After
    fun destroyActivity() {
        activity.finish()
    }
}