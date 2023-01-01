package com.pxddy.simpleviewbinding.demo.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import kotlin.time.Duration.Companion.seconds

class CounterViewModel : ViewModel() {

    val counter: StateFlow<Int> = flow {
        var count = 0
        while (currentCoroutineContext().isActive) {
            emit(count++)
            delay(1.seconds)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeout = 5.seconds),
        initialValue = 0
    )
}