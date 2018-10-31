package com.taylorcase.hearthstonescry.selecthero

import com.taylorcase.hearthstonescry.InjectingTest
import kotlinx.android.synthetic.main.activity_select_hero.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric.*
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SelectHeroActivityTest : InjectingTest() {

    private var activity: SelectHeroActivity? = null

    @Test fun testOnCreateSetsUpRecycler() {
        activity = buildActivity(SelectHeroActivity::class.java).create().get()

        assertThat(activity!!.select_hero_recycler).isNotNull()
        assertThat(activity!!.select_hero_recycler.adapter).isEqualTo(mockSelectHeroAdapter)
    }

    @After
    fun destroyActivity() {
        activity!!.finish()
        activity = null
    }
}
