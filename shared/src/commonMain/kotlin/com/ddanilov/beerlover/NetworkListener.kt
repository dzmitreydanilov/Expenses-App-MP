package com.ddanilov.beerlover

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn

class NetworkListener(private val helper: ConnetcivityProviderHelper) {
    val networkStatus: Flow<NetworkStatus> = callbackFlow {
        helper.registerListener(
            onNetworkAvailable = {
                trySend(NetworkStatus.Connected)
            },
            onNetworkLost = {
                trySend(NetworkStatus.NotConnected)
            }
        )

        awaitClose {
            helper.unregisterListener()
        }
    }.distinctUntilChanged().flowOn(Dispatchers.IO)
}