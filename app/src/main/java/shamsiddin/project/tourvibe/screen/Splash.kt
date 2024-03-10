package shamsiddin.project.tourvibe.screen

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import kotlinx.coroutines.delay
import shamsiddin.project.tourvibe.R
import shamsiddin.project.tourvibe.navigation.ScreenType
import shamsiddin.project.tourvibe.ui.theme.GreenPrimary
import shamsiddin.project.tourvibe.utils.Manager

@Composable

fun Splash(navController:NavController) {

    val context = LocalContext.current
    val user = Manager.getToken(context)
    LaunchedEffect(true) {
        delay(3250)
        if (user == "" ){
            navController.navigate(ScreenType.Default.route)
        }
         else{   navController.navigate(ScreenType.Default.route)}
        }


    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    Image(
        painter = rememberAsyncImagePainter(R.drawable.car_traveling, imageLoader),
        contentDescription = null,

        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
}
