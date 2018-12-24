package com.taylorcase.hearthstonescry.model

import com.taylorcase.hearthstonescry.model.enums.*
import org.assertj.core.api.Assertions.*
import org.junit.Test

class FilterItemTest {

    @Test fun testIsFilterEmptyIsTrue() {
        val filterItem = FilterItem()

        assertThat(filterItem.isFilterEmpty()).isTrue()
    }

    @Test fun testIsFilterEmptyIsFalseWithHero() {
        val filterItem = FilterItem(heroList = arrayListOf(Hero.WARLOCK.toString()))

        assertThat(filterItem.isFilterEmpty()).isFalse()
    }

    @Test fun testIsFilterEmptyIsFalseWithCost() {
        val filterItem = FilterItem(costList = arrayListOf(Cost.SEVEN.toString()))

        assertThat(filterItem.isFilterEmpty()).isFalse()
    }

    @Test fun testIsFilterEmptyIsFalseWithSet() {
        val filterItem = FilterItem(setList = arrayListOf(Sets.RASTAKHANS_RUMBLE.toString()))

        assertThat(filterItem.isFilterEmpty()).isFalse()
    }

    @Test fun testIsFilterEmptyIsFalseWithLeague() {
        val filterItem = FilterItem(league = League.STANDARD.toString())

        assertThat(filterItem.isFilterEmpty()).isFalse()
    }

    @Test fun testIsFilterEmptyIsFalseWithRarity() {
        val filterItem = FilterItem(rarityList = arrayListOf(Rarity.LEGENDARY.toString()))

        assertThat(filterItem.isFilterEmpty()).isFalse()
    }

    @Test fun testIsCardValidAllFieldsTrue() {
        val filterItem = demandMalganisFilterItem()

        val card = Card()
        card.playerClass = Hero.WARLOCK.toString()
        card.cost = 9
        card.cardSet = Sets.GOBLINS_VS_GNOMES.toString()
        card.rarity = Rarity.LEGENDARY.toString()

        assertThat(filterItem.isCardValid(card)).isTrue()
    }

    @Test fun testIsCardValidAllFieldsTrueLowCost() {
        val filterItem = FilterItem(
                arrayListOf(Hero.WARLOCK.toString()),
                arrayListOf(Cost.TWO.toString()),
                arrayListOf(Sets.GOBLINS_VS_GNOMES.toString()),
                League.WILD.toString(),
                arrayListOf(Rarity.LEGENDARY.toString()))

        val card = Card()
        card.playerClass = Hero.WARLOCK.toString()
        card.cost = 2
        card.cardSet = Sets.GOBLINS_VS_GNOMES.toString()
        card.rarity = Rarity.LEGENDARY.toString()

        assertThat(filterItem.isCardValid(card)).isTrue()
    }

    @Test fun testIsCardValidIsFalseBadSet() {
        val filterItem = demandMalganisFilterItem()

        val card = Card()
        card.playerClass = Hero.WARLOCK.toString()
        card.cost = 9
        card.cardSet = Sets.CLASSIC.toString()
        card.rarity = Rarity.LEGENDARY.toString()

        assertThat(filterItem.isCardValid(card)).isFalse()
    }

    @Test fun testIsCardValidIsFalseBadHero() {
        val filterItem = demandMalganisFilterItem()

        val card = Card()
        card.playerClass = Hero.WARRIOR.toString()
        card.cost = 9
        card.cardSet = Sets.GOBLINS_VS_GNOMES.toString()
        card.rarity = Rarity.LEGENDARY.toString()

        assertThat(filterItem.isCardValid(card)).isFalse()
    }

    @Test fun testIsCardValidIsFalseBadRarity() {
        val filterItem = demandMalganisFilterItem()

        val card = Card()
        card.playerClass = Hero.WARLOCK.toString()
        card.cost = 9
        card.cardSet = Sets.GOBLINS_VS_GNOMES.toString()
        card.rarity = Rarity.RARE.toString()

        assertThat(filterItem.isCardValid(card)).isFalse()
    }

    @Test fun testIsCardValidIsFalseBadCost() {
        val filterItem = demandMalganisFilterItem()

        val card = Card()
        card.playerClass = Hero.WARLOCK.toString()
        card.cost = 1
        card.cardSet = Sets.GOBLINS_VS_GNOMES.toString()
        card.rarity = Rarity.LEGENDARY.toString()

        assertThat(filterItem.isCardValid(card)).isFalse()
    }

    @Test fun testIsCardValidIsFalseBadLeague() {
        val filterItem = FilterItem(
                arrayListOf(Hero.WARLOCK.toString()),
                arrayListOf(Cost.SEVEN.toString()),
                arrayListOf(Sets.GOBLINS_VS_GNOMES.toString()),
                League.STANDARD.toString(),
                arrayListOf(Rarity.LEGENDARY.toString()))

        val card = Card()
        card.playerClass = Hero.WARLOCK.toString()
        card.cost = 9
        card.cardSet = Sets.GOBLINS_VS_GNOMES.toString()
        card.rarity = Rarity.LEGENDARY.toString()

        assertThat(filterItem.isCardValid(card)).isFalse()
    }

    private fun demandMalganisFilterItem(): FilterItem {
        return FilterItem(
                arrayListOf(Hero.WARLOCK.toString()),
                arrayListOf(Cost.SEVEN.toString()),
                arrayListOf(Sets.GOBLINS_VS_GNOMES.toString()),
                League.WILD.toString(),
                arrayListOf(Rarity.LEGENDARY.toString()))
    }
}
