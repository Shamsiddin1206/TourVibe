package shamsiddin.project.tourvibe.screen.menu

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import shamsiddin.project.tourvibe.model.Food
import shamsiddin.project.tourvibe.utils.JsonNavType

@Composable
fun FoodExtendedInformation(navController: NavController, food: Food) {

    Place(navController = navController, destination = food)
//    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
//
//
////        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(210.dp)
//                .aspectRatio(1.8f),
//
//
//            elevation = CardDefaults.cardElevation(5.dp)
//        ) {
//            Box(modifier = Modifier.fillMaxHeight()) {
//                SubcomposeAsyncImage(
//                    model = food.mainImage,
//                    contentDescription = "",
//                    modifier = Modifier
//                        .fillMaxHeight(),
//                    contentScale = ContentScale.Crop,
//                    loading = { CircularProgressIndicator() }
//                )
//            }
//        }
//    }


}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Place(navController: NavController, destination: Food?) {
//    val pagerState = rememberPagerState(pageCount = {listOfImages.size})

    Scaffold {
        CollapsingToolbar(destination = destination!!)
    }
}

@Composable
fun CollapsingToolbar(destination: Food) {
    val listOfImages = destination.images
    val mainImage = destination.mainImage
    val scroll: ScrollState = rememberScrollState(0)

    Box(modifier = Modifier.fillMaxSize()) {
        Header(mainImage, listOfImages, scroll)
        Body(destination.description, scroll)
        Toolbar(scroll, destination)
//        Title(destination.name, scroll)
    }
}


@Composable
private fun Header(mainImage: String, listOfImages: List<String>, scrollState: ScrollState) {
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
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color(0xAA000000)),
                        startY = 3 * headerHeightPx / 4 // to wrap the title only
                    )
                )
        )
    }
}

@Composable
private fun Body(text: String, scrollState: ScrollState) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        Spacer(Modifier.height(270.dp))
        Card(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
            elevation = CardDefaults.cardElevation(5.dp)
        ) {
            repeat(5) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .background(Color.White)
                        .padding(16.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Toolbar(scrollState: ScrollState, food: Food) {
    val headerHeightPx = with(LocalDensity.current) { 300.dp.toPx() }
    val toolbarHeightPx = with(LocalDensity.current) { 60.dp.toPx() }

    val toolbarBottom = headerHeightPx - toolbarHeightPx
    val showToolbar by remember {
        derivedStateOf { scrollState.value >= toolbarBottom }
    }

    AnimatedVisibility(
        visible = showToolbar,
        enter = fadeIn(animationSpec = tween(300)),
        exit = fadeOut(animationSpec = tween(300))
    ) {
        TopAppBar(
            modifier = Modifier
                .background(
                    color = Color.Cyan, shape = RoundedCornerShape(50.dp)
//                brush = Brush.horizontalGradient(
//                    listOf(Color(0xff026586), Color(0xff032C45))
//                )
                ),
            title = {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(text = food.name)
                }
            },
            colors = TopAppBarDefaults.mediumTopAppBarColors(Color.Transparent)
        )
    }
}

