package com.ddanilov.beerlover

interface ConnectivityProviderHelper {
    fun registerListener(onNetworkAvailable: () -> Unit, onNetworkLost: () -> Unit)
    fun unregisterListener()
}

sealed interface NetworkStatus {
    object Connected : NetworkStatus
    object NotConnected : NetworkStatus
}