package com.taylorcase.hearthstonescry.model.enums

enum class Cost constructor(private val stringValue: String) {

    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("+7");

    override fun toString(): String {
        return stringValue
    }
}
