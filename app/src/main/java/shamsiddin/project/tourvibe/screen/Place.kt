package shamsiddin.project.tourvibe.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.foundation.lazy.LazyColumn
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
import coil.compose.SubcomposeAsyncImage
import kotlinx.coroutines.launch
import shamsiddin.project.tourvibe.R
import shamsiddin.project.tourvibe.model.Comment
import shamsiddin.project.tourvibe.model.Destination
import shamsiddin.project.tourvibe.ui.theme.GreenPrimary
import shamsiddin.project.tourvibe.utils.Manager
import shamsiddin.project.tourvibe.utils.SharedPreferences


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Place(navController: NavController, destination: Destination?){
    Scaffold {
        CollapsingToolbar(destination = destination!!)
    }
}

@Composable
fun CollapsingToolbar(destination: Destination) {
    val listOfImages = destination.images
    val mainImage = destination.mainImage
    val scroll: ScrollState = rememberScrollState(0)

    Box(modifier = Modifier.fillMaxSize()) {
        Header(mainImage, listOfImages, scroll)
        Body(destination, scroll)
        Toolbar(scroll)
        Title(destination.name, scroll)
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
            loading = { CircularProgressIndicator()},
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize())

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

@OptIn( ExperimentalMaterial3Api::class)
@Composable
private fun Body(destination: Destination, scrollState: ScrollState) {
    //TopBar
    val tabData = listOf("Overview", "History", "Reviews")
    val bodyPagerState = rememberPagerState(pageCount = {tabData.size})
    val coroutineScope = rememberCoroutineScope()
    val selectedIndex = remember { mutableStateOf(0) }

    //Comments
    val shared = SharedPreferences.getInstance(LocalContext.current)
    val currentUser = Manager.getToken(LocalContext.current)
    val buttonState = remember { mutableStateOf(false) }
//BottomSheet
    var sheetState by remember { mutableStateOf(false) }
    //ViewPager
    var imageState by remember { mutableStateOf(false) }
    val placeImages = destination.images.toMutableList()
    placeImages.add(0, destination.mainImage)
    val viewPagerState = rememberPagerState { placeImages.size }





    Box(modifier = Modifier.fillMaxSize()){
        if (imageState){
            Row(modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black.copy(0.9f))
                .clickable { imageState = false },
                verticalAlignment = Alignment.CenterVertically
            ) {
                ViewPager(pagerState = viewPagerState,
                    listOfImages = placeImages,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.4f)) }
        }else{
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .verticalScroll(scrollState)
            ) {
                    Spacer(
                        Modifier
                            .height(270.dp)
                            .fillMaxWidth()
                            .clickable { imageState = true })
                    Card(modifier = Modifier.fillMaxSize(), colors = CardDefaults.cardColors(Color.White), shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp), elevation = CardDefaults.cardElevation(5.dp)) {
                        Spacer(modifier = Modifier.height(15.dp))
                        Column {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 15.dp, end = 15.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "${destination.locatedState}, ${destination.locatedCountry}", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Image(painter = painterResource(id = R.drawable.star), contentDescription = "", modifier = Modifier.size(20.dp))
                                    Spacer(modifier = Modifier.width(5.dp))
                                    Text(text = destination.rating.toString(), fontSize = 15.sp)
                                }
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            TabRow(selectedTabIndex = selectedIndex.value,
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
                                            coroutineScope.launch { bodyPagerState.animateScrollToPage(index) } },
                                        text = {Text(text = s, color = Color.Black)}
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            when(selectedIndex.value){
                                0 -> {
                                    OverView(destination = destination)
                                    buttonState.value = false
                                }
                                1 -> {
                                    Details(destination = destination)
                                    buttonState.value = false
                                }
                                2 -> {
                                    Reviews(destination = destination)
                                    buttonState.value = true
                                }
                            }
                        }
                    }
                }
            if (buttonState.value){
                    if (sheetState){
                        var myRating by remember { mutableStateOf(0) }
                        var commentText by remember { mutableStateOf("") }

                        ModalBottomSheet(
                            onDismissRequest = { sheetState = false },
                            dragHandle = { BottomSheetDefaults.DragHandle() },
                            containerColor = Color.White
                        ) {
                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)) {
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(text = destination.name, fontSize = 23.sp, color = Color.Black, fontWeight = FontWeight.Bold, modifier = Modifier.align(
                                    Alignment.TopCenter))
                                Column(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 55.dp)) {
                                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                                        .padding(start = 15.dp)
                                        .fillMaxWidth()) {
                                        Image(painter = painterResource(id = R.drawable.person_default_ic),
                                            contentDescription = "",
                                            modifier = Modifier
                                                .size(40.dp)
                                                .clip(
                                                    RoundedCornerShape(50)
                                                ))
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Column {
                                            Text(text = currentUser, color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                                            Text(text = "Country: ${destination.locatedCountry}", fontSize = 13.sp)
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(10.dp))
                                    RatingBar(maxRating = 5, currentRating = myRating, onRatingChanged = {myRating = it})
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
                                        colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color(android.graphics.Color.parseColor("#FF2F7A83")))
                                    )
                                    Spacer(modifier = Modifier.height(100.dp))
                                }
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.BottomCenter)
                                    .padding(top = 10.dp, bottom = 20.dp)
                                    .wrapContentHeight(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                                    ExtendedFloatingActionButton(
                                        text = { Text(text = "Cancel") },
                                        icon = { Icon(imageVector = Icons.Outlined.Cancel, contentDescription = "") },
                                        onClick = { sheetState = false },
                                        containerColor = Color(android.graphics.Color.parseColor("#FF000024")),
                                        contentColor = Color.White,
                                        modifier = Modifier
                                            .padding(start = 10.dp)
                                            .weight(1f)
                                    )
                                    ExtendedFloatingActionButton(
                                        text = { Text(text = "Confirm") },
                                        icon = { Icon(imageVector = Icons.Filled.DoneOutline, contentDescription = "") },
                                        onClick = {
                                            Manager.giveComment("destination",destination.id,currentUser,myRating.toDouble(),commentText){
                                                Log.d("COMMENT", "Body: ${it}")
                                            }
                                            sheetState = false },
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
}

@Composable
fun OverView(destination: Destination){
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = destination.description,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp),
            color = Color.Black
        )
    }
}

@Composable
fun Details(destination: Destination){
    Text(text = destination.history,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Justify,
        modifier = Modifier
            .background(Color.White)
            .padding(16.dp),
        color = Color.Black
    )
}

@Composable
fun Reviews(destination: Destination){
    var list by remember { mutableStateOf(destination.comments) }
    Box(modifier = Modifier
        .fillMaxWidth()) {
        if (!list.isNullOrEmpty()){
            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .height(1000.dp)){
                items(list!!.size){
                    ReviewItem(comment = list!![it])
                }
            }
        }else{
            Text(text = "No comments yet",
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(
                    Alignment.Center))
        }
    }
}

@Composable
fun ReviewItem(comment: Comment){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 10.dp)) {
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
                Text(text = comment.author.name, color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(painter = painterResource(id = R.drawable.star), contentDescription = "", modifier = Modifier.size(15.dp))
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = comment.rating.toString(), fontSize = 14.sp)
                }
            }
        }
        Text(text = comment.text, modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, end = 5.dp), color = Color.Gray, fontSize = 14.sp)

        androidx.compose.material3.Divider(
            modifier = Modifier.padding(bottom = 5.dp, end = 10.dp, start = 10.dp, top = 10.dp),
            thickness = 1.dp,
            color = GreenPrimary
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Toolbar(scrollState: ScrollState) {
    val headerHeightPx = with(LocalDensity.current) { 300.dp.toPx() }
    val toolbarHeightPx = with(LocalDensity.current) { 56.dp.toPx() }

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
            modifier = Modifier.background(
                color = Color(android.graphics.Color.parseColor("#FF2F7A83"))
            ),
            title = {},
            colors = TopAppBarDefaults.mediumTopAppBarColors(Color.Transparent)
        )
    }
}

@Composable
private fun Title(name: String, scrollState: ScrollState) {
    var titleHeightPx by remember { mutableStateOf(0f) }
    val titleHeightDp = with(LocalDensity.current) { titleHeightPx.toDp() }
    val headerHeightPx = with(LocalDensity.current) { 300.dp.toPx() }
    val toolbarHeightPx = with(LocalDensity.current) { 56.dp.toPx() }
    Text(
        text = name,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .graphicsLayer {
                val collapseRange: Float = (headerHeightPx - toolbarHeightPx)
                val collapseFraction: Float = (scrollState.value / collapseRange).coerceIn(0f, 1f)

                val titleY = lerp(
                    280.dp - titleHeightDp - 20.dp, // start Y
                    58.dp / 2 - titleHeightDp / 2, // end Y
                    collapseFraction
                )

                val titleX = lerp(
                    10.dp, // start X
                    10.dp, // end X
                    collapseFraction
                )

                translationY = titleY.toPx()
                translationX = titleX.toPx()
            }
            .onGloballyPositioned {
                titleHeightPx = it.size.height.toFloat()
            },
        color = Color.White
    )
}

@Composable
fun ViewPager(pagerState: PagerState, listOfImages: List<String>, modifier: Modifier){
    HorizontalPager(state = pagerState, modifier = modifier) {
            SubcomposeAsyncImage(
                model = listOfImages[it],
                contentDescription = "",
                loading = { CircularProgressIndicator()},
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds)
        }
}

@androidx.annotation.OptIn(UnstableApi::class) @Composable
fun VideoPlayer() {
    val context = LocalContext.current
    val exoPlayer = remember {
        SimpleExoPlayer.Builder(context).build().apply {
            this.prepare()
        }
    }

}

@Composable
fun RatingBar(maxRating: Int, currentRating: Int, onRatingChanged: (Int) -> Unit){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 10.dp, end = 10.dp), horizontalArrangement = Arrangement.Center) {
        for (i in 1 .. maxRating){
            Icon(imageVector = if (i <= currentRating) Icons.Filled.Star else Icons.Filled.StarOutline,
                contentDescription = null,
                tint = if (i <= currentRating) Color(android.graphics.Color.parseColor("#FFC928")) else Color.Unspecified,
                modifier = Modifier
                    .clickable { onRatingChanged(i) }
                    .padding(5.dp)
                    .size(35.dp)
            )
        }
    }
}


