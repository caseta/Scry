package com.taylorcase.hearthstonescry.base

import com.nhaarman.mockito_kotlin.verify
import com.taylorcase.hearthstonescry.CardsActivity
import com.taylorcase.hearthstonescry.TestAppComponent
import com.taylorcase.hearthstonescry.TestScryApplication
import com.taylorcase.hearthstonescry.utils.SharedPreferencesHelper
import org.assertj.core.api.Assertions
import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric.buildActivity
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
class BaseActivityTest {

    @Inject lateinit var mockSharedPreferencesHelper: SharedPreferencesHelper

    private lateinit var activity: CardsActivity

    @Before
    fun setUp() {
        ((RuntimeEnvironment.application as TestScryApplication).getComponent() as TestAppComponent).inject(this)
        activity = buildActivity(CardsActivity::class.java).create().get()
    }

    @Ignore
    @Test
    fun testOnCreateGetsTheme() {
        verify(mockSharedPreferencesHelper).getTheme()
    }

    @Test
    fun testOnCreateCreatesNavDrawerFragmentWhenNull() {
        Assertions.assertThat(activity.navDrawerFragment).isNotNull()
    }

    @After
    fun destroyActivity() {
        activity.finish()
    }
}
