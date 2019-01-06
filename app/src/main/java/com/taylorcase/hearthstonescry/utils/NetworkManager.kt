package com.taylorcase.hearthstonescry.utils

import android.net.ConnectivityManager
import javax.inject.Inject

open class NetworkManager constructor(
        private val connectivityManager: ConnectivityManager
) {

    open fun isConnected(): Boolean {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
    }
}
