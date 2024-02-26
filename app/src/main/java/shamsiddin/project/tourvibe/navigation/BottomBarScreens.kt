package shamsiddin.project.tourvibe.navigation

import androidx.compose.ui.res.painterResource
import shamsiddin.project.tourvibe.R

sealed class BottomBarScreens(
    val route: String,
    val title: String,
    val icon: Int,
    val iconFocused: Int
){
    object Guidebook: BottomBarScreens(
        route = "Guidebook",
        title = "GuideBook",
        icon = R.drawable.guidebook_not_filled,
        iconFocused = R.drawable.guidebook_filled
    )
    object Hotels: BottomBarScreens(
        route = "Hotels",
        title = "Hotels",
        icon = R.drawable.hotel_not_filled,
        iconFocused = R.drawable.hotel_filled
    )
    object Menu: BottomBarScreens(
        route = "Menu",
        title = "Menu",
        icon = R.drawable.restaurant_not_filled,
        iconFocused = R.drawable.restaurant_filled
    )
    object Profile: BottomBarScreens(
        route = "Profile",
        title = "Profile",
        icon = R.drawable.profile_not_filled,
        iconFocused = R.drawable.profile_filled
    )
}
