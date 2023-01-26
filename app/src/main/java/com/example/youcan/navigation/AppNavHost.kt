package com.example.youcan.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.youcan.MainViewModel
import com.example.youcan.di.FoodModel
import com.example.youcan.screens.*
import com.example.youcan.utils.Constants

sealed class NavRoute(val route: String){
//    object Splash: NavRoute(Constants.Screens.SPLASH_SCREEN)
    object Start: NavRoute(Constants.Screens.START_SCREEN)
    object Main: NavRoute(Constants.Screens.MAIN_SCREEN)
    object Add: NavRoute(Constants.Screens.ADD_SCREEN)
    object Note: NavRoute(Constants.Screens.NOTE_SCREEN)

}

@Composable
fun AppNavHost(mViewModel: MainViewModel, navController: NavHostController, food: FoodModel.Food){

    NavHost(navController = navController, startDestination = NavRoute.Start.route){
//        composable(NavRoute.Splash.route){
//            SplashScreen(navController = navController)
//        }
        composable(NavRoute.Start.route){
            StartScreen(navController = navController, viewModel = mViewModel)
        }
        composable(NavRoute.Main.route){
            MainScreen(navController = navController, viewModel = mViewModel)
        }
        composable(NavRoute.Add.route){
            AddScreen(navController = navController, viewModel = mViewModel, food = food)
        }
        composable(NavRoute.Note.route + "/{${Constants.Keys.ID}}"){ backStackEntry ->
            NoteScreen(navController = navController, viewModel = mViewModel, noteId = backStackEntry.arguments?.getString(Constants.Keys.ID))
        }
    }
}