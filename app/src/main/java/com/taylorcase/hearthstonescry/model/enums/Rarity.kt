package com.taylorcase.hearthstonescry.model.enums

enum class Rarity constructor(private val stringValue: String) {

    FREE("Free"),
    COMMON("Common"),
    RARE("Rare"),
    EPIC("Epic"),
    LEGENDARY("Legendary");

    override fun toString(): String {
        return stringValue
    }

}
