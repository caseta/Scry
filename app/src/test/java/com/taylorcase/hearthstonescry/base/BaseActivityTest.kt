package com.taylorcase.hearthstonescry.base

import com.nhaarman.mockito_kotlin.verify
import com.taylorcase.hearthstonescry.CardsActivity
import com.taylorcase.hearthstonescry.InjectingTest
import org.assertj.core.api.Assertions
import org.junit.After
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric.buildActivity
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class BaseActivityTest : InjectingTest() {

    private var activity: CardsActivity? = null

    @Ignore
    @Test fun testOnCreateGetsTheme() {
        buildActivity(CardsActivity::class.java).create().get()

        verify(mockSharedPreferencesHelper).getTheme()
    }

    @Test fun testOnCreateCreatesNavDrawerFragmentWhenNull() {
        activity = buildActivity(CardsActivity::class.java).create().get()

        Assertions.assertThat(activity!!.navDrawerFragment).isNotNull()
    }

    @After
    fun destroyActivity() {
        activity!!.finish()
        activity = null
    }
}
