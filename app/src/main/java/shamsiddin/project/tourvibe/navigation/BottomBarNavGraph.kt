package shamsiddin.project.tourvibe.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import shamsiddin.project.tourvibe.screen.GuideBook
import shamsiddin.project.tourvibe.screen.Hotels
import shamsiddin.project.tourvibe.screen.Menu
import shamsiddin.project.tourvibe.screen.Profile

@Composable
fun BottomBatNavGraph(navController: NavHostController, bottomNavController: NavHostController){
    NavHost(navController = bottomNavController, startDestination = BottomBarScreens.Guidebook.route, modifier = Modifier.fillMaxSize()){
        composable(BottomBarScreens.Guidebook.route){
            GuideBook(navController = navController)
        }
        composable(BottomBarScreens.Hotels.route){
            Hotels(navController = navController)
        }
        composable(BottomBarScreens.Menu.route){
            Menu(navController = navController)
        }
        composable(BottomBarScreens.Profile.route){
            Profile(navController = navController)
        }
    }
}