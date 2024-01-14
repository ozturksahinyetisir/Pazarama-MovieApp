package com.example.pazaramamovieapp.util.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.StandardTestDispatcher

class TestDefaultDispatcher : DispatcherProvider {
    override val IO: CoroutineDispatcher
        get() = StandardTestDispatcher()
    override val Main: CoroutineDispatcher
        get() = StandardTestDispatcher()
    override val Default: CoroutineDispatcher
        get() = StandardTestDispatcher()

}