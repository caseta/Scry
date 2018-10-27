package com.taylorcase.hearthstonescry.model.enums

enum class Hero constructor(private val stringValue: String) {

    HUNTER("Hunter"),
    MAGE("Mage"),
    WARRIOR("Warrior"),
    PRIEST("Priest"),
    PALADIN("Paladin"),
    ROGUE("Rogue"),
    WARLOCK("Warlock"),
    SHAMAN("Shaman"),
    DRUID("Druid"),
    NEUTRAL("Neutral");

    override fun toString(): String {
        return stringValue
    }
}
