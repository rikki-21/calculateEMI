package com.example.calculateemi

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "mainscreen") {
        composable("mainscreen") { MainScreen(navController) }
        composable("CalculateEMI/{mortgage}/{interest}/{months}",
            arguments = listOf(navArgument(name = "mortgage"){ type = NavType.IntType },
                navArgument("interest"){ type = NavType.IntType },
                navArgument("months"){ type = NavType.IntType }))
        { backStackEntry ->
            CalculateEMI(backStackEntry.arguments?.getInt("mortgage"),
                backStackEntry.arguments?.getInt("interest"),
                backStackEntry.arguments?.getInt("months") ,
                navController) }
        /*...*/
    }
}
