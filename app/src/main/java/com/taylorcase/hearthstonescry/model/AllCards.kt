package com.taylorcase.hearthstonescry.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class AllCards(
        @SerializedName("Basic") @Expose var basicCards: List<Card> = emptyList(),
        @SerializedName("Classic") @Expose var classicCards: List<Card> = emptyList(),
        @SerializedName("Naxxramas") @Expose var naxxCards: List<Card> = emptyList(),
        @SerializedName("Goblins vs Gnomes") @Expose var gvgCards: List<Card> = emptyList(),
        @SerializedName("Blackrock Mountain") @Expose var blackrockCards: List<Card> = emptyList(),
        @SerializedName("The Grand Tournament") @Expose var grandTournamentCards: List<Card> = emptyList(),
        @SerializedName("The League of Explorers") @Expose var loeCards: List<Card> = emptyList(),
        @SerializedName("Whispers of the Old Gods") @Expose var wotogCards: List<Card> = emptyList(),
        @SerializedName("One Night in Karazhan") @Expose var onikCards: List<Card> = emptyList(),
        @SerializedName("Mean Streets of Gadgetzan") @Expose var msogCards: List<Card> = emptyList(),
        @SerializedName("Hall of Fame") @Expose var hofCards: List<Card> = emptyList(),
        @SerializedName("Journey to Un'Goro") @Expose var jtugCards: List<Card> = emptyList(),
        @SerializedName("Knights of the Frozen Throne") @Expose var kotftCards: List<Card> = emptyList(),
        @SerializedName("Kobolds & Catacombs") @Expose var kacCards: List<Card> = emptyList(),
        @SerializedName("The Witchwood") @Expose var wwCards: List<Card> = emptyList(),
        @SerializedName("The Boomsday Project") @Expose var bpCards: List<Card> = emptyList(),
        @SerializedName("Rastakhan's Rumble") @Expose var rrCards: List<Card> = emptyList()) {

    open val listOfAllCards: List<Card>
        get() {
            val allCardsList = ArrayList<Card>()
            allCardsList.addAll(basicCards)
            allCardsList.addAll(classicCards)
            allCardsList.addAll(naxxCards)
            allCardsList.addAll(gvgCards)
            allCardsList.addAll(blackrockCards)
            allCardsList.addAll(grandTournamentCards)
            allCardsList.addAll(loeCards)
            allCardsList.addAll(wotogCards)
            allCardsList.addAll(onikCards)
            allCardsList.addAll(msogCards)
            allCardsList.addAll(hofCards)
            allCardsList.addAll(jtugCards)
            allCardsList.addAll(kotftCards)
            allCardsList.addAll(kacCards)
            allCardsList.addAll(wwCards)
            allCardsList.addAll(bpCards)
            allCardsList.addAll(rrCards)

            return allCardsList
        }
}
