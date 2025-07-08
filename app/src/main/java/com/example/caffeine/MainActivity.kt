package com.example.caffeine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.caffeine.navigation.Screen
import com.example.caffeine.navigation.coffeeNavGraph
import com.example.caffeine.ui.theme.CaffeineTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CaffeineTheme {
                val navController = rememberNavController()
                Scaffold {
                    NavHost(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        navController = navController,
                        startDestination = Screen.HomeOnBoarding.route
                    ) {
                        coffeeNavGraph(navController)
                    }
                }
            }
        }
    }
}
