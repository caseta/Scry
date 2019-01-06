package com.taylorcase.hearthstonescry.utils

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.taylorcase.hearthstonescry.R
import com.taylorcase.hearthstonescry.model.enums.Hero
import com.taylorcase.hearthstonescry.model.enums.Hero.*
import org.assertj.core.api.Assertions.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.RuntimeEnvironment.*

@RunWith(RobolectricTestRunner::class)
class HeroUtilsTest {

    private val mockSharedPreferencesHelper = mock<SharedPreferencesHelper>()

    @Test
    fun testGetFavoriteHeroIconIsWarlockReturnsWarlockDrawable() {
        demandFavoriteHero(WARLOCK)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getFavoriteHeroIcon()).isEqualTo(R.drawable.ic_warlock)
        verifyFavoriteHero()
    }

    @Test
    fun testGetFavoriteHeroIconIsWarriorReturnsWarriorDrawable() {
        demandFavoriteHero(WARRIOR)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getFavoriteHeroIcon()).isEqualTo(R.drawable.ic_warrior)
        verifyFavoriteHero()
    }

    @Test
    fun testGetFavoriteHeroIconIsHunterReturnsHunterDrawable() {
        demandFavoriteHero(HUNTER)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getFavoriteHeroIcon()).isEqualTo(R.drawable.ic_hunter)
        verifyFavoriteHero()
    }

    @Test
    fun testGetFavoriteHeroIconIsMageReturnsMageDrawable() {
        demandFavoriteHero(MAGE)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getFavoriteHeroIcon()).isEqualTo(R.drawable.ic_mage)
        verifyFavoriteHero()
    }

    @Test
    fun testGetFavoriteHeroIconIsShamanReturnsShamanDrawable() {
        demandFavoriteHero(SHAMAN)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getFavoriteHeroIcon()).isEqualTo(R.drawable.ic_shaman)
        verifyFavoriteHero()
    }

    @Test
    fun testGetFavoriteHeroIconIsPaladinReturnsPaladinDrawable() {
        demandFavoriteHero(PALADIN)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getFavoriteHeroIcon()).isEqualTo(R.drawable.ic_paladin)
        verifyFavoriteHero()
    }

    @Test
    fun testGetFavoriteHeroIconIsPriestReturnsPriestDrawable() {
        demandFavoriteHero(PRIEST)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getFavoriteHeroIcon()).isEqualTo(R.drawable.ic_priest)
        verifyFavoriteHero()
    }

    @Test
    fun testGetFavoriteHeroIconIsDruidReturnsDruidDrawable() {
        demandFavoriteHero(DRUID)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getFavoriteHeroIcon()).isEqualTo(R.drawable.ic_druid)
        verifyFavoriteHero()
    }

    @Test
    fun testGetFavoriteHeroIconIsRogueReturnsRogueDrawable() {
        demandFavoriteHero(ROGUE)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getFavoriteHeroIcon()).isEqualTo(R.drawable.ic_rogue)
        verifyFavoriteHero()
    }

    @Test
    fun testGetFavoriteHeroImageWarlock() {
        demandFavoriteHero(WARLOCK)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getFavoriteHeroImage()).isEqualTo(R.drawable.hero_warlock)
        verifyFavoriteHero()
    }

    @Test
    fun testGetFavoriteHeroImageWarrior() {
        demandFavoriteHero(WARRIOR)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getFavoriteHeroImage()).isEqualTo(R.drawable.hero_warrior)
        verifyFavoriteHero()
    }

    @Test
    fun testGetFavoriteHeroImageHunter() {
        demandFavoriteHero(HUNTER)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getFavoriteHeroImage()).isEqualTo(R.drawable.hero_hunter)
        verifyFavoriteHero()
    }

    @Test
    fun testGetFavoriteHeroImageMage() {
        demandFavoriteHero(MAGE)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getFavoriteHeroImage()).isEqualTo(R.drawable.hero_mage)
        verifyFavoriteHero()
    }

    @Test
    fun testGetFavoriteHeroImageShaman() {
        demandFavoriteHero(SHAMAN)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getFavoriteHeroImage()).isEqualTo(R.drawable.hero_shaman)
        verifyFavoriteHero()
    }

    @Test
    fun testGetFavoriteHeroImagePaladin() {
        demandFavoriteHero(PALADIN)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getFavoriteHeroImage()).isEqualTo(R.drawable.hero_paladin)
        verifyFavoriteHero()
    }

    @Test
    fun testGetFavoriteHeroImagePriest() {
        demandFavoriteHero(PRIEST)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getFavoriteHeroImage()).isEqualTo(R.drawable.hero_priest)
        verifyFavoriteHero()
    }

    @Test
    fun testGetFavoriteHeroImageDruid() {
        demandFavoriteHero(DRUID)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getFavoriteHeroImage()).isEqualTo(R.drawable.hero_druid)
        verifyFavoriteHero()
    }

    @Test
    fun testGetFavoriteHeroImageRogue() {
        demandFavoriteHero(ROGUE)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getFavoriteHeroImage()).isEqualTo(R.drawable.hero_rogue)
        verifyFavoriteHero()
    }

    @Test
    fun testHasFavoriteHeroIsFalse() {
        doReturn("").whenever(mockSharedPreferencesHelper).getSavedHeroString()
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.hasFavoriteHero()).isFalse()
        verifyFavoriteHero()
    }

    @Test
    fun testHasFavoriteHeroIsTrue() {
        demandFavoriteHero(WARLOCK)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.hasFavoriteHero()).isTrue()
        verifyFavoriteHero()
    }

    @Test
    fun testShouldAssetsBeWhiteTrueWarlock() {
        demandFavoriteHero(WARLOCK)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.shouldAssetsBeWhite()).isTrue()
        verifyFavoriteHero()
    }

    @Test
    fun testShouldAssetsBeWhiteTrueWarrior() {
        demandFavoriteHero(WARRIOR)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.shouldAssetsBeWhite()).isTrue()
        verifyFavoriteHero()
    }

    @Test
    fun testShouldAssetsBeWhiteTrueHunter() {
        demandFavoriteHero(HUNTER)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.shouldAssetsBeWhite()).isTrue()
        verifyFavoriteHero()
    }

    @Test
    fun testShouldAssetsBeWhiteFalseMage() {
        demandFavoriteHero(MAGE)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.shouldAssetsBeWhite()).isFalse()
        verifyFavoriteHero()
    }

    @Test
    fun testShouldAssetsBeWhiteTrueShaman() {
        demandFavoriteHero(SHAMAN)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.shouldAssetsBeWhite()).isTrue()
        verifyFavoriteHero()
    }

    @Test
    fun testShouldAssetsBeWhiteFalsePaladin() {
        demandFavoriteHero(PALADIN)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.shouldAssetsBeWhite()).isFalse()
        verifyFavoriteHero()
    }

    @Test
    fun testShouldAssetsBeWhiteFalsePriest() {
        demandFavoriteHero(PRIEST)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.shouldAssetsBeWhite()).isFalse()
        verifyFavoriteHero()
    }

    @Test
    fun testShouldAssetsBeWhiteTrueDruid() {
        demandFavoriteHero(DRUID)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.shouldAssetsBeWhite()).isTrue()
        verifyFavoriteHero()
    }

    @Test
    fun testShouldAssetsBeWhiteTrueRogue() {
        demandFavoriteHero(ROGUE)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.shouldAssetsBeWhite()).isTrue()
        verifyFavoriteHero()
    }

    @Test
    fun testGetHeroDescriptionReturnsWarrior() {
        demandFavoriteHero(WARRIOR)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getDescriptionForHero(WARRIOR)).isEqualTo(application.getString(R.string.warrior_description))
    }

    @Test
    fun testGetHeroDescriptionReturnsHunter() {
        demandFavoriteHero(HUNTER)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getDescriptionForHero(HUNTER)).isEqualTo(application.getString(R.string.hunter_description))
    }

    @Test
    fun testGetHeroDescriptionReturnsMage() {
        demandFavoriteHero(MAGE)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getDescriptionForHero(MAGE)).isEqualTo(application.getString(R.string.mage_description))
    }

    @Test
    fun testGetHeroDescriptionReturnsShaman() {
        demandFavoriteHero(SHAMAN)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getDescriptionForHero(SHAMAN)).isEqualTo(application.getString(R.string.shaman_description))
    }

    @Test
    fun testGetHeroDescriptionReturnsPaladin() {
        demandFavoriteHero(PALADIN)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getDescriptionForHero(PALADIN)).isEqualTo(application.getString(R.string.paladin_description))
    }

    @Test
    fun testGetHeroDescriptionReturnsPriest() {
        demandFavoriteHero(PRIEST)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getDescriptionForHero(PRIEST)).isEqualTo(application.getString(R.string.priest_description))
    }

    @Test
    fun testGetHeroDescriptionReturnsDruid() {
        demandFavoriteHero(DRUID)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getDescriptionForHero(DRUID)).isEqualTo(application.getString(R.string.druid_description))
    }

    @Test
    fun testGetHeroDescriptionReturnsRogue() {
        demandFavoriteHero(ROGUE)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getDescriptionForHero(ROGUE)).isEqualTo(application.getString(R.string.rogue_description))
    }

    @Test
    fun testGetHeroDescriptionReturnsWarlock() {
        demandFavoriteHero(WARLOCK)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getDescriptionForHero(WARLOCK)).isEqualTo(application.getString(R.string.warlock_description))
    }

    @Test
    fun testGetMechanicsReturnsWarrior() {
        demandFavoriteHero(WARRIOR)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getMechanicsForHero(WARRIOR)).isEqualTo(application.getString(R.string.warrior_mechanics))
    }

    @Test
    fun testGetMechanicsReturnsHunter() {
        demandFavoriteHero(HUNTER)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getMechanicsForHero(HUNTER)).isEqualTo(application.getString(R.string.hunter_mechanics))
    }

    @Test
    fun testGetMechanicsReturnsMage() {
        demandFavoriteHero(MAGE)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getMechanicsForHero(MAGE)).isEqualTo(application.getString(R.string.mage_mechanics))
    }

    @Test
    fun testGetMechanicsReturnsShaman() {
        demandFavoriteHero(SHAMAN)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getMechanicsForHero(SHAMAN)).isEqualTo(application.getString(R.string.shaman_mechanics))
    }

    @Test
    fun testGetMechanicsReturnsPaladin() {
        demandFavoriteHero(PALADIN)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getMechanicsForHero(PALADIN)).isEqualTo(application.getString(R.string.paladin_mechanics))
    }

    @Test
    fun testGetMechanicsReturnsPriest() {
        demandFavoriteHero(PRIEST)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getMechanicsForHero(PRIEST)).isEqualTo(application.getString(R.string.priest_mechanics))
    }

    @Test
    fun testGetMechanicsReturnsDruid() {
        demandFavoriteHero(DRUID)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getMechanicsForHero(DRUID)).isEqualTo(application.getString(R.string.druid_mechanics))
    }

    @Test
    fun testGetMechanicsReturnsRogue() {
        demandFavoriteHero(ROGUE)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getMechanicsForHero(ROGUE)).isEqualTo(application.getString(R.string.rogue_mechanics))
    }

    @Test
    fun testGetMechanicsReturnsWarlock() {
        demandFavoriteHero(WARLOCK)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getMechanicsForHero(WARLOCK)).isEqualTo(application.getString(R.string.warlock_mechanics))
    }

    @Test
    fun testGetHeroPowerForHeroWarrior() {
        demandFavoriteHero(WARRIOR)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getHeroPowerForHero(WARRIOR)).isEqualTo(application.getString(R.string.warrior_hero_power))
    }

    @Test
    fun testGetHeroPowerForHeroHunter() {
        demandFavoriteHero(HUNTER)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getHeroPowerForHero(HUNTER)).isEqualTo(application.getString(R.string.hunter_hero_power))
    }

    @Test
    fun testGetHeroPowerForHeroMage() {
        demandFavoriteHero(MAGE)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getHeroPowerForHero(MAGE)).isEqualTo(application.getString(R.string.mage_hero_power))
    }

    @Test
    fun testGetHeroPowerForHeroShaman() {
        demandFavoriteHero(SHAMAN)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getHeroPowerForHero(SHAMAN)).isEqualTo(application.getString(R.string.shaman_hero_power))
    }

    @Test
    fun testGetHeroPowerForHeroPaladin() {
        demandFavoriteHero(PALADIN)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getHeroPowerForHero(PALADIN)).isEqualTo(application.getString(R.string.paladin_hero_power))
    }

    @Test
    fun testGetHeroPowerForHeroPriest() {
        demandFavoriteHero(PRIEST)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getHeroPowerForHero(PRIEST)).isEqualTo(application.getString(R.string.priest_hero_power))
    }

    @Test
    fun testGetHeroPowerForHeroDruid() {
        demandFavoriteHero(DRUID)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getHeroPowerForHero(DRUID)).isEqualTo(application.getString(R.string.druid_hero_power))
    }

    @Test
    fun testGetHeroPowerForHeroRogue() {
        demandFavoriteHero(ROGUE)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getHeroPowerForHero(ROGUE)).isEqualTo(application.getString(R.string.rogue_hero_power))
    }

    @Test
    fun testGetHeroPowerForHeroWarlock() {
        demandFavoriteHero(WARLOCK)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper, application)

        assertThat(heroUtils.getHeroPowerForHero(WARLOCK)).isEqualTo(application.getString(R.string.warlock_hero_power))
    }

    private fun demandFavoriteHero(hero: Hero) {
        doReturn(hero.toString()).whenever(mockSharedPreferencesHelper).getSavedHeroString()
    }

    private fun verifyFavoriteHero() {
        verify(mockSharedPreferencesHelper).getSavedHeroString()
    }
}
