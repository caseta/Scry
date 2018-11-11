package com.taylorcase.hearthstonescry.utils

import android.net.ConnectivityManager
import javax.inject.Inject

open class NetworkManager @Inject constructor(private val connectivityManager: ConnectivityManager) {

    val isConnected
        get() = connectivityManager.allNetworks.any { connectivityManager.getNetworkInfo(it).isConnected }

}
