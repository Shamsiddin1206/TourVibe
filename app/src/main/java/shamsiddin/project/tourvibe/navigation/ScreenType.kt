package shamsiddin.project.tourvibe.navigation

sealed class ScreenType(val route: String){
    object Splash: ScreenType("splash_screen")
    object Login: ScreenType("login_screen")
    object Registration: ScreenType("registration_screen")
    object Hotels: ScreenType("hotels_screen")
    object SingleHotel: ScreenType("single_hotel/{hotel}")
    object ImageSlider: ScreenType("image_slider/{hotel}/{id}")
    object Default: ScreenType("default_screen")
    object Menu: ScreenType("menu_screen")
    object GuideBook: ScreenType("guideBook_screen")
    object Profile: ScreenType("profile_screen")
    object Countries: ScreenType("countries_screen")
    object PLace: ScreenType("place_screen"+"/{destination}")
    object Restaurant: ScreenType("restaurant_screen"+"/{restaurant}")
    object FoodExtendedInformation: ScreenType("food_extended_screen"+"/{food}")
}
