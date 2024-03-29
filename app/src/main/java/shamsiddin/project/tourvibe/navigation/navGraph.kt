package shamsiddin.project.tourvibe.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import shamsiddin.project.tourvibe.model.Destination
import shamsiddin.project.tourvibe.model.DestinationArgType
import shamsiddin.project.tourvibe.model.Food
import shamsiddin.project.tourvibe.model.FoodArgType
import shamsiddin.project.tourvibe.model.Hotel
import shamsiddin.project.tourvibe.model.HotelArgType
import shamsiddin.project.tourvibe.model.Restaurant
import shamsiddin.project.tourvibe.model.RestaurantArgType
import shamsiddin.project.tourvibe.screen.Countries
import shamsiddin.project.tourvibe.screen.Default
import shamsiddin.project.tourvibe.screen.GuideBook
import shamsiddin.project.tourvibe.screen.Hotels
import shamsiddin.project.tourvibe.screen.ImageSlider
import shamsiddin.project.tourvibe.screen.Login
import shamsiddin.project.tourvibe.screen.Place
import shamsiddin.project.tourvibe.screen.Profile
import shamsiddin.project.tourvibe.screen.Registration
import shamsiddin.project.tourvibe.screen.SingleHotelScreen
import shamsiddin.project.tourvibe.screen.Splash
import shamsiddin.project.tourvibe.screen.menu.FoodExtendedInformation
import shamsiddin.project.tourvibe.screen.menu.Menu
import shamsiddin.project.tourvibe.screen.menu.RestaurantScreen

@Composable
fun SetNavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = ScreenType.Splash.route){
        var foodName = ""
//        val model = MenuViewModel()
        composable(ScreenType.Splash.route){
            Splash(navController)
        }
        composable(ScreenType.Login.route){
            Login(navController)
        }
        composable(ScreenType.Registration.route){
            Registration(navController)
        }
        composable(ScreenType.GuideBook.route){
            GuideBook(navController)
        }
        composable(ScreenType.Hotels.route){
            Hotels(navController)
        }
        composable(ScreenType.Menu.route){
            Menu(navController)
        }

        composable(ScreenType.Profile.route){
            Profile(navController)
        }
        composable(ScreenType.Countries.route){
            Countries(navController)
        }
        composable(ScreenType.Restaurant.route, arguments = listOf(navArgument("restaurant"){
            type = RestaurantArgType()
        })){

            val res = it.arguments?.getString("restaurant")?.let { it1 -> Gson().fromJson(it1, Restaurant::class.java) }
            Log.d("navGraph Destination", "SetNavGraph: ${res!!.name}")
            RestaurantScreen(navController = navController, restaurant = res,food_name = foodName)
        }
        composable(ScreenType.PLace.route, arguments = listOf(navArgument("destination"){
            type = DestinationArgType()
        })){
            val destination = it.arguments?.getString("destination")?.let { it1 -> Gson().fromJson(it1, Destination::class.java) }
            Log.d("navGraph Destination", "SetNavGraph: ${destination!!.name}")
            Place(navController = navController, destination = destination)
        }
        composable(ScreenType.Default.route){
            Default(navController)
        }

        composable(ScreenType.FoodExtendedInformation.route, arguments = listOf(navArgument("food"){
            type = FoodArgType()
        })){
            val food =it.arguments?.getString("food")?.let { it1 -> Gson().fromJson(it1, Food::class.java) }
            FoodExtendedInformation(navController = navController, food = food!!)
            foodName = food.name
            Log.d("shmas aqlli", "SetNavGraph: ${food!!.name}")
        }

        composable(
            ScreenType.SingleHotel.route,
            arguments = listOf(
                navArgument("hotel"){type = HotelArgType() }
            )
        ) {
            val hotel = it.arguments?.getString("hotel")?.let { it1 -> Gson().fromJson(it1, Hotel::class.java) }
            SingleHotelScreen(navController, hotel!!)
        }

        composable(
            ScreenType.ImageSlider.route,
            arguments = listOf(
//                navArgument("images") { type = NavType.IntArrayType },
                navArgument("id") { type = NavType.IntType },
                navArgument("hotel"){type = HotelArgType()}
            )
        ) {
            val hotel = it.arguments?.getString("hotel")?.let { it1 -> Gson().fromJson(it1, Hotel::class.java) }
//            val images = navBackStackEntry.arguments?.getIntegerArrayList("images")
            val id = it.arguments?.getInt("id")
            ImageSlider(navController, hotel!!, id!!)
        }
    }
}