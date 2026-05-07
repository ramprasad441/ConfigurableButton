package com.configurui

import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Created by Ramprasad on 5/7/26.
 */

class DefaultDispatcherProvider
    @Inject
    constructor() : DispatcherProvider {
        override val io: CoroutineDispatcher = Dispatchers.IO
    }

interface DispatcherProvider {
    val io: CoroutineDispatcher
}
