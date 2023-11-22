package com.ddanilov.beerlover

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren

actual abstract class ViewModel actual constructor() {

    protected actual val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    /**
     * Override this to do any cleanup immediately before the
     * internal [CoroutineScope][kotlinx.coroutines.CoroutineScope]
     * is cancelled.
     */
    protected actual open fun onCleared() {
        scope.coroutineContext.cancelChildren()
    }

    fun clear() {
        onCleared()
    }
}
