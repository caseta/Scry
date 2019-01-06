package com.taylorcase.hearthstonescry.filter

import android.app.Activity.RESULT_OK
import android.content.Intent
import com.taylorcase.hearthstonescry.model.FilterItem
import com.taylorcase.hearthstonescry.model.enums.*
import com.taylorcase.hearthstonescry.utils.makeChecked
import kotlinx.android.synthetic.main.activity_filter.*
import org.assertj.core.api.Assertions.*
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric.*
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.shadows.ShadowActivity

@RunWith(RobolectricTestRunner::class)
class FilterActivityTest {

    private lateinit var activity: FilterActivity

    @Test
    fun testOnResumeSetsWarlockCheckedProperly() {
        val filterItem = FilterItem(heroList = arrayListOf(Hero.WARLOCK.toString()))
        val intent = Intent().putExtra(FilterItem.FILTER_EXTRA, filterItem)
        activity = buildActivity(FilterActivity::class.java, intent).create().visible().get()

        activity.onResume()

        assertThat(activity.filter_warlock.isChecked).isTrue()
    }

    @Test
    fun testOnResumeSetsNeutralCheckedProperly() {
        val filterItem = FilterItem(heroList = arrayListOf(Hero.NEUTRAL.toString()))
        val intent = Intent().putExtra(FilterItem.FILTER_EXTRA, filterItem)
        activity = buildActivity(FilterActivity::class.java, intent).create().visible().get()

        activity.onResume()

        assertThat(activity.filter_neutral.isChecked).isTrue()
    }

    @Test
    fun testOnResumeSetsCostCheckedProperly() {
        val filterItem = FilterItem(costList = arrayListOf(Cost.SEVEN.toString()))
        val intent = Intent().putExtra(FilterItem.FILTER_EXTRA, filterItem)
        activity = buildActivity(FilterActivity::class.java, intent).create().visible().get()

        activity.onResume()

        assertThat(activity.filter_seven.isChecked).isTrue()
    }

    @Test
    fun testOnResumeSetsRastaRumbleCheckedProperly() {
        val filterItem = FilterItem(setList = arrayListOf(Sets.RASTAKHANS_RUMBLE.toString()))
        val intent = Intent().putExtra(FilterItem.FILTER_EXTRA, filterItem)
        activity = buildActivity(FilterActivity::class.java, intent).create().visible().get()

        activity.onResume()

        assertThat(activity.filter_rastakhans_rumble.isChecked).isTrue()
    }

    @Test
    fun testOnResumeSetsStandardCheckedProperly() {
        val filterItem = FilterItem(league = League.STANDARD.toString())
        val intent = Intent().putExtra(FilterItem.FILTER_EXTRA, filterItem)
        activity = buildActivity(FilterActivity::class.java, intent).create().visible().get()

        activity.onResume()

        assertThat(activity.filter_standard.isChecked).isTrue()
    }

    @Test
    fun testOnResumeSetsLegendaryCheckedProperly() {
        val filterItem = FilterItem(rarityList = arrayListOf(Rarity.LEGENDARY.toString()))
        val intent = Intent().putExtra(FilterItem.FILTER_EXTRA, filterItem)
        activity = buildActivity(FilterActivity::class.java, intent).create().visible().get()

        activity.onResume()

        assertThat(activity.filter_legendary.isChecked).isTrue()
    }

    @Test
    fun testOnClickApplyFilterAddsDruid() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_druid.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithHero(Hero.DRUID, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsHunter() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_hunter.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithHero(Hero.HUNTER, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsMage() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_mage.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithHero(Hero.MAGE, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsPaladin() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_paladin.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithHero(Hero.PALADIN, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsPriest() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_priest.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithHero(Hero.PRIEST, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsRogue() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_rogue.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithHero(Hero.ROGUE, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsShaman() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_shaman.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithHero(Hero.SHAMAN, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsWarlock() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_warlock.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithHero(Hero.WARLOCK, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsWarrior() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_warrior.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithHero(Hero.WARRIOR, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsOne() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_one.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithCost(Cost.ONE, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsTwo() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_two.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithCost(Cost.TWO, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsThree() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_three.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithCost(Cost.THREE, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsFour() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_four.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithCost(Cost.FOUR, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsFive() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_five.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithCost(Cost.FIVE, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsSix() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_six.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithCost(Cost.SIX, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsSeven() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_seven.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithCost(Cost.SEVEN, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsHallOfFame() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_hall_of_fame.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithSet(Sets.HALL_OF_FAME, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsBasic() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_basic.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithSet(Sets.BASIC, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsClassic() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_classic.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithSet(Sets.CLASSIC, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsNaxx() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_naxx.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithSet(Sets.CURSE_OF_NAXXRAMAS, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsGoblins() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_goblins.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithSet(Sets.GOBLINS_VS_GNOMES, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsBlackRock() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_blackrock.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithSet(Sets.BLACKROCK_MOUNTAIN, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsGrandTournament() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_grand_tournament.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithSet(Sets.THE_GRAND_TOURNAMENT, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsLeague() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_league_of_explorers.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithSet(Sets.LEAGUE_OF_EXPLORERS, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsWhispers() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_whispers.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithSet(Sets.WHISPERS_OF_THE_OLD_GODS, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsKarazhan() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_one_night.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithSet(Sets.ONE_NIGHT_IN_KARAZHAN, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsMeanStreets() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_mean_streets.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithSet(Sets.MEAN_STREETS_OF_GADGETZAN, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsJourney() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_journey.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithSet(Sets.JOURNEY_TO_UNGORO, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsKnights() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_knights.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithSet(Sets.KNIGHTS_OF_THE_FRONZE_THRONE, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsKobolds() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_kobolds.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithSet(Sets.KOBOLDS_AND_CATACOMBS, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsWitchwood() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_witchwood.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithSet(Sets.THE_WITCHWOOD, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsBoomsday() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_boomsday_project.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithSet(Sets.BOOMSDAY_PROJECT, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsRastakhansRumble() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_rastakhans_rumble.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithSet(Sets.RASTAKHANS_RUMBLE, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsStandard() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_standard.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithLeague(League.STANDARD, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsWild() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_wild.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithLeague(League.WILD, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsFree() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_free.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithRarity(Rarity.FREE, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsCommon() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_common.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithRarity(Rarity.COMMON, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsRare() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_rare.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithRarity(Rarity.RARE, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsEpic() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_epic.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithRarity(Rarity.EPIC, shadowActivity)
    }

    @Test
    fun testOnClickApplyFilterAddsLegendary() {
        activity = buildActivity(FilterActivity::class.java).create().get()
        activity.filter_legendary.makeChecked()
        val shadowActivity = Shadows.shadowOf(activity)

        activity.onClick(activity.filter_apply_button)

        assertFinishWithRarity(Rarity.LEGENDARY, shadowActivity)
    }

    @Test
    fun testFilterApplyClickListenerIsSet() {
        activity = buildActivity(FilterActivity::class.java).create().get()

        assertThat(activity.allFilters).hasSize(FILTER_COUNT)
        assertThat(activity.filter_apply_button.hasOnClickListeners())
        assertThat(activity.filter_druid.hasOnClickListeners())
        assertThat(activity.filter_hunter.hasOnClickListeners())
        assertThat(activity.filter_mage.hasOnClickListeners())
        assertThat(activity.filter_paladin.hasOnClickListeners())
        assertThat(activity.filter_priest.hasOnClickListeners())
        assertThat(activity.filter_rogue.hasOnClickListeners())
        assertThat(activity.filter_shaman.hasOnClickListeners())
        assertThat(activity.filter_warlock.hasOnClickListeners())
        assertThat(activity.filter_warrior.hasOnClickListeners())
        assertThat(activity.filter_neutral.hasOnClickListeners())
        assertThat(activity.filter_hall_of_fame.hasOnClickListeners())
        assertThat(activity.filter_basic.hasOnClickListeners())
        assertThat(activity.filter_classic.hasOnClickListeners())
        assertThat(activity.filter_naxx.hasOnClickListeners())
        assertThat(activity.filter_goblins.hasOnClickListeners())
        assertThat(activity.filter_blackrock.hasOnClickListeners())
        assertThat(activity.filter_grand_tournament.hasOnClickListeners())
        assertThat(activity.filter_league_of_explorers.hasOnClickListeners())
        assertThat(activity.filter_whispers.hasOnClickListeners())
        assertThat(activity.filter_one_night.hasOnClickListeners())
        assertThat(activity.filter_mean_streets.hasOnClickListeners())
        assertThat(activity.filter_journey.hasOnClickListeners())
        assertThat(activity.filter_knights.hasOnClickListeners())
        assertThat(activity.filter_kobolds.hasOnClickListeners())
        assertThat(activity.filter_witchwood.hasOnClickListeners())
        assertThat(activity.filter_boomsday_project.hasOnClickListeners())
        assertThat(activity.filter_rastakhans_rumble.hasOnClickListeners())
        assertThat(activity.filter_one.hasOnClickListeners())
        assertThat(activity.filter_two.hasOnClickListeners())
        assertThat(activity.filter_three.hasOnClickListeners())
        assertThat(activity.filter_four.hasOnClickListeners())
        assertThat(activity.filter_five.hasOnClickListeners())
        assertThat(activity.filter_six.hasOnClickListeners())
        assertThat(activity.filter_seven.hasOnClickListeners())
        assertThat(activity.filter_free.hasOnClickListeners())
        assertThat(activity.filter_common.hasOnClickListeners())
        assertThat(activity.filter_rare.hasOnClickListeners())
        assertThat(activity.filter_epic.hasOnClickListeners())
        assertThat(activity.filter_legendary.hasOnClickListeners())
        assertThat(activity.filter_wild.hasOnClickListeners())
        assertThat(activity.filter_standard.hasOnClickListeners())
    }

    private fun assertFinishWithHero(hero: Hero, shadowActivity: ShadowActivity) {
        assertIsFinishingAndResultOk(shadowActivity)
        val filterItem = shadowActivity.resultIntent.getParcelableExtra<FilterItem>(FilterActivity.FILTER_DATA)
        assertThat(filterItem.heroList[0]).isEqualTo(hero.toString())
    }

    private fun assertFinishWithCost(cost: Cost, shadowActivity: ShadowActivity) {
        assertIsFinishingAndResultOk(shadowActivity)
        val filterItem = shadowActivity.resultIntent.getParcelableExtra<FilterItem>(FilterActivity.FILTER_DATA)
        assertThat(filterItem.costList[0]).isEqualTo(cost.toString())
    }

    private fun assertFinishWithSet(set: Sets, shadowActivity: ShadowActivity) {
        assertIsFinishingAndResultOk(shadowActivity)
        val filterItem = shadowActivity.resultIntent.getParcelableExtra<FilterItem>(FilterActivity.FILTER_DATA)
        assertThat(filterItem.setList[0]).isEqualTo(set.toString())
    }

    private fun assertFinishWithLeague(league: League, shadowActivity: ShadowActivity) {
        assertIsFinishingAndResultOk(shadowActivity)
        val filterItem = shadowActivity.resultIntent.getParcelableExtra<FilterItem>(FilterActivity.FILTER_DATA)
        assertThat(filterItem.league).isEqualTo(league.toString())
    }

    private fun assertFinishWithRarity(rarity: Rarity, shadowActivity: ShadowActivity) {
        assertIsFinishingAndResultOk(shadowActivity)
        val filterItem = shadowActivity.resultIntent.getParcelableExtra<FilterItem>(FilterActivity.FILTER_DATA)
        assertThat(filterItem.rarityList[0]).isEqualTo(rarity.toString())
    }

    private fun assertIsFinishingAndResultOk(shadowActivity: ShadowActivity) {
        assertThat(shadowActivity.isFinishing)
        assertThat(RESULT_OK).isEqualTo(shadowActivity.resultCode)
    }

    @After
    fun destroyActivity() {
        activity.finish()
    }

    private companion object {
        private const val FILTER_COUNT = 41
    }
}