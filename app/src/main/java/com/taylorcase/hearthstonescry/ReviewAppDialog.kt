package com.taylorcase.hearthstonescry

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager.LayoutParams.*
import kotlinx.android.synthetic.main.dialog_review_app.*
import timber.log.Timber

open class ReviewAppDialog : DialogFragment(), View.OnClickListener {

    override fun onStart() {
        super.onStart()
        dialog.window.setLayout(MATCH_PARENT, WRAP_CONTENT)
        setupListeners()
    }

    private fun setupListeners() {
        app_review_yes.setOnClickListener(this)
        app_review_nahhh.setOnClickListener(this)
        app_review_rate.setOnClickListener(this)
        app_review_send_feedback.setOnClickListener(this)
        app_review_cancel_rate.setOnClickListener(this)
        app_review_cancel_feedback.setOnClickListener(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_review_app, container, false)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.app_review_send_feedback -> sendFeedbackClicked()
            R.id.app_review_rate -> rateAppClicked()
            R.id.app_review_yes -> yesClicked()
            R.id.app_review_nahhh -> nahhhClicked()
            R.id.app_review_cancel_rate -> dismiss()
            R.id.app_review_cancel_feedback -> dismiss()
        }
    }

    private fun sendFeedbackClicked() {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse(MAIL_TO + CC + CC_CONTENT + SUBJECT + Uri.encode(SUBJECT_CONTENT) + BODY + Uri.encode(BODY_CONTENT))
        try {
            context?.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Timber.d(e)
        }
    }

    private fun rateAppClicked() {
        val packageName = context?.packageName
        try {
            context?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(MARKET_DETAILS_URL + packageName)))
        } catch (e: ActivityNotFoundException) {
            context?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(PLAY_STORE_URL + packageName)))
        }
    }

    private fun yesClicked() {
        app_review_sub_header.text = getString(R.string.review_app_mind_rating)
        app_review_yes.visibility = View.GONE
        app_review_nahhh.visibility = View.GONE
        app_review_cancel_rate.visibility = View.VISIBLE
        app_review_rate.visibility = View.VISIBLE
    }

    private fun nahhhClicked() {
        app_review_sub_header.text = getString(R.string.review_app_mind_sending)
        app_review_yes.visibility = View.GONE
        app_review_nahhh.visibility = View.GONE
        app_review_cancel_feedback.visibility = View.VISIBLE
        app_review_send_feedback.visibility = View.VISIBLE
    }

    companion object {
        const val REVIEW_APP_TAG = "review app tag"
        private const val MAIL_TO = "mailto:greeninjalabs@gmail.com"
        private const val CC = "?cc="
        private const val CC_CONTENT = ""
        private const val SUBJECT = "&subject="
        private const val SUBJECT_CONTENT = "Scry App Feedback"
        private const val BODY = "&body="
        private const val BODY_CONTENT = ""
        private const val MARKET_DETAILS_URL = "market://details?id="
        private const val PLAY_STORE_URL = "https://play.google.com/store/apps/details?id="
    }
}
