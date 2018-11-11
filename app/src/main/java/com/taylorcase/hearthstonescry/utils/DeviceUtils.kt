package com.taylorcase.hearthstonescry.utils

object DeviceUtils {

    internal fun isSamsungDevice(): Boolean {
        return android.os.Build.MANUFACTURER.toLowerCase() == "samsung"
    }

}