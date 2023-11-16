package com.ddanilov.beerlover

import androidx.lifecycle.ViewModel as AndroidXViewModel

actual abstract class ViewModel : AndroidXViewModel() {

    actual override fun onCleared() {
        super.onCleared()
    }
}
