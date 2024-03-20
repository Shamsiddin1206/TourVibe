package shamsiddin.project.tourvibe.screen

import android.os.Build
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay
import shamsiddin.project.tourvibe.R
import shamsiddin.project.tourvibe.navigation.ScreenType
import shamsiddin.project.tourvibe.ui.theme.GreenPrimary
import shamsiddin.project.tourvibe.utils.Manager
import shamsiddin.project.tourvibe.utils.SharedPreferences

var TAG = "TAG"

@Composable

fun Splash(navController:NavController) {

    val context = LocalContext.current
    val user = SharedPreferences.getInstance(context).getUser()
    Log.d(TAG, "Splash: ${user?.name}")
    LaunchedEffect(true) {
        delay(3000)
        if (user != null){
            navController.navigate(ScreenType.Default.route)
        }
         else{   navController.navigate(ScreenType.Registration.route)}
        }


    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_lottie))

    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )
}
