package shamsiddin.project.tourvibe.screen.menu

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import shamsiddin.project.tourvibe.model.Destination
import shamsiddin.project.tourvibe.model.Food
import shamsiddin.project.tourvibe.model.Restaurant
import shamsiddin.project.tourvibe.navigation.ScreenType
import shamsiddin.project.tourvibe.ui.theme.GreenPrimary
import shamsiddin.project.tourvibe.ui.theme.TITLE
import shamsiddin.project.tourvibe.ui.theme.TITLETEXT
import shamsiddin.project.tourvibe.utils.Manager

@Composable
fun FoodExtendedInformation(navController: NavController, food: Food) {

    Place(navController = navController, food = food)
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
fun Place(food: Food?,navController: NavController) {
//    val pagerState = rememberPagerState(pageCount = {listOfImages.size})

    Scaffold {
        CollapsingToolbar(food = food!!,navController)
    }
}

@Composable
fun CollapsingToolbar(food: Food,navController: NavController) {
    val listOfImages = food.images
    val mainImage = food.mainImage
    val scroll: ScrollState = rememberScrollState(0)

    Box(modifier = Modifier.fillMaxSize()) {
        Header(mainImage, listOfImages, scroll)
        Body(food, scroll, navController)

        Toolbar(scroll, food)

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
private fun Body(food: Food, scrollState: ScrollState,navController: NavController) {

//    Manager.getFoodsAllCategory {
//        categoriesFood = it
//        Log.d(TAG, "Body: ${categoriesFood.joinToString()}")
//    }

    var restaurantList by remember {
        mutableStateOf(listOf<Restaurant>())
    }

    Manager.getRestaurants {
        restaurantList = it.toMutableList()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(scrollState)
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
                    text = food.description,
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


                    LazyRow(
                        Modifier
                            .fillMaxWidth()
                            .padding(start = 5.dp, end = 5.dp)
                    ) {
                        items(food.images) {

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp)
                                    .aspectRatio(0.8f),
                                elevation = CardDefaults.cardElevation(5.dp),
                                shape = RoundedCornerShape(5.dp)
                            ) {
                                Box(modifier = Modifier.fillMaxWidth()) {
                                    SubcomposeAsyncImage(
                                        model = it,
                                        contentDescription = "",
                                        loading = { CircularProgressIndicator() },
                                        modifier = Modifier.fillMaxSize().size(200.dp),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            }
                        }
                    }

                    Text(
                        text = "Restaurants you may like",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(16.dp), fontSize = 25.sp, fontWeight = FontWeight.Bold
                    )

                    LazyRow(
                        Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp)
                    ) {
                        items(restaurantList){
                            RestaurantItem(restaurant = it, navController = navController )
                        }
                    }

                }
            }


        }
    }
}


@Composable
fun RestaurantItem(restaurant: Restaurant,navController: NavController){
    Card(modifier = Modifier.clickable {
        navController.navigate("restaurant_screen" + "/${restaurant}")
    }
        .fillMaxWidth()
        .padding(5.dp)
        .aspectRatio(0.8f),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            SubcomposeAsyncImage(
                model = restaurant.mainImage,
                contentDescription = "",
                loading = { CircularProgressIndicator() },
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
                .align(Alignment.BottomCenter)
                .background(color = Color.Transparent),
                contentAlignment = Alignment.BottomCenter
            ){
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.White.copy(0.5f),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .graphicsLayer { alpha = 0.8f }
                    .align(Alignment.BottomCenter),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()) {
                        Text(text = restaurant.name, color = Color.White, fontSize = 18.sp,fontWeight = FontWeight.Bold, modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth())
                    }
                }
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
                    color = TITLE
//                brush = Brush.horizontalGradient(
//                    listOf(Color(0xff026586), Color(0xff032C45))
//                )
                ),
            title = {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(text = food.name, color = TITLETEXT)
                }
            },
            colors = TopAppBarDefaults.mediumTopAppBarColors(Color.Transparent)
        )
    }
}

