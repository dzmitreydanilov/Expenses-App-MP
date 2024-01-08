package com.expenses.app

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.defaultComponentContext
import com.ddanilov.beerlover.decompose.root.RootComponent
import com.ddanilov.beerlover.initKoin
import com.expenses.core.decompose.DefaultAppComponentContext
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class AndroidApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin(true) {
            androidContext(this@AndroidApp)
            androidLogger()
            modules(

            )
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = RootComponent(DefaultAppComponentContext(defaultComponentContext()))
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RootScreen(component = appComponent)
                }
            }
        }
    }
}
