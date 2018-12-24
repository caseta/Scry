package com.taylorcase.hearthstonescry.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.taylorcase.hearthstonescry.model.enums.Sets

@Entity(tableName = "card")
open class Card : Parcelable {

    @PrimaryKey @SerializedName("cardId") @Expose var cardId: String = ""
    @ColumnInfo(name = "dbfId") @SerializedName("dbfId") @Expose var dbfId: String = ""
    @ColumnInfo(name = "name") @SerializedName("name") @Expose open var name: String = ""
    @ColumnInfo(name = "cardSet") @SerializedName("cardSet") @Expose open var cardSet: String = ""
    @ColumnInfo(name = "type") @SerializedName("type") @Expose var type: String = ""
    @ColumnInfo(name = "faction") @SerializedName("faction") @Expose var faction: String = ""
    @ColumnInfo(name = "rarity") @SerializedName("rarity") @Expose open var rarity: String = ""
    @ColumnInfo(name = "cost") @SerializedName("cost") @Expose var cost: Int = 0
    @ColumnInfo(name = "attack") @SerializedName("attack") @Expose var attack: Int = 0
    @ColumnInfo(name = "health") @SerializedName("health") @Expose var health: Int = 0
    @ColumnInfo(name = "text") @SerializedName("text") @Expose var text: String = ""
    @ColumnInfo(name = "flavor") @SerializedName("flavor") @Expose var flavor: String = ""
    @ColumnInfo(name = "artist") @SerializedName("artist") @Expose open var artist: String = ""
    @ColumnInfo(name = "collectible") @SerializedName("collectible") @Expose var isCollectible: Boolean = false
    @ColumnInfo(name = "elite") @SerializedName("elite") @Expose var isElite: Boolean = false
    @ColumnInfo(name = "race") @SerializedName("race") @Expose var race: String = ""
    @ColumnInfo(name = "playerClass") @SerializedName("playerClass") @Expose open var playerClass: String = ""
    @ColumnInfo(name = "img") @SerializedName("img") @Expose open var img: String = ""
    @ColumnInfo(name = "imgGold") @SerializedName("imgGold") @Expose var imgGold: String = ""
    @ColumnInfo(name = "locale") @SerializedName("locale") @Expose var locale: String = ""

    override fun toString(): String {
        return ("Card{" + "cardId='" + cardId + '\''.toString() + ", dbfId='" + dbfId + '\''.toString() + ", name='" + name + '\''.toString() + ", cardSet='" + cardSet + '\''.toString() + ", type='"
                + type + '\''.toString() + ", faction='" + faction + '\''.toString() + ", rarity='" + rarity + '\''.toString() + ", cost=" + cost + ", attack=" + attack + ", health=" + health
                + ", text='" + text + '\''.toString() + ", flavor='" + flavor + '\''.toString() + ", artist='" + artist + '\''.toString() + ", collectible=" + isCollectible + ", elite=" + isElite
                + ", race='" + race + '\''.toString() + ", playerClass='" + playerClass + '\''.toString() + ", img='" + img + '\''.toString() + ", imgGold='" + imgGold + '\''.toString() + ", locale='"
                + locale + '\''.toString() + '}'.toString())
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.cardId)
        dest.writeString(this.dbfId)
        dest.writeString(this.name)
        dest.writeString(this.cardSet)
        dest.writeString(this.type)
        dest.writeString(this.faction)
        dest.writeString(this.rarity)
        dest.writeInt(this.cost)
        dest.writeInt(this.attack)
        dest.writeInt(this.health)
        dest.writeString(this.text)
        dest.writeString(this.flavor)
        dest.writeString(this.artist)
        dest.writeByte(if (this.isCollectible) 1.toByte() else 0.toByte())
        dest.writeByte(if (this.isElite) 1.toByte() else 0.toByte())
        dest.writeString(this.race)
        dest.writeString(this.playerClass)
        dest.writeString(this.img)
        dest.writeString(this.imgGold)
        dest.writeString(this.locale)
    }

    constructor()

    private constructor(`in`: Parcel) {
        this.cardId = `in`.readString()
        this.dbfId = `in`.readString()
        this.name = `in`.readString()
        this.cardSet = `in`.readString()
        this.type = `in`.readString()
        this.faction = `in`.readString()
        this.rarity = `in`.readString()
        this.cost = `in`.readInt()
        this.attack = `in`.readInt()
        this.health = `in`.readInt()
        this.text = `in`.readString()
        this.flavor = `in`.readString()
        this.artist = `in`.readString()
        this.isCollectible = `in`.readByte().toInt() != 0
        this.isElite = `in`.readByte().toInt() != 0
        this.race = `in`.readString()
        this.playerClass = `in`.readString()
        this.img = `in`.readString()
        this.imgGold = `in`.readString()
        this.locale = `in`.readString()
    }

    companion object CREATOR : Parcelable.Creator<Card> {

        const val CARD_EXTRA = "card_extra"

        override fun createFromParcel(parcel: Parcel): Card {
            return Card(parcel)
        }

        override fun newArray(size: Int): Array<Card?> {
            return arrayOfNulls(size)
        }
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
