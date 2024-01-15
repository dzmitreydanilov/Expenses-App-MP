package com.ddanilov.beerlover

interface ConnetcivityProviderHelper {
    fun registerListener(onNetworkAvailable: () -> Unit, onNetworkLost: () -> Unit)
    fun unregisterListener()
}

sealed interface NetworkStatus {
    object Connected : NetworkStatus
    object NotConnected : NetworkStatus
}