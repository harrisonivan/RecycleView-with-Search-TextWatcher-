package com.example.test

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class MainRepository(val context: Context): MainContract.repository, CoroutineScope {
    override fun onDestroy() {
        job.cancel()
    }

    private var job = Job()

    // set coroutine
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job
}