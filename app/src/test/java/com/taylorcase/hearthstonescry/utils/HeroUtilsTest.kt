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

class HeroUtilsTest {

    private val mockSharedPreferencesHelper = mock<SharedPreferencesHelper>()

    @Test fun testGetFavoriteHeroIconIsWarlockReturnsWarlockDrawable() {
        demandFavoriteHero(WARLOCK)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper)

        assertThat(heroUtils.getFavoriteHeroIcon()).isEqualTo(R.drawable.ic_warlock)
        verifyFavoriteHero()
    }

    @Test fun testGetFavoriteHeroIconIsWarriorReturnsWarriorDrawable() {
        demandFavoriteHero(WARRIOR)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper)

        assertThat(heroUtils.getFavoriteHeroIcon()).isEqualTo(R.drawable.ic_warrior)
        verifyFavoriteHero()
    }

    @Test fun testGetFavoriteHeroIconIsHunterReturnsHunterDrawable() {
        demandFavoriteHero(HUNTER)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper)

        assertThat(heroUtils.getFavoriteHeroIcon()).isEqualTo(R.drawable.ic_hunter)
        verifyFavoriteHero()
    }

    @Test fun testGetFavoriteHeroIconIsMageReturnsMageDrawable() {
        demandFavoriteHero(MAGE)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper)

        assertThat(heroUtils.getFavoriteHeroIcon()).isEqualTo(R.drawable.ic_mage)
        verifyFavoriteHero()
    }

    @Test fun testGetFavoriteHeroIconIsShamanReturnsShamanDrawable() {
        demandFavoriteHero(SHAMAN)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper)

        assertThat(heroUtils.getFavoriteHeroIcon()).isEqualTo(R.drawable.ic_shaman)
        verifyFavoriteHero()
    }

    @Test fun testGetFavoriteHeroIconIsPaladinReturnsPaladinDrawable() {
        demandFavoriteHero(PALADIN)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper)

        assertThat(heroUtils.getFavoriteHeroIcon()).isEqualTo(R.drawable.ic_paladin)
        verifyFavoriteHero()
    }

    @Test fun testGetFavoriteHeroIconIsPriestReturnsPriestDrawable() {
        demandFavoriteHero(PRIEST)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper)

        assertThat(heroUtils.getFavoriteHeroIcon()).isEqualTo(R.drawable.ic_priest)
        verifyFavoriteHero()
    }

    @Test fun testGetFavoriteHeroIconIsDruidReturnsDruidDrawable() {
        demandFavoriteHero(DRUID)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper)

        assertThat(heroUtils.getFavoriteHeroIcon()).isEqualTo(R.drawable.ic_druid)
        verifyFavoriteHero()
    }

    @Test fun testGetFavoriteHeroIconIsRogueReturnsRogueDrawable() {
        demandFavoriteHero(ROGUE)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper)

        assertThat(heroUtils.getFavoriteHeroIcon()).isEqualTo(R.drawable.ic_rogue)
        verifyFavoriteHero()
    }

    @Test fun testGetFavoriteHeroImageWarlock() {
        demandFavoriteHero(WARLOCK)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper)

        assertThat(heroUtils.getFavoriteHeroImage()).isEqualTo(R.drawable.hero_warlock)
        verifyFavoriteHero()
    }

    @Test fun testGetFavoriteHeroImageWarrior() {
        demandFavoriteHero(WARRIOR)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper)

        assertThat(heroUtils.getFavoriteHeroImage()).isEqualTo(R.drawable.hero_warrior)
        verifyFavoriteHero()
    }

    @Test fun testGetFavoriteHeroImageHunter() {
        demandFavoriteHero(HUNTER)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper)

        assertThat(heroUtils.getFavoriteHeroImage()).isEqualTo(R.drawable.hero_hunter)
        verifyFavoriteHero()
    }

    @Test fun testGetFavoriteHeroImageMage() {
        demandFavoriteHero(MAGE)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper)

        assertThat(heroUtils.getFavoriteHeroImage()).isEqualTo(R.drawable.hero_mage)
        verifyFavoriteHero()
    }

    @Test fun testGetFavoriteHeroImageShaman() {
        demandFavoriteHero(SHAMAN)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper)

        assertThat(heroUtils.getFavoriteHeroImage()).isEqualTo(R.drawable.hero_shaman)
        verifyFavoriteHero()
    }

    @Test fun testGetFavoriteHeroImagePaladin() {
        demandFavoriteHero(PALADIN)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper)

        assertThat(heroUtils.getFavoriteHeroImage()).isEqualTo(R.drawable.hero_paladin)
        verifyFavoriteHero()
    }

    @Test fun testGetFavoriteHeroImagePriest() {
        demandFavoriteHero(PRIEST)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper)

        assertThat(heroUtils.getFavoriteHeroImage()).isEqualTo(R.drawable.hero_priest)
        verifyFavoriteHero()
    }

    @Test fun testGetFavoriteHeroImageDruid() {
        demandFavoriteHero(DRUID)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper)

        assertThat(heroUtils.getFavoriteHeroImage()).isEqualTo(R.drawable.hero_druid)
        verifyFavoriteHero()
    }

    @Test fun testGetFavoriteHeroImageRogue() {
        demandFavoriteHero(ROGUE)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper)

        assertThat(heroUtils.getFavoriteHeroImage()).isEqualTo(R.drawable.hero_rogue)
        verifyFavoriteHero()
    }

    @Test fun testHasFavoriteHeroIsFalse() {
        doReturn("").whenever(mockSharedPreferencesHelper).getSavedHeroString()
        val heroUtils = HeroUtils(mockSharedPreferencesHelper)

        assertThat(heroUtils.hasFavoriteHero()).isFalse()
        verifyFavoriteHero()
    }

    @Test fun testHasFavoriteHeroIsTrue() {
        demandFavoriteHero(WARLOCK)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper)

        assertThat(heroUtils.hasFavoriteHero()).isTrue()
        verifyFavoriteHero()
    }

    @Test fun testShouldAssetsBeWhiteTrueWarlock() {
        demandFavoriteHero(WARLOCK)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper)

        assertThat(heroUtils.shouldAssetsBeWhite()).isTrue()
        verifyFavoriteHero()
    }

    @Test fun testShouldAssetsBeWhiteTrueWarrior() {
        demandFavoriteHero(WARRIOR)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper)

        assertThat(heroUtils.shouldAssetsBeWhite()).isTrue()
        verifyFavoriteHero()
    }

    @Test fun testShouldAssetsBeWhiteTrueHunter() {
        demandFavoriteHero(HUNTER)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper)

        assertThat(heroUtils.shouldAssetsBeWhite()).isTrue()
        verifyFavoriteHero()
    }

    @Test fun testShouldAssetsBeWhiteFalseMage() {
        demandFavoriteHero(MAGE)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper)

        assertThat(heroUtils.shouldAssetsBeWhite()).isFalse()
        verifyFavoriteHero()
    }

    @Test fun testShouldAssetsBeWhiteTrueShaman() {
        demandFavoriteHero(SHAMAN)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper)

        assertThat(heroUtils.shouldAssetsBeWhite()).isTrue()
        verifyFavoriteHero()
    }

    @Test fun testShouldAssetsBeWhiteFalsePaladin() {
        demandFavoriteHero(PALADIN)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper)

        assertThat(heroUtils.shouldAssetsBeWhite()).isFalse()
        verifyFavoriteHero()
    }

    @Test fun testShouldAssetsBeWhiteFalsePriest() {
        demandFavoriteHero(PRIEST)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper)

        assertThat(heroUtils.shouldAssetsBeWhite()).isFalse()
        verifyFavoriteHero()
    }

    @Test fun testShouldAssetsBeWhiteTrueDruid() {
        demandFavoriteHero(DRUID)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper)

        assertThat(heroUtils.shouldAssetsBeWhite()).isTrue()
        verifyFavoriteHero()
    }

    @Test fun testShouldAssetsBeWhiteTrueRogue() {
        demandFavoriteHero(ROGUE)
        val heroUtils = HeroUtils(mockSharedPreferencesHelper)

        assertThat(heroUtils.shouldAssetsBeWhite()).isTrue()
        verifyFavoriteHero()
    }

    private fun demandFavoriteHero(hero: Hero) {
        doReturn(hero.toString()).whenever(mockSharedPreferencesHelper).getSavedHeroString()
    }

    private fun verifyFavoriteHero() {
        verify(mockSharedPreferencesHelper).getSavedHeroString()
    }
}
