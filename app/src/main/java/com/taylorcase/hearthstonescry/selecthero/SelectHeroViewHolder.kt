package com.taylorcase.hearthstonescry.selecthero

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import com.taylorcase.hearthstonescry.R
import com.taylorcase.hearthstonescry.model.enums.Hero
import com.taylorcase.hearthstonescry.utils.HeroUtils
import com.taylorcase.hearthstonescry.utils.ImageLoader
import kotlinx.android.synthetic.main.item_select_hero_card.view.*

open class SelectHeroViewHolder constructor(itemView: View, var imageLoader: ImageLoader, var heroUtils: HeroUtils) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    companion object {
        const val EXTRA_HERO = "hero"
    }

    private var context: Context = itemView.context
    private var hero: Hero? = null

    open fun loadHero(hero: Hero) {
        this.hero = hero
        this.context = itemView.context
        itemView.setOnClickListener(this)
        itemView.select_hero_description_toggle.setOnClickListener(this)
        setItemViewEnable(true)

        imageLoader.loadDrawableCenterCrop(heroUtils.getHeroIconForHero(hero), itemView.select_hero_icon_image)
        imageLoader.loadDrawableCenterCrop(heroUtils.getHeroImageForHero(hero), itemView.select_hero_image)

        itemView.select_hero_header_text.text = hero.toString()
        itemView.select_hero_sub_header_text.text = heroUtils.getDescriptionForHero(hero)
    }

    override fun onClick(v: View?) {
        val id = v?.id
        if (id == itemView.select_hero_description_toggle.id) {
            if (itemView.select_hero_sub_header_text.visibility == View.VISIBLE) {
                itemView.select_hero_sub_header_text.visibility = View.GONE
                itemView.select_hero_description_toggle.setImageDrawable(itemView.resources.getDrawable(R.drawable.ic_keyboard_arrow_up_black_24dp))
            } else {
                itemView.select_hero_sub_header_text.visibility = View.VISIBLE
                itemView.select_hero_description_toggle.setImageDrawable(itemView.resources.getDrawable(R.drawable.ic_keyboard_arrow_down_black_24dp))
            }
        } else {
            setItemViewEnable(false)
            context.startActivity(Intent(context, DetailedSelectHeroActivity::class.java)
                    .putExtra(EXTRA_HERO, this.hero.toString()))
            setItemViewEnable(true)
        }
    }

    private fun setItemViewEnable(isEnabled: Boolean) {
        itemView.isEnabled = isEnabled
        itemView.isClickable = isEnabled
    }
}
