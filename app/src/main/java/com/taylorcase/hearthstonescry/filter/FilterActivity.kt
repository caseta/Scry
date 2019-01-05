package com.taylorcase.hearthstonescry.filter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.Checkable
import com.taylorcase.hearthstonescry.CheckableFrameLayout
import com.taylorcase.hearthstonescry.R
import com.taylorcase.hearthstonescry.ScryApplication
import com.taylorcase.hearthstonescry.base.BaseActivity
import com.taylorcase.hearthstonescry.base.InjectLayout
import com.taylorcase.hearthstonescry.model.FilterItem
import com.taylorcase.hearthstonescry.model.enums.Cost
import com.taylorcase.hearthstonescry.model.enums.Cost.*
import com.taylorcase.hearthstonescry.model.enums.Hero
import com.taylorcase.hearthstonescry.model.enums.Hero.*
import com.taylorcase.hearthstonescry.model.enums.League
import com.taylorcase.hearthstonescry.model.enums.Rarity.*
import com.taylorcase.hearthstonescry.model.enums.Sets.*
import com.taylorcase.hearthstonescry.utils.*
import kotlinx.android.synthetic.main.activity_filter.*
import kotlinx.android.synthetic.main.include_toolbar.*

open class FilterActivity : BaseActivity(), View.OnClickListener {

    @VisibleForTesting lateinit var allFilters: Array<Checkable>

    @VisibleForTesting private var filterItem: FilterItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as ScryApplication).getAppComponent()?.inject(this)
        setupToolbar(toolbar, getString(R.string.filter), MODAL)

        populateAllFilters()
        setAllFilterClickListeners()

        filterItem = savedInstanceState?.getParcelable(FilterItem.FILTER_EXTRA)
        if (filterItem == null) filterItem = intent?.extras?.getParcelable(FilterItem.FILTER_EXTRA)
        if (filterItem == null) filterItem = FilterItem()
    }

    override fun provideContentLayoutId(): Int {
        return R.layout.activity_filter
    }

    private fun setAllFilterClickListeners() {
        filter_apply_button.setOnClickListener(this)
        for (filter in allFilters) {
            if (filter is CheckBox) {
                filter.setOnClickListener(this)
            } else {
                (filter as CheckableFrameLayout).setOnClickListener(this)
            }
        }
    }

    public override fun onResume() {
        super.onResume()

        filterItem?.let {
            populateHero(it)
            populateCost(it)
            populateSets(it)
            populateLeague(it)
            populateRarity(it)
        }

        toggleApplyVisibility()
    }

    private fun populateHero(filterItem: FilterItem) {
        val heroList = filterItem.heroList
        if (heroList.isNotEmpty()) {
            for (hero in heroList) {
                if (hero == Hero.WARLOCK.toString()) filter_warlock.makeChecked()
                if (hero == Hero.WARRIOR.toString()) filter_warrior.makeChecked()
                if (hero == Hero.MAGE.toString()) filter_mage.makeChecked()
                if (hero == Hero.PALADIN.toString()) filter_paladin.makeChecked()
                if (hero == Hero.PRIEST.toString()) filter_priest.makeChecked()
                if (hero == Hero.SHAMAN.toString()) filter_shaman.makeChecked()
                if (hero == Hero.DRUID.toString()) filter_druid.makeChecked()
                if (hero == Hero.HUNTER.toString()) filter_hunter.makeChecked()
                if (hero == Hero.ROGUE.toString()) filter_rogue.makeChecked()
                if (hero == Hero.NEUTRAL.toString()) filter_neutral.makeChecked()
            }
        }
    }

    private fun populateCost(filterItem: FilterItem) {
        val costList = filterItem.costList
        if (costList.isNotEmpty()) {
            for (cost in costList) {
                if (cost == Cost.ONE.toString()) filter_one.makeChecked()
                if (cost == Cost.TWO.toString()) filter_two.makeChecked()
                if (cost == Cost.THREE.toString()) filter_three.makeChecked()
                if (cost == Cost.FOUR.toString()) filter_four.makeChecked()
                if (cost == Cost.FIVE.toString()) filter_five.makeChecked()
                if (cost == Cost.SIX.toString()) filter_six.makeChecked()
                if (cost == Cost.SEVEN.toString()) filter_seven.makeChecked()
            }
        }
    }

    private fun populateSets(filterItem: FilterItem) {
        val setList = filterItem.setList
        if (setList.isNotEmpty()) {
            for (set in setList) {
                if (set == HALL_OF_FAME.toString()) filter_hall_of_fame.makeChecked()
                if (set == BASIC.toString()) filter_basic.makeChecked()
                if (set == CLASSIC.toString()) filter_classic.makeChecked()
                if (set == CURSE_OF_NAXXRAMAS.toString()) filter_naxx.makeChecked()
                if (set == GOBLINS_VS_GNOMES.toString()) filter_goblins.makeChecked()
                if (set == BLACKROCK_MOUNTAIN.toString()) filter_blackrock.makeChecked()
                if (set == THE_GRAND_TOURNAMENT.toString()) filter_grand_tournament.makeChecked()
                if (set == LEAGUE_OF_EXPLORERS.toString()) filter_league_of_explorers.makeChecked()
                if (set == WHISPERS_OF_THE_OLD_GODS.toString()) filter_whispers.makeChecked()
                if (set == ONE_NIGHT_IN_KARAZHAN.toString()) filter_one_night.makeChecked()
                if (set == MEAN_STREETS_OF_GADGETZAN.toString()) filter_mean_streets.makeChecked()
                if (set == JOURNEY_TO_UNGORO.toString()) filter_journey.makeChecked()
                if (set == KNIGHTS_OF_THE_FRONZE_THRONE.toString()) filter_knights.makeChecked()
                if (set == KOBOLDS_AND_CATACOMBS.toString()) filter_kobolds.makeChecked()
                if (set == THE_WITCHWOOD.toString()) filter_witchwood.makeChecked()
                if (set == BOOMSDAY_PROJECT.toString()) filter_boomsday_project.makeChecked()
                if (set == RASTAKHANS_RUMBLE.toString()) filter_rastakhans_rumble.makeChecked()
            }
        }
    }

    private fun populateLeague(filterItem: FilterItem) {
        val league = filterItem.league
        if (!league.isBlank()) {
            if (league == League.STANDARD.toString()) {
                filter_standard.makeChecked()
            } else {
                filter_wild.makeChecked()
            }
        }
    }

    private fun populateRarity(filterItem: FilterItem) {
        val rarityList = filterItem.rarityList
        if (rarityList.isNotEmpty()) {
            for (rarity in rarityList) {
                if (rarity == FREE.toString()) filter_free.makeChecked()
                if (rarity == COMMON.toString()) filter_common.makeChecked()
                if (rarity == RARE.toString()) filter_rare.makeChecked()
                if (rarity == EPIC.toString()) filter_epic.makeChecked()
                if (rarity == LEGENDARY.toString()) filter_legendary.makeChecked()
            }
        }
    }

    override fun onClick(v: View?) {
        val id = v?.id
        if (id == R.id.filter_apply_button) {
            val intent = Intent()
            intent.putExtra(FILTER_DATA, createFilterItem())
            setResult(Activity.RESULT_OK, intent)
            finish()
        } else if (id == R.id.filter_standard || id == R.id.filter_wild) {
            if (id == R.id.filter_standard) {
                filter_wild.makeUnChecked()
            } else {
                filter_standard.makeUnChecked()
            }
            toggleApplyVisibility()
        } else if (id == R.id.home) {
            onBackPressed()
        } else {
            toggleApplyVisibility()
        }
    }

    override fun onBackPressed() {
        if (allFiltersDeselected()) {
            intent.putExtra(FILTER_DATA, createFilterItem())
            setResult(Activity.RESULT_OK, intent)
        }
        finish()
    }

    private fun createFilterItem() : FilterItem {
        return FilterItem(createHeroList(), createCostList(), createSetList(), createLeague(), createRarityList())
    }

    private fun createHeroList(): ArrayList<String> {
        val heroList = ArrayList<String>()

        if (filter_druid.isChecked) heroList.add(DRUID.toString())
        if (filter_hunter.isChecked) heroList.add(HUNTER.toString())
        if (filter_mage.isChecked) heroList.add(MAGE.toString())
        if (filter_paladin.isChecked) heroList.add(PALADIN.toString())
        if (filter_priest.isChecked) heroList.add(PRIEST.toString())
        if (filter_rogue.isChecked) heroList.add(ROGUE.toString())
        if (filter_shaman.isChecked) heroList.add(SHAMAN.toString())
        if (filter_warlock.isChecked) heroList.add(WARLOCK.toString())
        if (filter_warrior.isChecked) heroList.add(WARRIOR.toString())
        if (filter_neutral.isChecked) heroList.add(NEUTRAL.toString())

        return heroList
    }

    private fun createSetList(): ArrayList<String> {
        val setList = ArrayList<String>()

        if (filter_hall_of_fame.isChecked) setList.add(HALL_OF_FAME.toString())
        if (filter_basic.isChecked) setList.add(BASIC.toString())
        if (filter_classic.isChecked) setList.add(CLASSIC.toString())
        if (filter_naxx.isChecked) setList.add(CURSE_OF_NAXXRAMAS.toString())
        if (filter_goblins.isChecked) setList.add(GOBLINS_VS_GNOMES.toString())
        if (filter_blackrock.isChecked) setList.add(BLACKROCK_MOUNTAIN.toString())
        if (filter_grand_tournament.isChecked) setList.add(THE_GRAND_TOURNAMENT.toString())
        if (filter_league_of_explorers.isChecked) setList.add(LEAGUE_OF_EXPLORERS.toString())
        if (filter_whispers.isChecked) setList.add(WHISPERS_OF_THE_OLD_GODS.toString())
        if (filter_one_night.isChecked) setList.add(ONE_NIGHT_IN_KARAZHAN.toString())
        if (filter_mean_streets.isChecked) setList.add(MEAN_STREETS_OF_GADGETZAN.toString())
        if (filter_journey.isChecked) setList.add(JOURNEY_TO_UNGORO.toString())
        if (filter_knights.isChecked) setList.add(KNIGHTS_OF_THE_FRONZE_THRONE.toString())
        if (filter_kobolds.isChecked) setList.add(KOBOLDS_AND_CATACOMBS.toString())
        if (filter_witchwood.isChecked) setList.add(THE_WITCHWOOD.toString())
        if (filter_boomsday_project.isChecked) setList.add(BOOMSDAY_PROJECT.toString())
        if (filter_rastakhans_rumble.isChecked) setList.add(RASTAKHANS_RUMBLE.toString())

        return setList
    }

    private fun createCostList(): ArrayList<String> {
        val costList = ArrayList<String>()

        if (filter_one.isChecked) costList.add(ONE.toString())
        if (filter_two.isChecked) costList.add(TWO.toString())
        if (filter_three.isChecked) costList.add(THREE.toString())
        if (filter_four.isChecked) costList.add(FOUR.toString())
        if (filter_five.isChecked) costList.add(FIVE.toString())
        if (filter_six.isChecked) costList.add(SIX.toString())
        if (filter_seven.isChecked) costList.add(SEVEN.toString())

        return costList
    }

    private fun createRarityList(): ArrayList<String> {
        val rarityList = ArrayList<String>()

        if (filter_free.isChecked) rarityList.add(FREE.toString())
        if (filter_common.isChecked) rarityList.add(COMMON.toString())
        if (filter_rare.isChecked) rarityList.add(RARE.toString())
        if (filter_epic.isChecked) rarityList.add(EPIC.toString())
        if (filter_legendary.isChecked) rarityList.add(LEGENDARY.toString())

        return rarityList
    }

    private fun createLeague(): String {
        var league = ""
        if (filter_standard.isChecked) { league = League.STANDARD.toString() }
        if (filter_wild.isChecked) { league = League.WILD.toString() }

        return league
    }

    private fun toggleApplyVisibility() {
        if (allFiltersDeselected()) {
            filter_apply_container.makeGone()
        } else {
            filter_apply_container.makeVisible()
        }
    }

    private fun allFiltersDeselected(): Boolean {
        for (filter in allFilters) {
            if (filter.isChecked) {
                return false
            }
        }
        return true
    }

    private fun populateAllFilters() {
        allFilters = arrayOf(filter_druid, filter_hunter, filter_mage, filter_paladin, filter_priest,
                filter_rogue, filter_shaman, filter_warlock, filter_warrior, filter_neutral, filter_hall_of_fame,
                filter_basic, filter_classic, filter_naxx, filter_goblins, filter_blackrock, filter_grand_tournament,
                filter_league_of_explorers, filter_whispers, filter_one_night, filter_mean_streets, filter_journey,
                filter_knights, filter_kobolds, filter_witchwood, filter_boomsday_project, filter_rastakhans_rumble,
                filter_one, filter_two, filter_three, filter_four, filter_five, filter_six, filter_seven, filter_free,
                filter_common, filter_rare, filter_epic, filter_legendary, filter_wild, filter_standard)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_clear_filter) {
            for (filter in allFilters) {
                filter.makeUnChecked()
            }
            filter_apply_container.makeGone()
            filterItem = FilterItem()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(bundle: Bundle?) {
        super.onSaveInstanceState(bundle)

        bundle?.putParcelable(FilterItem.FILTER_EXTRA, createFilterItem())
    }

    companion object {
        const val FILTER_DATA = "filter data"
    }
}
