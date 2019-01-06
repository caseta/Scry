package com.taylorcase.hearthstonescry.selecthero

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.taylorcase.hearthstonescry.R
import com.taylorcase.hearthstonescry.model.enums.Hero
import com.taylorcase.hearthstonescry.utils.SharedPreferencesHelper
import org.junit.Test

class SelectHeroPresenterTest {

    private val mockView = mock<SelectHeroContract.View>()
    private val mockSharedPreferencesHelper = mock<SharedPreferencesHelper>()

    @Test
    fun testGetHeroAndThemeSavesThemeWarlock() {
        val presenter = demandPresenter()

        presenter.getHeroAndTheme(R.drawable.ic_warlock)

        verify(mockSharedPreferencesHelper).saveTheme(R.style.AppTheme_Warlock)
    }

    @Test
    fun testGetHeroAndThemeSavesHeroWarlock() {
        val presenter = demandPresenter()

        presenter.getHeroAndTheme(R.drawable.ic_warlock)

        verify(mockSharedPreferencesHelper).saveHero(Hero.WARLOCK)
    }

    @Test
    fun testGetHeroAndThemeSavesHeroWarrior() {
        val presenter = demandPresenter()

        presenter.getHeroAndTheme(R.drawable.ic_warrior)

        verify(mockSharedPreferencesHelper).saveHero(Hero.WARRIOR)
    }

    @Test
    fun testGetHeroAndThemeSavesThemeWarrior() {
        val presenter = demandPresenter()

        presenter.getHeroAndTheme(R.drawable.ic_warrior)

        verify(mockSharedPreferencesHelper).saveTheme(R.style.AppTheme_Warrior)
    }

    @Test
    fun testGetHeroAndThemeSavesHeroMage() {
        val presenter = demandPresenter()

        presenter.getHeroAndTheme(R.drawable.ic_mage)

        verify(mockSharedPreferencesHelper).saveHero(Hero.MAGE)
    }

    @Test
    fun testGetHeroAndThemeSavesThemeMage() {
        val presenter = demandPresenter()

        presenter.getHeroAndTheme(R.drawable.ic_mage)

        verify(mockSharedPreferencesHelper).saveTheme(R.style.AppTheme_Mage)
    }

    @Test
    fun testGetHeroAndThemeSavesHeroPaladin() {
        val presenter = demandPresenter()

        presenter.getHeroAndTheme(R.drawable.ic_paladin)

        verify(mockSharedPreferencesHelper).saveHero(Hero.PALADIN)
    }

    @Test
    fun testGetHeroAndThemeSavesThemePaladin() {
        val presenter = demandPresenter()

        presenter.getHeroAndTheme(R.drawable.ic_paladin)

        verify(mockSharedPreferencesHelper).saveTheme(R.style.AppTheme_Paladin)
    }

    @Test
    fun testGetHeroAndThemeSavesHeroPriest() {
        val presenter = demandPresenter()

        presenter.getHeroAndTheme(R.drawable.ic_priest)

        verify(mockSharedPreferencesHelper).saveHero(Hero.PRIEST)
    }

    @Test
    fun testGetHeroAndThemeSavesThemePriest() {
        val presenter = demandPresenter()

        presenter.getHeroAndTheme(R.drawable.ic_priest)

        verify(mockSharedPreferencesHelper).saveTheme(R.style.AppTheme_Priest)
    }

    @Test
    fun testGetHeroAndThemeSavesHeroRogue() {
        val presenter = demandPresenter()

        presenter.getHeroAndTheme(R.drawable.ic_rogue)

        verify(mockSharedPreferencesHelper).saveHero(Hero.ROGUE)
    }

    @Test
    fun testGetHeroAndThemeSavesThemeRogue() {
        val presenter = demandPresenter()

        presenter.getHeroAndTheme(R.drawable.ic_rogue)

        verify(mockSharedPreferencesHelper).saveTheme(R.style.AppTheme_Rogue)
    }

    @Test
    fun testGetHeroAndThemeSavesHeroShaman() {
        val presenter = demandPresenter()

        presenter.getHeroAndTheme(R.drawable.ic_shaman)

        verify(mockSharedPreferencesHelper).saveHero(Hero.SHAMAN)
    }

    @Test
    fun testGetHeroAndThemeSavesThemeShaman() {
        val presenter = demandPresenter()

        presenter.getHeroAndTheme(R.drawable.ic_shaman)

        verify(mockSharedPreferencesHelper).saveTheme(R.style.AppTheme_Shaman)
    }

    @Test
    fun testGetHeroAndThemeSavesThemeDruid() {
        val presenter = demandPresenter()

        presenter.getHeroAndTheme(R.drawable.ic_druid)

        verify(mockSharedPreferencesHelper).saveTheme(R.style.AppTheme_Druid)
    }

    @Test
    fun testGetHeroAndThemeSavesHeroDruid() {
        val presenter = demandPresenter()

        presenter.getHeroAndTheme(R.drawable.ic_druid)

        verify(mockSharedPreferencesHelper).saveHero(Hero.DRUID)
    }

    @Test
    fun testGetHeroAndThemeSavesThemeHunter() {
        val presenter = demandPresenter()

        presenter.getHeroAndTheme(R.drawable.ic_hunter)

        verify(mockSharedPreferencesHelper).saveTheme(R.style.AppTheme_Hunter)
    }

    @Test
    fun testGetHeroAndThemeSavesHeroHunter() {
        val presenter = demandPresenter()

        presenter.getHeroAndTheme(R.drawable.ic_hunter)

        verify(mockSharedPreferencesHelper).saveHero(Hero.HUNTER)
    }

    @Test
    fun testGetHeroAndThemeCallsViewFinish() {
        val presenter = demandPresenter()

        presenter.getHeroAndTheme(R.drawable.ic_warlock)

        verify(mockView).finishAndStartCardsActivity()
    }

    private fun demandPresenter(): SelectHeroPresenter {
        val presenter = SelectHeroPresenter(mockSharedPreferencesHelper)
        presenter.attach(mockView)
        return presenter
    }
}
