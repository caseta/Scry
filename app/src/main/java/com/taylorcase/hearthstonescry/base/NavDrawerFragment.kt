package com.taylorcase.hearthstonescry.base

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.taylorcase.hearthstonescry.CardsActivity
import com.taylorcase.hearthstonescry.R
import com.taylorcase.hearthstonescry.ScryApplication
import com.taylorcase.hearthstonescry.savedcards.SavedCardsActivity
import com.taylorcase.hearthstonescry.selecthero.SelectHeroActivity
import com.taylorcase.hearthstonescry.utils.HeroUtils
import com.taylorcase.hearthstonescry.utils.ImageLoader
import kotlinx.android.synthetic.main.fragment_nav_drawer.*
import javax.inject.Inject

open class NavDrawerFragment : Fragment(), View.OnClickListener {

    companion object {
        val NAV_FRAG_TAG: String = NavDrawerFragment::class.java.simpleName

        fun newInstance(): NavDrawerFragment = NavDrawerFragment()
    }

    @Inject lateinit var heroUtils: HeroUtils
    @Inject lateinit var imageLoader: ImageLoader

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity?.application as ScryApplication).getAppComponent()?.inject(this)
        return inflater.inflate(R.layout.fragment_nav_drawer, container, false)
    }

    override fun onStart() {
        super.onStart()
        nav_drawer_home.setOnClickListener(this)
        nav_drawer_update_favorite_hero.setOnClickListener(this)
        nav_drawer_saved_cards.setOnClickListener(this)

        imageLoader.loadDrawableCenterCrop(heroUtils.getFavoriteHeroIcon(), nav_drawer_hero_icon)
        imageLoader.loadDrawableCenterInside(heroUtils.getFavoriteHeroImage(), nav_drawer_hero_image)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.nav_drawer_home -> startActivity(Intent(activity, CardsActivity::class.java))
            R.id.nav_drawer_update_favorite_hero -> startActivity(Intent(activity, SelectHeroActivity::class.java))
            R.id.nav_drawer_saved_cards -> startActivity(Intent(activity, SavedCardsActivity::class.java))
        }
        val activity = activity
        if (activity is BaseActivity) {
            activity.closeDrawer()
        }
    }
}
