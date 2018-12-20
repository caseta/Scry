package com.taylorcase.hearthstonescry.model

import android.os.Parcel
import android.os.Parcelable

class FilterItem constructor(
        val heroList: List<String>,
        val costList: List<String>,
        val setList: List<String>,
        val isStandard: Boolean,
        val rarityList: List<String>
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.createStringArrayList(),
            parcel.createStringArrayList(),
            parcel.createStringArrayList(),
            parcel.readByte() != 0.toByte(),
            parcel.createStringArrayList())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeStringList(heroList)
        parcel.writeStringList(costList)
        parcel.writeStringList(setList)
        parcel.writeByte(if (isStandard) 1 else 0)
        parcel.writeStringList(rarityList)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FilterItem> {
        override fun createFromParcel(parcel: Parcel): FilterItem {
            return FilterItem(parcel)
        }

        override fun newArray(size: Int): Array<FilterItem?> {
            return arrayOfNulls(size)
        }
    }

}
