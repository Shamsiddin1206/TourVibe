package shamsiddin.project.tourvibe.screen

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import shamsiddin.project.tourvibe.model.Destination

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Place(navController: NavController, destination: Destination?){
    Log.d("Received", "Place: ${destination!!.name}")
    val listOfImages = destination!!.images
    val pagerState = rememberPagerState(pageCount = {listOfImages.size})

    HorizontalPager(state = pagerState) {
        SubcomposeAsyncImage(
            model = listOfImages[it],
            contentDescription = "",
            loading = { CircularProgressIndicator()},
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds)
    }
}