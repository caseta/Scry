package com.taylorcase.hearthstonescry.model

import android.os.Parcelable
import com.taylorcase.hearthstonescry.model.enums.League
import kotlinx.android.parcel.Parcelize

@Parcelize
open class FilterItem constructor(
        val heroList: List<String> = emptyList(),
        val costList: List<String> = emptyList(),
        val setList: List<String> = emptyList(),
        val league: String = "",
        val rarityList: List<String> = emptyList()
) : Parcelable {

    fun isFilterEmpty(): Boolean {
        return heroList.isEmpty() && costList.isEmpty() &&
                setList.isEmpty() && league.isEmpty() &&
                rarityList.isEmpty()
    }

    fun isCardValid(card: Card): Boolean {
        var setValid = false
        var heroValid = false
        var rarityValid = false
        var costValid = false
        val league = league

        if (setList.isNotEmpty()) {
            loop@ for (set in setList) {
                if (card.cardSet == set) {
                    setValid = true
                    break@loop
                }
            }

            if (!setValid) {
                return false
            }
        }

        if (heroList.isNotEmpty()) {
            loop@ for (hero in heroList) {
                if (card.playerClass == hero) {
                    heroValid = true
                    break@loop
                }
            }

            if (!heroValid) {
                return false
            }
        }

        if (rarityList.isNotEmpty()) {
            loop@ for (rarity in rarityList) {
                if (card.rarity == rarity) {
                    rarityValid = true
                    break@loop
                }
            }

            if (!rarityValid) {
                return false
            }
        }

        if (costList.isNotEmpty()) {
            loop@ for (cost in costList) {
                if (card.cost.toString() == cost) {
                    costValid = true
                    break@loop
                }
                if (cost.toInt() >= 7 && card.cost >= 7) {
                    costValid = true
                    break@loop
                }
            }

            if (!costValid) {
                return false
            }
        }

        if (league.isNotBlank()) {
            if (league == League.STANDARD.toString()) {
                if (!card.isInStandard()) {
                    return false
                }
            }
        }

        return true
    }

    companion object {
        const val FILTER_EXTRA = "filter extra"
    }
}
