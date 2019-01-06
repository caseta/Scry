package com.taylorcase.hearthstonescry.selecthero

import com.taylorcase.hearthstonescry.R
import com.taylorcase.hearthstonescry.base.BasePresenter
import com.taylorcase.hearthstonescry.model.enums.Hero
import com.taylorcase.hearthstonescry.utils.SharedPreferencesHelper
import javax.inject.Inject

open class SelectHeroPresenter @Inject constructor(
        private val sharedPreferencesHelper: SharedPreferencesHelper
) : BasePresenter<SelectHeroContract.View>(), SelectHeroContract.Presenter {

    override fun getHeroAndTheme(selectedHero: Int) {
        var hero = Hero.WARLOCK
        var style = R.style.AppTheme_Warlock
        when (selectedHero) {
            R.drawable.ic_warrior -> {
                hero = Hero.WARRIOR
                style = R.style.AppTheme_Warrior
            }
            R.drawable.ic_mage -> {
                hero = Hero.MAGE
                style = R.style.AppTheme_Mage
            }
            R.drawable.ic_paladin -> {
                hero = Hero.PALADIN
                style = R.style.AppTheme_Paladin
            }
            R.drawable.ic_priest -> {
                hero = Hero.PRIEST
                style = R.style.AppTheme_Priest
            }
            R.drawable.ic_rogue -> {
                hero = Hero.ROGUE
                style = R.style.AppTheme_Rogue
            }
            R.drawable.ic_shaman -> {
                hero = Hero.SHAMAN
                style = R.style.AppTheme_Shaman
            }
            R.drawable.ic_druid -> {
                hero = Hero.DRUID
                style = R.style.AppTheme_Druid
            }
            R.drawable.ic_hunter -> {
                hero = Hero.HUNTER
                style = R.style.AppTheme_Hunter
            }
        }

        sharedPreferencesHelper.saveTheme(style)
        sharedPreferencesHelper.saveHero(hero)

        val view = getView() as SelectHeroContract.View
        view.finishAndStartCardsActivity()
    }
}
