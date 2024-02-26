package shamsiddin.project.tourvibe.navigation

sealed class ScreenType(val route: String){
    object Splash: ScreenType("splash_screen")
    object Login: ScreenType("login_screen")
    object Registration: ScreenType("registration_screen")
    object Hotels: ScreenType("hotels_screen")
    object Default: ScreenType("default_screen")
    object Menu: ScreenType("menu_screen")
    object GuideBook: ScreenType("guideBook_screen")
    object Profile: ScreenType("profile_screen")
    object Countries: ScreenType("countries_screen")
    object PLace: ScreenType("place_screen"+"/{destination}")
}
