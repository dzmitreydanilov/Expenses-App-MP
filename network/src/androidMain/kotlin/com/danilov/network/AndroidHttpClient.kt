package com.danilov.network

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android

actual val httpEngine: HttpClientEngine = Android.create()
