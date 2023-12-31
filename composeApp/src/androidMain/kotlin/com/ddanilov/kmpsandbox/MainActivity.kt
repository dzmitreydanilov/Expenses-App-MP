package com.ddanilov.kmpsandbox

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
import com.ddanilov.kmpsandbox.theme.AppTheme
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.scope.AndroidScopeComponent
import org.koin.core.scope.Scope

class AndroidApp : Application(), AndroidScopeComponent{

    override fun onCreate() {
        super.onCreate()
        initKoin(true) {
            androidContext(this@AndroidApp)
            androidLogger()
        }
    }

    override val scope: Scope
        get() = TODO("Not yet implemented")
}

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = RootComponent(defaultComponentContext())
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
