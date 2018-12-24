package com.taylorcase.hearthstonescry.model.enums

enum class Sets(private val stringValue: String) {

    BASIC("Basic"),
    CLASSIC("Classic"),
    CURSE_OF_NAXXRAMAS("Naxxramas"),
    GOBLINS_VS_GNOMES("Goblins vs Gnomes"),
    BLACKROCK_MOUNTAIN("Blackrock Mountain"),
    THE_GRAND_TOURNAMENT("The Grand Tournament"),
    LEAGUE_OF_EXPLORERS("The League of Explorers"),
    WHISPERS_OF_THE_OLD_GODS("Whispers of the Old Gods"),
    ONE_NIGHT_IN_KARAZHAN("One Night in Karazhan"),
    MEAN_STREETS_OF_GADGETZAN("Mean Streets of Gadgetzan"),
    HALL_OF_FAME("Hall of Fame"),
    JOURNEY_TO_UNGORO("Journey to Un'Goro"),
    KNIGHTS_OF_THE_FRONZE_THRONE("Knights of the Frozen Throne"),
    KOBOLDS_AND_CATACOMBS("Kobolds & Catacombs"),
    THE_WITCHWOOD("The Witchwood"),
    BOOMSDAY_PROJECT("The Boomsday Project"),
    RASTAKHANS_RUMBLE("Rastakhan's Rumble");

    override fun toString(): String {
        return stringValue
    }
}
