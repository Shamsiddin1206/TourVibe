package shamsiddin.project.tourvibe.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import shamsiddin.project.tourvibe.navigation.BottomBatNavGraph

@Composable
fun Default(navController: NavHostController){
    val bottomNavController = rememberNavController()
    Column {
        Box(modifier = Modifier.weight(1f)){
            BottomBatNavGraph(navController = navController, bottomNavController = bottomNavController)
        }
        BottomBar(bottomNavController = bottomNavController)
    }
}