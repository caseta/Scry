package com.taylorcase.hearthstonescry.selecthero

import android.content.Intent
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.taylorcase.hearthstonescry.InjectingTest
import com.taylorcase.hearthstonescry.R
import com.taylorcase.hearthstonescry.model.enums.Hero
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric.*
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
open class DetailedSelectHeroActivityTest : InjectingTest() {

    private lateinit var activity: DetailedSelectHeroActivity

    @Test
    fun testOnCreateActivityPresenterCallsAttach() {
        demandActivity()

        verify(mockSelectHeroPresenter).attach(activity)
        verifyDemands()
    }

    @Test
    fun testOnDestroyDetachesPresenter() {
        demandActivity()

        activity.onDestroy()

        verify(mockSelectHeroPresenter).detach()
        verifyDemands()
    }

    private fun demandActivity() {
        doReturn(Hero.WARLOCK).whenever(mockHeroUtils)!!.getHeroForString(warlock)
        doReturn(R.color.white).whenever(mockHeroUtils)!!.getCurrentAssetsColor()
        activity = buildActivity(DetailedSelectHeroActivity::class.java, Intent().putExtra(hero, warlock)).create().get()
    }

    private fun verifyDemands() {
        verify(mockHeroUtils).getHeroForString(warlock)
        verify(mockHeroUtils).getCurrentAssetsColor()
    }

    @After
    fun destroyActivity() {
        activity.finish()
    }

    companion object {
        private const val warlock = "Warlock"
        private const val hero = "hero"
    }
}
