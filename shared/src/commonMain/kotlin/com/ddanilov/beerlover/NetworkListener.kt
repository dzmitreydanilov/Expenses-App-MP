package com.ddanilov.beerlover

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NetworkListener(private val listener: ConnectivityProviderHelper) {

    private val _networkStatus: MutableStateFlow<NetworkStatus> =
        MutableStateFlow(NetworkStatus.NotConnected)
    val networkStatus: NetworkStatus = _networkStatus.value

    init {
        startListening()
    }

    private fun startListening() {
        listener.registerListener(
            onNetworkAvailable = { updateNetworkStatus(NetworkStatus.Connected) },
            onNetworkLost = { updateNetworkStatus(NetworkStatus.NotConnected) }
        )
    }

    private fun updateNetworkStatus(status: NetworkStatus) {
        _networkStatus.value = status
    }

    fun unregisterListener() {
        listener.unregisterListener()
    }
}