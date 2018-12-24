package com.taylorcase.hearthstonescry.model.enums

enum class League constructor(private val stringValue: String) {

    STANDARD("Standard"),
    WILD("Wild");

    override fun toString(): String {
        return stringValue
    }
}
