package com.taylorcase.hearthstonescry.utils

import android.app.Application
import com.taylorcase.hearthstonescry.model.enums.Hero
import com.taylorcase.hearthstonescry.R
import com.taylorcase.hearthstonescry.model.enums.Hero.*
import javax.inject.Inject

open class HeroUtils @Inject constructor(
        private val sharedPreferencesHelper: SharedPreferencesHelper,
        private val context: Application
) {

    open fun getHeroIconForHero(hero: Hero): Int {
        return when (hero) {
            WARRIOR -> R.drawable.ic_warrior
            HUNTER -> R.drawable.ic_hunter
            MAGE -> R.drawable.ic_mage
            SHAMAN -> R.drawable.ic_shaman
            PALADIN -> R.drawable.ic_paladin
            PRIEST -> R.drawable.ic_priest
            DRUID -> R.drawable.ic_druid
            ROGUE -> R.drawable.ic_rogue
            else -> {
                R.drawable.ic_warlock
            }
        }
    }

    open fun getFavoriteHeroIcon(): Int = getHeroIconForHero(getFavoriteHero())

    open fun getHeroImageForHero(hero: Hero): Int {
        return when (hero) {
            WARRIOR -> R.drawable.hero_warrior
            HUNTER -> R.drawable.hero_hunter
            MAGE -> R.drawable.hero_mage
            SHAMAN -> R.drawable.hero_shaman
            PALADIN -> R.drawable.hero_paladin
            PRIEST -> R.drawable.hero_priest
            DRUID -> R.drawable.hero_druid
            ROGUE -> R.drawable.hero_rogue
            else -> {
                R.drawable.hero_warlock
            }
        }
    }

    open fun getDescriptionForHero(hero: Hero): String {
        val resources = context.resources
        return when (hero) {
            WARRIOR -> resources.getString(R.string.warrior_description)
            HUNTER -> resources.getString(R.string.hunter_description)
            MAGE -> resources.getString(R.string.mage_description)
            SHAMAN -> resources.getString(R.string.shaman_description)
            PALADIN -> resources.getString(R.string.paladin_description)
            PRIEST -> resources.getString(R.string.priest_description)
            DRUID -> resources.getString(R.string.druid_description)
            ROGUE -> resources.getString(R.string.rogue_description)
            else -> {
                resources.getString(R.string.warlock_description)
            }
        }
    }

    open fun getMechanicsForHero(hero: Hero): String {
        val resources = context.resources
        return when (hero) {
            WARRIOR -> resources.getString(R.string.warrior_mechanics)
            HUNTER -> resources.getString(R.string.hunter_mechanics)
            MAGE -> resources.getString(R.string.mage_mechanics)
            SHAMAN -> resources.getString(R.string.shaman_mechanics)
            PALADIN -> resources.getString(R.string.paladin_mechanics)
            PRIEST -> resources.getString(R.string.priest_mechanics)
            DRUID -> resources.getString(R.string.druid_mechanics)
            ROGUE -> resources.getString(R.string.rogue_mechanics)
            else -> {
                resources.getString(R.string.warlock_mechanics)
            }
        }
    }

    open fun getHeroPowerForHero(hero: Hero): String {
        val resources = context.resources
        return when (hero) {
            WARRIOR -> resources.getString(R.string.warrior_hero_power)
            HUNTER -> resources.getString(R.string.hunter_hero_power)
            MAGE -> resources.getString(R.string.mage_hero_power)
            SHAMAN -> resources.getString(R.string.shaman_hero_power)
            PALADIN -> resources.getString(R.string.paladin_hero_power)
            PRIEST -> resources.getString(R.string.priest_hero_power)
            DRUID -> resources.getString(R.string.druid_hero_power)
            ROGUE -> resources.getString(R.string.rogue_hero_power)
            else -> {
                resources.getString(R.string.warlock_hero_power)
            }
        }
    }

    // Reverse enum look up hack since valueOf() does not work in Kotlin...
    open fun getHeroForString(name: String): Hero? {
        val map = HashMap<String, Hero>()
        for (hero in Hero.values()) {
            map[hero.name] = hero
        }
        return map[name.toUpperCase()]
    }

    open fun getFavoriteHeroImage(): Int = getHeroImageForHero(getFavoriteHero())

    open fun getFavoriteHero(): Hero {
        val savedHero = sharedPreferencesHelper.getSavedHeroString()

        var favoriteHero = WARLOCK
        for (hero in values()) {
            if (hero.toString() == savedHero) {
                favoriteHero = hero
            }
        }
        return favoriteHero
    }

    open fun hasFavoriteHero(): Boolean = sharedPreferencesHelper.getSavedHeroString().isNotEmpty()

    open fun shouldAssetsBeWhite(): Boolean {
        return when (getFavoriteHero()) {
            MAGE -> false
            PALADIN -> false
            PRIEST -> false
            else -> true
        }
    }

    open fun getCurrentAssetsColor(): Int {
        if (shouldAssetsBeWhite()) {
            return R.color.white
        }
        return R.color.black
    }
}