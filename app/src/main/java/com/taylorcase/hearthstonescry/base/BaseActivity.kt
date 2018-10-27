package com.taylorcase.hearthstonescry.base

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.annotation.VisibleForTesting
import android.support.design.widget.Snackbar
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.view.*
import android.view.Gravity.*
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.FrameLayout
import android.widget.Toast
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.taylorcase.hearthstonescry.base.NavDrawerFragment.Companion.NAV_FRAG_TAG
import com.taylorcase.hearthstonescry.R
import com.taylorcase.hearthstonescry.ScryApplication
import com.taylorcase.hearthstonescry.search.SearchActivity
import com.taylorcase.hearthstonescry.utils.HeroUtils
import com.taylorcase.hearthstonescry.utils.KeyboardUtils
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.decor_nav_drawer.*
import com.taylorcase.hearthstonescry.utils.SharedPreferencesHelper
import javax.inject.Inject

abstract class BaseActivity : RxAppCompatActivity(), RequestListener<Drawable>, MvpView, View.OnClickListener {

    companion object {
        const val HOME = 0
        const val BACK_ARROW = 1
    }

    @VisibleForTesting var navDrawerFragment: NavDrawerFragment? = null
    private var drawerLayout: DrawerLayout? = null

    @Inject lateinit var heroUtils: HeroUtils
    @Inject lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as ScryApplication).getAppComponent()?.inject(this)
        setTheme(sharedPreferencesHelper.getTheme())
        super.onCreate(savedInstanceState)

        supportPostponeEnterTransition()

        val manager = supportFragmentManager
        navDrawerFragment = manager.findFragmentByTag(NAV_FRAG_TAG) as NavDrawerFragment?

        if (navDrawerFragment == null) {
            navDrawerFragment = NavDrawerFragment.newInstance()
            manager.beginTransaction().add(R.id.drawer_container, navDrawerFragment, NAV_FRAG_TAG).commit()
        }

        val inflater = LayoutInflater.from(this)
        drawerLayout = inflater.inflate(R.layout.decor_nav_drawer, null) as DrawerLayout
        inflater.inflate(getLayoutId(), drawerLayout?.findViewById<FrameLayout>(R.id.drawer_content), true)
        setContentView(drawerLayout)
        drawerLayout?.addDrawerListener(ActivityDrawerListener())
    }

    fun displaySnackbar(message: String) {
        Snackbar.make(drawer_layout, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (heroUtils.shouldAssetsBeWhite()) {
            menuInflater.inflate(R.menu.search_white, menu)
        } else {
            menuInflater.inflate(R.menu.search_black, menu)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_search) {
            startActivity(Intent(this, SearchActivity::class.java))
        }
        if (id == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    fun setupToolbar(toolbar: Toolbar, title: String = getAppName(), navigationMethod: Int = HOME) {
        setSupportActionBar(toolbar)

        val actionBar = supportActionBar
        actionBar?.title = title
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeButtonEnabled(true)

        if (navigationMethod == HOME) {
            toolbar.setNavigationOnClickListener(this)
            if (heroUtils.shouldAssetsBeWhite()) {
                toolbar.setNavigationIcon(R.drawable.ic_dehaze_white_24dp)
            } else {
                toolbar.setNavigationIcon(R.drawable.ic_dehaze_black_24dp)
            }
        } else if (navigationMethod == BACK_ARROW) {
            if (heroUtils.shouldAssetsBeWhite()) {
                toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
            } else {
                toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
            }
        }
    }

    override fun onClick(v: View?) {
        drawerLayout?.openDrawer(START)
    }

    fun setLoading(isLoading: Boolean) {
        progress_bar.visibility = if (isLoading) VISIBLE else INVISIBLE
    }

    override fun showError() {
        Handler(Looper.getMainLooper()).post({
            setLoading(false)
            Toast.makeText(this, R.string.whoops_error, Toast.LENGTH_LONG).show()
        })
    }

    override fun onBackPressed() {
        if (!closeDrawer()) {
            super.onBackPressed()
        }
    }

    fun closeDrawer(): Boolean {
        if (drawerLayout != null && drawerLayout!!.isDrawerOpen(START)) {
            drawerLayout!!.closeDrawer(START)
            return true
        }
        return false
    }

    private fun getAppName(): String {
        return resources.getString(R.string.app_name)
    }

    private fun getLayoutId(): Int {
        val contentView = findAnnotation(javaClass, InjectLayout::class.java)
                ?: throw IllegalStateException(javaClass.simpleName + " does not specify @InjectLayout")
        return contentView.value
    }

    private fun <A : Annotation> findAnnotation(type: Class<*>, annotationType: Class<A>): A? {
        var annotation: A? = null
        var currentType: Class<*>? = type
        while (currentType != null) {
            annotation = currentType.getAnnotation(annotationType)
            if (annotation != null) {
                break
            }
            currentType = currentType.superclass
        }
        return annotation
    }

    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
        supportStartPostponedEnterTransition()
        setLoading(false)
        return false
    }

    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
        supportStartPostponedEnterTransition()
        setLoading(false)
        return false
    }

    private fun scheduleStartPostponedTransition(sharedElement: View) {
        sharedElement.viewTreeObserver.addOnPreDrawListener(
                object : ViewTreeObserver.OnPreDrawListener {
                    override fun onPreDraw(): Boolean {
                        sharedElement.viewTreeObserver.removeOnPreDrawListener(this)
                        startPostponedEnterTransition()
                        return true
                    }
                })
    }

    override fun onDestroy() {
        super.onDestroy()
        drawerLayout = null
    }

    private class ActivityDrawerListener : DrawerLayout.SimpleDrawerListener() {

        override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
            KeyboardUtils.hideKeyboard(drawerView)
            super.onDrawerSlide(drawerView, slideOffset)
        }
    }
}