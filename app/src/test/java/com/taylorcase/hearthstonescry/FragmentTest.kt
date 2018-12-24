package com.taylorcase.hearthstonescry

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import org.junit.After
import org.robolectric.Robolectric.*

abstract class FragmentTest : InjectingTest() {

    lateinit var activity: AppCompatActivity
    private lateinit var fragment: Fragment

    open fun startFragment(fragment: Fragment): Fragment {
        this.fragment = fragment
        activity = buildActivity(AppCompatActivity::class.java).create().start().visible().get()
        val manager = activity.supportFragmentManager
        manager?.beginTransaction()?.add(fragment, TAG)?.commit()
        manager?.executePendingTransactions()
        return fragment
    }

    @After
    fun destroyFragment() {
        val manager = activity.supportFragmentManager
        manager?.beginTransaction()?.remove(fragment)?.commit()
        activity.finish()
    }

    companion object {
        private const val TAG = "tag"
    }
}
