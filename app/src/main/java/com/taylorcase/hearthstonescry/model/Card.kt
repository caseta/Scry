package com.taylorcase.hearthstonescry.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.taylorcase.hearthstonescry.model.enums.Sets
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "card")
data class Card(
        @PrimaryKey @SerializedName("cardId") @Expose var cardId: String = "",
        @ColumnInfo(name = "dbfId") @SerializedName("dbfId") @Expose var dbfId: String = "",
        @ColumnInfo(name = "name") @SerializedName("name") @Expose var name: String = "",
        @ColumnInfo(name = "cardSet") @SerializedName("cardSet") @Expose var cardSet: String = "",
        @ColumnInfo(name = "type") @SerializedName("type") @Expose var type: String = "",
        @ColumnInfo(name = "faction") @SerializedName("faction") @Expose var faction: String = "",
        @ColumnInfo(name = "rarity") @SerializedName("rarity") @Expose var rarity: String = "",
        @ColumnInfo(name = "cost") @SerializedName("cost") @Expose var cost: Int = 0,
        @ColumnInfo(name = "attack") @SerializedName("attack") @Expose var attack: Int = 0,
        @ColumnInfo(name = "health") @SerializedName("health") @Expose var health: Int = 0,
        @ColumnInfo(name = "text") @SerializedName("text") @Expose var text: String = "",
        @ColumnInfo(name = "flavor") @SerializedName("flavor") @Expose var flavor: String = "",
        @ColumnInfo(name = "artist") @SerializedName("artist") @Expose var artist: String = "",
        @ColumnInfo(name = "collectible") @SerializedName("collectible") @Expose var isCollectible: Boolean = false,
        @ColumnInfo(name = "elite") @SerializedName("elite") @Expose var isElite: Boolean = false,
        @ColumnInfo(name = "race") @SerializedName("race") @Expose var race: String = "",
        @ColumnInfo(name = "playerClass") @SerializedName("playerClass") @Expose var playerClass: String = "",
        @ColumnInfo(name = "img") @SerializedName("img") @Expose var img: String = "",
        @ColumnInfo(name = "imgGold") @SerializedName("imgGold") @Expose var imgGold: String = "",
        @ColumnInfo(name = "locale") @SerializedName("locale") @Expose var locale: String = "") : Parcelable {

    companion object {
        const val CARD_EXTRA = "card_extra"
    }

    // TODO: Make this config driven, so they can be updated without an app update
    fun isInStandard(): Boolean {
        if (cardSet == Sets.BASIC.toString() ||
                cardSet == Sets.CLASSIC.toString() ||
                cardSet == Sets.KOBOLDS_AND_CATACOMBS.toString() ||
                cardSet == Sets.JOURNEY_TO_UNGORO.toString() ||
                cardSet == Sets.THE_WITCHWOOD.toString() ||
                cardSet == Sets.BOOMSDAY_PROJECT.toString() ||
                cardSet == Sets.RASTAKHANS_RUMBLE.toString()) {
            return true
        }
        return false
    }
}
