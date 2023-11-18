package com.ddanilov.beerlover

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import androidx.lifecycle.ViewModel as AndroidXViewModel

actual abstract class ViewModel : AndroidXViewModel() {
    protected actual val scope: CoroutineScope
        get() = viewModelScope

    actual override fun onCleared() {
        super.onCleared()
    }
}
