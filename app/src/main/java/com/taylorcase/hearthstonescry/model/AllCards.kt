package com.taylorcase.hearthstonescry.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

open class AllCards {

    @SerializedName("Basic") @Expose var basicCards: List<Card>? = null
    @SerializedName("Classic") @Expose var classicCards: List<Card>? = null
    @SerializedName("Naxxramas") @Expose var naxxCards: List<Card>? = null
    @SerializedName("Goblins vs Gnomes") @Expose var gvgCards: List<Card>? = null
    @SerializedName("Blackrock Mountain") @Expose var blackrockCards: List<Card>? = null
    @SerializedName("The Grand Tournament") @Expose var grandTournamentCards: List<Card>? = null
    @SerializedName("The League of Explorers") @Expose var loeCards: List<Card>? = null
    @SerializedName("Whispers of the Old Gods") @Expose var wotogCards: List<Card>? = null
    @SerializedName("One Night in Karazhan") @Expose var onikCards: List<Card>? = null
    @SerializedName("Mean Streets of Gadgetzan") @Expose var msogCards: List<Card>? = null
    @SerializedName("Hall of Fame") @Expose var hofCards: List<Card>? = null
    @SerializedName("Journey to Un'Goro") @Expose var jtugCards: List<Card>? = null
    @SerializedName("Knights of the Frozen Throne") @Expose var kotftCards: List<Card>? = null
    @SerializedName("Kobolds & Catacombs") @Expose var kacCards: List<Card>? = null
    @SerializedName("The Witchwood") @Expose var wwCards: List<Card>? = null
    @SerializedName("The Boomsday Project") @Expose var bpCards: List<Card>? = null

    open val listOfAllCards: List<Card>
        get() {
            val allCardsList = ArrayList<Card>()
            allCardsList.addAll(basicCards!!)
            allCardsList.addAll(classicCards!!)
            allCardsList.addAll(naxxCards!!)
            allCardsList.addAll(gvgCards!!)
            allCardsList.addAll(blackrockCards!!)
            allCardsList.addAll(grandTournamentCards!!)
            allCardsList.addAll(loeCards!!)
            allCardsList.addAll(wotogCards!!)
            allCardsList.addAll(onikCards!!)
            allCardsList.addAll(msogCards!!)
            allCardsList.addAll(hofCards!!)
            allCardsList.addAll(jtugCards!!)
            allCardsList.addAll(kotftCards!!)
            allCardsList.addAll(kacCards!!)
            allCardsList.addAll(wwCards!!)
            allCardsList.addAll(bpCards!!)

            return allCardsList
        }

    override fun toString(): String {
        return "AllCards(basicCards=$basicCards, classicCards=$classicCards, naxxCards=$naxxCards, gvgCards=$gvgCards, blackrockCards=$blackrockCards, grandTournamentCards=$grandTournamentCards, loeCards=$loeCards, wotogCards=$wotogCards, onikCards=$onikCards, msogCards=$msogCards, hofCards=$hofCards, jtugCards=$jtugCards, kotftCards=$kotftCards, kacCards=$kacCards, wwCards=$wwCards, bpCards=$bpCards)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AllCards

        if (basicCards != other.basicCards) return false
        if (classicCards != other.classicCards) return false
        if (naxxCards != other.naxxCards) return false
        if (gvgCards != other.gvgCards) return false
        if (blackrockCards != other.blackrockCards) return false
        if (grandTournamentCards != other.grandTournamentCards) return false
        if (loeCards != other.loeCards) return false
        if (wotogCards != other.wotogCards) return false
        if (onikCards != other.onikCards) return false
        if (msogCards != other.msogCards) return false
        if (hofCards != other.hofCards) return false
        if (jtugCards != other.jtugCards) return false
        if (kotftCards != other.kotftCards) return false
        if (kacCards != other.kacCards) return false
        if (wwCards != other.wwCards) return false
        if (bpCards != other.bpCards) return false

        return true
    }

    override fun hashCode(): Int {
        var result = basicCards?.hashCode() ?: 0
        result = 31 * result + (classicCards?.hashCode() ?: 0)
        result = 31 * result + (naxxCards?.hashCode() ?: 0)
        result = 31 * result + (gvgCards?.hashCode() ?: 0)
        result = 31 * result + (blackrockCards?.hashCode() ?: 0)
        result = 31 * result + (grandTournamentCards?.hashCode() ?: 0)
        result = 31 * result + (loeCards?.hashCode() ?: 0)
        result = 31 * result + (wotogCards?.hashCode() ?: 0)
        result = 31 * result + (onikCards?.hashCode() ?: 0)
        result = 31 * result + (msogCards?.hashCode() ?: 0)
        result = 31 * result + (hofCards?.hashCode() ?: 0)
        result = 31 * result + (jtugCards?.hashCode() ?: 0)
        result = 31 * result + (kotftCards?.hashCode() ?: 0)
        result = 31 * result + (kacCards?.hashCode() ?: 0)
        result = 31 * result + (wwCards?.hashCode() ?: 0)
        result = 31 * result + (bpCards?.hashCode() ?: 0)
        return result
    }
}
