package shamsiddin.project.tourvibe.screen.menu

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoneOutline
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.launch
import shamsiddin.project.tourvibe.R
import shamsiddin.project.tourvibe.model.Destination
import shamsiddin.project.tourvibe.model.Restaurant
import shamsiddin.project.tourvibe.screen.Details
import shamsiddin.project.tourvibe.screen.OverView
import shamsiddin.project.tourvibe.screen.RatingBar
import shamsiddin.project.tourvibe.screen.Reviews
import shamsiddin.project.tourvibe.screen.ViewPager
import shamsiddin.project.tourvibe.ui.theme.AshGrey
import shamsiddin.project.tourvibe.ui.theme.DeepSlate
import shamsiddin.project.tourvibe.ui.theme.GreenPrimary
import shamsiddin.project.tourvibe.ui.theme.LightGrey
import shamsiddin.project.tourvibe.ui.theme.MidnightBlue
import shamsiddin.project.tourvibe.ui.theme.NEWCOLOR
import shamsiddin.project.tourvibe.ui.theme.TITLE
import shamsiddin.project.tourvibe.ui.theme.TITLETEXT
import shamsiddin.project.tourvibe.utils.Manager
import shamsiddin.project.tourvibe.utils.SharedPreferences

@Composable
fun RestaurantScreen(restaurant: Restaurant, navController: NavController, food_name: String) {
//    var lesson by remember { mutableStateOf(Restaurant("", "", "", "", "")) }
//    Manager.getLesson(key) {
//        lesson = it
//    }
    val scroll: ScrollState = rememberScrollState(0)

    Log.d("qarab tur", "RestaurantScreen: ${restaurant.overViewVideo}")
    val cnt = LocalLifecycleOwner.current



    Column(modifier = Modifier.fillMaxWidth()) {
        SubcomposeAsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = restaurant.mainImage,
            contentDescription = null,
            loading = { CircularProgressIndicator() }
        )
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(Modifier.height(240.dp))
        Card(
            modifier = Modifier
                .fillMaxSize()
                .background(MidnightBlue),
            shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
            elevation = CardDefaults.cardElevation(5.dp)
        ) {
            repeat(1) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {


                        Text(
                            text = food_name,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 16.dp, start = 16.dp),
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            color = NEWCOLOR
                        )
                        Text(
                            text = "In "+restaurant.name,
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Justify,
                            color = Color.Gray,
                            modifier = Modifier.padding(top = 4.dp, start = 16.dp)
                        )
                    }
                    Text(
                        text = restaurant.price,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(top = 22.dp, end = 24.dp),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = NEWCOLOR
                    )
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp), thickness = 1.dp, color = GreenPrimary
                )

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Row {
                        Icon(
                            painter = painterResource(id = shamsiddin.project.tourvibe.R.drawable.star),
                            modifier = Modifier.size(28.dp),

                            contentDescription = "location",
                        )
//                            Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = restaurant.rating.toString(),
                            color = Color.Black,
                            fontSize = 17.sp,
                            modifier = Modifier.padding(start = 4.dp, top = 5.dp),

                            )
                    }
                    Row {
                        Icon(
                            painter = painterResource(id = shamsiddin.project.tourvibe.R.drawable.calorie),
                            modifier = Modifier.size(28.dp),

                            contentDescription = "location",
                        )
//                            Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = restaurant.caloryInfo,
                            color = Color.Black,
                            fontSize = 17.sp,
                            modifier = Modifier.padding(start = 4.dp, top = 5.dp),

                            )
                    }
                    Row {
                        Icon(
                            painter = painterResource(id = shamsiddin.project.tourvibe.R.drawable.time),
                            modifier = Modifier.size(28.dp),

                            contentDescription = "location",
                        )
//                            Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = "5-10 min",
                            color = Color.Black,
                            fontSize = 17.sp,
                            modifier = Modifier.padding(start = 4.dp, top = 5.dp),

                            )
                    }
                }

                AndroidView(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 16.dp)
                    .clip(RoundedCornerShape(16.dp)), factory = { context ->
                    YouTubePlayerView(context = context).apply {
                        cnt.lifecycle.addObserver(this)

                        addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                            override fun onReady(youTubePlayer: YouTubePlayer) {
                                youTubePlayer.loadVideo(restaurant.overViewVideo, 0f)
                                youTubePlayer.pause()
                                var playbackRate = PlayerConstants.PlaybackRate.values()
//                                youTubePlayer.setPlaybackRate(playbackRate.get(1))
//                                youTubePlayer.toggleFullscreen()

                            }
                        })
                    }
                })

                Log.d("Restaurant link", "RestaurantScreen:${restaurant.overViewVideo} ")

            }
        }
//    Header(mainImage = restaurant.mainImage, scrollState = scroll)
//    Body(restaurant = restaurant, scroll = scroll, food_name = food_name)
    }
}


@Composable
private fun Header(mainImage: String, scrollState: ScrollState) {

    val headerHeightPx = with(LocalDensity.current) { 300.dp.toPx() }

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(300.dp)
        .graphicsLayer {
            alpha = (-1f / headerHeightPx) * scrollState.value + 1
        }) {
        SubcomposeAsyncImage(
            model = mainImage,
            contentDescription = null,
            loading = { CircularProgressIndicator() },
            contentScale = ContentScale.FillBounds
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Body(restaurant: Restaurant, scroll: ScrollState, food_name: String) {
    val cnt = LocalLifecycleOwner.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(scroll)
//            .background(TITLE)
    ) {
        Spacer(Modifier.height(270.dp))
        Card(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
            elevation = CardDefaults.cardElevation(5.dp)
        ) {
            repeat(1) {
                Text(
                    text = food_name,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .background(TITLE)
                        .padding(16.dp)
                )
                Column(
                    modifier = Modifier
                        .background(TITLE)
                ) {
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp), thickness = 1.dp, color = GreenPrimary
                    )

                    Text(
                        text = "Related images",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(16.dp), fontSize = 25.sp, fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }


//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier
//            .verticalScroll(scroll)
////            .background(TITLE)
//    ) {
//        Spacer(Modifier.height(270.dp))
//        Card(
//            modifier = Modifier.fillMaxSize(),
//            shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
//            elevation = CardDefaults.cardElevation(5.dp)
//        ) {
//            Text(
//                text = "In " + restaurant.name,
//                style = MaterialTheme.typography.bodyMedium,
//                modifier = Modifier
//                    .padding(16.dp), fontSize = 25.sp, fontWeight = FontWeight.Bold
//            )
//            Row(horizontalArrangement = Arrangement.SpaceBetween) {
//                Text(
//                    text = food_name,
//                    style = MaterialTheme.typography.bodyMedium,
//                    modifier = Modifier, fontSize = 22.sp, fontWeight = FontWeight.Bold
//                )
//
//                Text(
//                    text = restaurant.price,
//                    style = MaterialTheme.typography.bodyMedium,
//                    modifier = Modifier
//                        .padding(16.dp), fontSize = 25.sp, fontWeight = FontWeight.Bold
//                )
//            }
//
////
//        }
//    }
}
