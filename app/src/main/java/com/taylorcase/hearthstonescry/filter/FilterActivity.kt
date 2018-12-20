package com.taylorcase.hearthstonescry.filter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.Checkable
import com.taylorcase.hearthstonescry.R
import com.taylorcase.hearthstonescry.ScryApplication
import com.taylorcase.hearthstonescry.base.BaseActivity
import com.taylorcase.hearthstonescry.base.InjectLayout
import com.taylorcase.hearthstonescry.model.FilterItem
import com.taylorcase.hearthstonescry.model.enums.Cost.*
import com.taylorcase.hearthstonescry.model.enums.Hero.*
import com.taylorcase.hearthstonescry.model.enums.Rarity.*
import com.taylorcase.hearthstonescry.model.enums.Sets.*
import com.taylorcase.hearthstonescry.utils.isVisible
import com.taylorcase.hearthstonescry.utils.makeGone
import com.taylorcase.hearthstonescry.utils.makeVisible
import kotlinx.android.synthetic.main.activity_filter.*
import kotlinx.android.synthetic.main.include_toolbar.*
import javax.inject.Inject

@InjectLayout(R.layout.activity_filter)
open class FilterActivity : BaseActivity(), FilterContract.View, View.OnClickListener {

    @Inject lateinit var presenter: FilterContract.Presenter

    private lateinit var allFilters: Array<Checkable>

    var filterItem: FilterItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as ScryApplication).getAppComponent()?.inject(this)
        setupToolbar(toolbar, getString(R.string.filter), BACK_ARROW)
//        presenter.attach(this)

        populateAllFilters()

        filter_apply_button.setOnClickListener(this)
        for (filter in allFilters) {
            if (filter is CheckBox) {
                filter.setOnClickListener(this)
            } else {
                (filter as CheckableFrameLayout).setOnClickListener(this)
            }
        }
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

    override fun onClick(v: View?) {
        if (v?.id == R.id.filter_apply_button) {
            val intent = Intent()
            intent.putExtra(FILTER_DATA, createFilterItem())
            setResult(Activity.RESULT_OK, intent)
            finish()
        } else {
            toggleApplyVisibility()
        }
    }

    private fun createFilterItem() : FilterItem {
        val heroList = ArrayList<String>()
        val setList = ArrayList<String>()
        val costList = ArrayList<String>()
        val rarityList = ArrayList<String>()

        // hero
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

        // sets
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

        // cost
        if (filter_one.isChecked) costList.add(ONE.toString())
        if (filter_two.isChecked) costList.add(TWO.toString())
        if (filter_three.isChecked) costList.add(THREE.toString())
        if (filter_four.isChecked) costList.add(FOUR.toString())
        if (filter_five.isChecked) costList.add(FIVE.toString())
        if (filter_six.isChecked) costList.add(SIX.toString())
        if (filter_seven.isChecked) costList.add(SEVEN.toString())

        // rarity
        if (filter_free.isChecked) rarityList.add(FREE.toString())
        if (filter_common.isChecked) rarityList.add(COMMON.toString())
        if (filter_rare.isChecked) rarityList.add(RARE.toString())
        if (filter_epic.isChecked) rarityList.add(EPIC.toString())
        if (filter_legendary.isChecked) rarityList.add(LEGENDARY.toString())

        var isStandard = false
        if (filter_standard.isChecked) {
            isStandard = true
        }

        return FilterItem(heroList, costList, setList, isStandard, rarityList)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_clear_filter) {
            for (filter in allFilters) {
                filter.isChecked = false
            }
            filter_apply_container.makeGone()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun toggleApplyVisibility() {
        if (filter_apply_container.isVisible() && allFiltersDeselected()) {
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

    public override fun onDestroy() {
        super.onDestroy()
//        presenter.detach()
    }

    companion object {
        const val FILTER_DATA = "filter data"
    }
}
