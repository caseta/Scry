package com.taylorcase.hearthstonescry

import kotlinx.android.synthetic.main.dialog_review_app.*
import org.assertj.android.api.Assertions.*
import org.assertj.core.api.Assertions.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ReviewAppDialogTest : FragmentTest() {

    @Test
    fun testYesHasOnCLickListener() {
        val fragment = startFragment(ReviewAppDialog())

        assertThat(fragment.app_review_yes.hasOnClickListeners())
    }

    @Test
    fun testNahhhHasOnCLickListener() {
        val fragment = startFragment(ReviewAppDialog())

        assertThat(fragment.app_review_nahhh.hasOnClickListeners())
    }

    @Test
    fun testRateHasOnCLickListener() {
        val fragment = startFragment(ReviewAppDialog())

        assertThat(fragment.app_review_rate.hasOnClickListeners())
    }

    @Test
    fun testSendFeedbackHasOnCLickListener() {
        val fragment = startFragment(ReviewAppDialog())

        assertThat(fragment.app_review_send_feedback.hasOnClickListeners())
    }

    @Test
    fun testCancelRateHasOnCLickListener() {
        val fragment = startFragment(ReviewAppDialog())

        assertThat(fragment.app_review_cancel_rate.hasOnClickListeners())
    }

    @Test
    fun testCancelFeedbackHasOnCLickListener() {
        val fragment = startFragment(ReviewAppDialog())

        assertThat(fragment.app_review_cancel_feedback.hasOnClickListeners())
    }

    @Test
    fun testYesClickedSetsTextAndTogglesVisibility() {
        val fragment = startFragment(ReviewAppDialog()) as ReviewAppDialog

        fragment.onClick(fragment.app_review_yes)

        assertThat(fragment.app_review_sub_header).hasText(R.string.review_app_mind_rating)
        assertThat(fragment.app_review_yes).isGone
        assertThat(fragment.app_review_nahhh).isGone
        assertThat(fragment.app_review_cancel_rate).isVisible
        assertThat(fragment.app_review_rate).isVisible
    }

    @Test
    fun testNahhhClickedSetsTextAndTogglesVisibility() {
        val fragment = startFragment(ReviewAppDialog()) as ReviewAppDialog

        fragment.onClick(fragment.app_review_nahhh)

        assertThat(fragment.app_review_sub_header).hasText(R.string.review_app_mind_sending)
        assertThat(fragment.app_review_yes).isGone
        assertThat(fragment.app_review_nahhh).isGone
        assertThat(fragment.app_review_cancel_feedback).isVisible
        assertThat(fragment.app_review_send_feedback).isVisible
    }
}
