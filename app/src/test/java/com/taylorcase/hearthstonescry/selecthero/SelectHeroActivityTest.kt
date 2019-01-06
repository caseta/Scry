package com.taylorcase.hearthstonescry.selecthero

import com.taylorcase.hearthstonescry.TestAppComponent
import com.taylorcase.hearthstonescry.TestScryApplication
import kotlinx.android.synthetic.main.activity_select_hero.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric.*
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
class SelectHeroActivityTest {

    @Inject lateinit var mockSelectHeroAdapter: SelectHeroAdapter

    private lateinit var activity: SelectHeroActivity

    @Before
    fun setUp() {
        ((RuntimeEnvironment.application as TestScryApplication).getComponent() as TestAppComponent).inject(this)
        activity = buildActivity(SelectHeroActivity::class.java).create().get()
    }

    @Test
    fun testOnCreateSetsUpRecycler() {
        assertThat(activity.select_hero_recycler).isNotNull()
        assertThat(activity.select_hero_recycler.adapter).isEqualTo(mockSelectHeroAdapter)
    }

    @After
    fun destroyActivity() {
        activity.finish()
    }
}
