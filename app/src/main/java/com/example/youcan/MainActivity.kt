package com.example.youcan

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.youcan.di.Food
import com.example.youcan.navigation.AppNavHost
import com.example.youcan.navigation.NavRoute
import com.example.youcan.ui.theme.YouCanTheme
import com.example.youcan.utils.DB_TYPE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var isSplashScreen = mutableStateOf(true)

        lifecycleScope.launch(Dispatchers.Default){
            delay(500)
            isSplashScreen.value = false
        }
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                isSplashScreen.value
            }
        }
        setContent {
            YouCanTheme {
                val mfood = Food
                val context = LocalContext.current
                val mViewModel: MainViewModel =
                    viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
                val navController = rememberNavController()
                Scaffold(
                    content = {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colors.background
                        ) {
                            AppNavHost(mViewModel, navController, mfood)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    YouCanTheme {
        Greeting("Android")
    }
}