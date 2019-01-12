package com.taylorcase.hearthstonescry.selecthero

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.taylorcase.hearthstonescry.CardsActivity
import com.taylorcase.hearthstonescry.R
import com.taylorcase.hearthstonescry.ScryApplication
import com.taylorcase.hearthstonescry.base.BaseActivity
import com.taylorcase.hearthstonescry.model.enums.Hero
import com.taylorcase.hearthstonescry.selecthero.SelectHeroViewHolder.Companion.EXTRA_HERO
import com.taylorcase.hearthstonescry.utils.ImageLoader
import kotlinx.android.synthetic.main.activity_detailed_select_hero.*
import javax.inject.Inject

class DetailedSelectHeroActivity : BaseActivity(), SelectHeroContract.View {

    private lateinit var hero: Hero

    @Inject lateinit var presenter: SelectHeroContract.Presenter
    @Inject lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as ScryApplication).getComponent().inject(this)
        presenter.attach(this)

        val intent = intent
        if (intent != null) {
            val heroName = intent.getStringExtra(EXTRA_HERO)
            this.hero = heroUtils.getHeroForString(heroName)!!
            imageLoader.loadDrawableCenterCrop(heroUtils.getHeroImageForHero(hero), detailed_select_hero_image)

            setText()
            setupCollapsingToolbar(hero.toString())
        }

        detailed_select_hero_select.setOnClickListener { onHeroClicked() }
        setupToolbar(detailed_select_hero_toolbar, "", BACK_ARROW)
    }

    override fun provideContentLayoutId(): Int {
        return R.layout.activity_detailed_select_hero
    }

    private fun setupCollapsingToolbar(title: String) {
        detailed_select_hero_collapsing_toolbar.title = title
        val textColor = resources.getColor(heroUtils.getCurrentAssetsColor())
        detailed_select_hero_collapsing_toolbar.setCollapsedTitleTextColor(textColor)

        // TODO: Fix this to properly set the correct color of expanded Title color
        // detailed_select_hero_collapsing_toolbar.setExpandedTitleTextColor(textColor)
    }

    private fun setText() {
        detailed_select_hero_desc.text = heroUtils.getDescriptionForHero(hero)
        detailed_select_hero_mechanics.text = heroUtils.getMechanicsForHero(hero)
        detailed_select_hero_power.text = heroUtils.getHeroPowerForHero(hero)
    }

    private fun onHeroClicked() {
        presenter.getHeroAndTheme(heroUtils.getHeroIconForHero(hero))
    }

    override fun finishAndStartCardsActivity() {
        finish()
        startActivity(Intent(this, CardsActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
    }

    public override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }
}
