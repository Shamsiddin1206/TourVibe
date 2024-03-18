package shamsiddin.project.tourvibe.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoneOutline
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.SimpleExoPlayer
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindowContent
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch
import shamsiddin.project.tourvibe.R
import shamsiddin.project.tourvibe.model.Comment
import shamsiddin.project.tourvibe.model.Hotel
import shamsiddin.project.tourvibe.utils.Manager


//var comments = listOf(
//    Comment(
//        1,
//        "Vashe chotki, hammaga tavsiya beraman",
//        User(
//            "Mike Tyson",
//            "https://upload.wikimedia.org/wikipedia/commons/e/ee/Mike_Tyson_Photo_Op_GalaxyCon_Austin_2023.jpg"
//        ),
//        "02.02.2024",
//        4.5
//    ),
//    Comment(
//        2,
//        "Aaa aldavotti hammasi, bo'midi bu",
//        User("Jahongir Otajonov", "https://xabar.uz/static/crop/3/3/920__95_3300202273.jpg"),
//        "02.03.2024",
//        3.7
//    )
//)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleHotelScreen(navHostController: NavHostController, hotel: Hotel) {
    Box(modifier = Modifier.fillMaxSize()) {
        val buttonState = remember { mutableStateOf(false) }
        var sheetState by remember { mutableStateOf(false) }
        val currentUser = Manager.getToken(LocalContext.current)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(8.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                Modifier
                    .shadow(elevation = 20.dp, shape = RoundedCornerShape(30.dp))
                    .width(330.dp)
                    .height(400.dp)
                    .clip(RoundedCornerShape(30.dp))


            ) {
                SubcomposeAsyncImage(
                    model = hotel.mainImage,
                    contentDescription = "",
                    loading = { CircularProgressIndicator() },
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.BottomCenter)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent, Color(0xAD000000)
                                ),
                                startY = 550f,

                                )
                        ),
                )
                Text(
                    text = hotel.name,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(bottom = 60.dp, start = 20.dp)
                )
                Text(
                    text = "${hotel.locatedState}, ${hotel.locatedCountry}",
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(bottom = 35.dp, start = 20.dp)
                )


            }
            Spacer(modifier = Modifier.height(20.dp))
            val tabData = listOf("Details", "Comments")
            val bodyPagerState = rememberPagerState(pageCount = { tabData.size })
            val coroutineScope = rememberCoroutineScope()
            val selectedIndex = remember { mutableStateOf(0) }
            TabRow(
                selectedTabIndex = selectedIndex.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                indicator = {
                    TabRowDefaults.Indicator(
                        modifier = Modifier.tabIndicatorOffset(it[selectedIndex.value]),
                        height = 3.dp,
                        color = Color(android.graphics.Color.parseColor("#FF2F7A83"))
                    )
                },
                containerColor = Color.White
            ) {
                tabData.forEachIndexed { index, s ->
                    Tab(selected = selectedIndex.value == index,
                        onClick = {
                            selectedIndex.value = index
                            coroutineScope.launch { bodyPagerState.animateScrollToPage(index) }
                        },
                        text = { Text(text = s, color = Color.Black) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            when (selectedIndex.value) {
                0 -> {
                    Details(hotel = hotel, navHostController = navHostController)
                    buttonState.value = false
                }

                1 -> {
                    Comments(hotel = hotel)
                    buttonState.value = true
                }

            }


        }
        if (buttonState.value) {
            if (sheetState) {
                var myRating by remember { mutableStateOf(0) }
                var commentText by remember { mutableStateOf("") }

                ModalBottomSheet(
                    onDismissRequest = { sheetState = false },
                    dragHandle = { BottomSheetDefaults.DragHandle() },
                    containerColor = Color.White
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = hotel.name,
                            fontSize = 23.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(
                                Alignment.TopCenter
                            )
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 55.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                                    .padding(start = 15.dp)
                                    .fillMaxWidth()
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.person_default_ic),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(
                                            RoundedCornerShape(50)
                                        )
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(
                                        text = currentUser,
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp
                                    )
                                    Text(
                                        text = "Country: ${hotel.locatedCountry}",
                                        fontSize = 13.sp
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            RatingBar(
                                maxRating = 5,
                                currentRating = myRating,
                                onRatingChanged = { myRating = it })
                            Spacer(modifier = Modifier.height(20.dp))
                            OutlinedTextField(
                                value = commentText, onValueChange = { commentText = it },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .defaultMinSize(minHeight = 100.dp)
                                    .padding(start = 15.dp, end = 15.dp),
                                shape = RoundedCornerShape(10.dp),
                                label = { Text(text = "Write comment") },
                                textStyle = TextStyle(Color.DarkGray, fontSize = 15.sp),
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = Color(android.graphics.Color.parseColor("#FF2F7A83"))
                                )
                            )
                            Spacer(modifier = Modifier.height(100.dp))
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter)
                                .padding(top = 10.dp, bottom = 20.dp)
                                .wrapContentHeight(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ExtendedFloatingActionButton(
                                text = { Text(text = "Cancel") },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Outlined.Cancel,
                                        contentDescription = ""
                                    )
                                },
                                onClick = { sheetState = false },
                                containerColor = Color(android.graphics.Color.parseColor("#FF000024")),
                                contentColor = Color.White,
                                modifier = Modifier
                                    .padding(start = 10.dp)
                                    .weight(1f)
                            )
                            ExtendedFloatingActionButton(
                                text = { Text(text = "Confirm") },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Filled.DoneOutline,
                                        contentDescription = ""
                                    )
                                },
                                onClick = {
                                    Manager.giveComment(
                                        "hotel",
                                        hotel.id,
                                        currentUser,
                                        myRating.toDouble(),
                                        commentText
                                    ) {
                                        Log.d("COMMENT", "Body: ${it}")
                                    }
                                    sheetState = false
                                },
                                containerColor = Color(android.graphics.Color.parseColor("#49be25")),
                                contentColor = Color.White,
                                modifier = Modifier
                                    .padding(start = 10.dp, end = 10.dp)
                                    .weight(1f)
                            )
                        }
                    }
                }
            }

            ExtendedFloatingActionButton(
                text = { Text(text = "Compose") },
                icon = { Icon(imageVector = Icons.Outlined.Edit, contentDescription = "") },
                onClick = { sheetState = true },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 15.dp, bottom = 10.dp),
                containerColor = Color(android.graphics.Color.parseColor("#FF2F7A83")),
                contentColor = Color.White
            )
        }
    }
}

@Composable
fun Comments(hotel: Hotel) {
    var list by remember { mutableStateOf(hotel.comments) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        if (!list.isNullOrEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
            ) {
                items(list!!.size) {
                    CommentItem(comment = list!![it])
                }
            }
        } else {
            Text(
                text = "No comments yet",
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(
                    Alignment.Center
                )
            )
        }
    }
}

@Composable
fun CommentItem(comment: Comment) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 10.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.person_default_ic),
                contentDescription = "",
                modifier = Modifier
                    .size(45.dp)
                    .clip(
                        RoundedCornerShape(50)
                    )
            )
            Spacer(modifier = Modifier.width(5.dp))
            Column {
                Text(
                    text = comment.author.name,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = "",
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = comment.rating.toString(), fontSize = 14.sp)
                }
            }
        }
        Text(
            text = comment.text, modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp, end = 5.dp), color = Color.Gray, fontSize = 14.sp
        )

        androidx.compose.material3.Divider(
            modifier = Modifier.padding(bottom = 5.dp, end = 10.dp, start = 10.dp, top = 10.dp),
            thickness = 1.dp,
            color = Color(0xFF2F7A83)
        )
    }
}

@Composable
fun Details(hotel: Hotel, navHostController: NavHostController) {
    Column {


        Text(
            text = "Overview",
            color = Color.Black,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 20.dp, start = 20.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = hotel.description,
            color = Color.Gray,
            textAlign = TextAlign.Start,
            fontSize = 15.sp,
            modifier = Modifier.padding(start = 20.dp)
        )

        Text(
            text = "Gallery",
            color = Color.Black,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 20.dp, start = 20.dp)
        )


        Spacer(modifier = Modifier.height(20.dp))
        LazyRow() {

            items(hotel.images.size) { it ->
                imageGallery(image = hotel.images[it]) {
                    navHostController.navigate(route = "image_slider/${hotel}/${it}")
                }
            }
        }

        Log.d("LatLong", "Details: ${hotel.latitude}    ${hotel.longitude}")
        val hydePark = LatLng(hotel.latitude,hotel.longitude)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(hydePark, 10f)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Location",
            color = Color.Black,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 20.dp, start = 20.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        GoogleMap(
            modifier = Modifier
                .width(350.dp)
                .height(350.dp)
                .align(Alignment.CenterHorizontally),
            cameraPositionState = cameraPositionState
        ) {
            MarkerInfoWindowContent(
                state = MarkerState(position = hydePark),
                title = "Hyde Park",
                snippet = "Marker in Hyde Park"
            ) { marker ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        modifier = Modifier.padding(top = 6.dp),
                        text = marker.title ?: "",
                        fontWeight = FontWeight.Bold
                    )
                    Text(hotel.name)
                    Image(
                        modifier = Modifier
                            .padding(top = 6.dp)
                            .border(
                                BorderStroke(3.dp, color = Color.Gray),
                                shape = RectangleShape
                            ),
                        painter = painterResource(id = R.drawable.img),
                        contentDescription = "A picture of hyde park"
                    )
                }
            }
        }
    }
}

@Composable
fun commentCard(comment: Comment) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Row {
                    AsyncImage(
                        model = comment.author.image,
                        contentDescription = null,
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp)
                            .clip(RoundedCornerShape(0.5f))
                    )
                    Text(
                        text = comment.author.name,
                        color = MaterialTheme.colorScheme.primary
                    )

                }

//                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = comment.text,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}


@Composable
fun imageGallery(
    image: String,
    onClick: () -> Unit
) {
    Spacer(modifier = Modifier.width(10.dp))
    Box(
        Modifier
            .clickable { onClick() }
            .shadow(elevation = 20.dp, shape = RoundedCornerShape(30.dp))
            .width(100.dp)
            .height(100.dp)
            .clip(RoundedCornerShape(5.dp))
    ) {

        SubcomposeAsyncImage(
            model = image,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(5.dp)),
            contentDescription = "",
            loading = { CircularProgressIndicator() },
            contentScale = ContentScale.Crop,
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent, Color(0xAD000000)
                        ),
                        startY = 100f,

                        )
                ),
        )
    }
}

//@Composable
//fun RatingBar(maxRating: Int, currentRating: Int, onRatingChanged: (Int) -> Unit) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(start = 10.dp, end = 10.dp), horizontalArrangement = Arrangement.Center
//    ) {
//        for (i in 1..maxRating) {
//            Icon(imageVector = if (i <= currentRating) Icons.Filled.Star else Icons.Filled.StarOutline,
//                contentDescription = null,
//                tint = if (i <= currentRating) Color(android.graphics.Color.parseColor("#FFC928")) else Color.Unspecified,
//                modifier = Modifier
//                    .clickable { onRatingChanged(i) }
//                    .padding(5.dp)
//                    .size(35.dp)
//            )
//        }
//    }
//}








