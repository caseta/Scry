package com.taylorcase.hearthstonescry.selecthero

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.taylorcase.hearthstonescry.R
import com.taylorcase.hearthstonescry.model.enums.Hero.*
import com.taylorcase.hearthstonescry.utils.HeroUtils
import com.taylorcase.hearthstonescry.utils.ImageLoader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class SelectHeroAdapter @Inject constructor(
        private val imageLoader: ImageLoader,
        private val heroUtils: HeroUtils
) : RecyclerView.Adapter<SelectHeroViewHolder>() {

    private var heroList = listOf(WARLOCK, DRUID, HUNTER, MAGE, PALADIN, PRIEST, ROGUE, SHAMAN, WARRIOR)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectHeroViewHolder {
        return SelectHeroViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_select_hero_card, parent, false)
                , imageLoader, heroUtils)
    }

    override fun onBindViewHolder(holder: SelectHeroViewHolder, position: Int) {
        holder.loadHero(heroList[position])
    }

    override fun getItemCount() = heroList.size

    open fun getSelectedItem(position: Int) = heroList[position]
}
