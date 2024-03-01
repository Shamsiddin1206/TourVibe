package shamsiddin.project.tourvibe.screen

import android.graphics.BlurMaskFilter.Blur
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.SubcomposeAsyncImage
import shamsiddin.project.tourvibe.R
import shamsiddin.project.tourvibe.model.CategoryData
import shamsiddin.project.tourvibe.model.Comment
import shamsiddin.project.tourvibe.model.Destination
import shamsiddin.project.tourvibe.model.User
import shamsiddin.project.tourvibe.navigation.ScreenType
import shamsiddin.project.tourvibe.utils.SharedPreferences

@Composable
fun GuideBook(navController: NavController) {
    val user = User(0, "Shamsiddin Tohirov", "takhirovshamsiddin@gmail.com", "+998 99 008 65 08", "Uzbekistan", null, null, null, "12345")
    val shared = SharedPreferences.getInstance(LocalContext.current)
    shared.setUser(user)
    val mutableList = mutableListOf<CategoryData>(
        CategoryData(
            "Beach",
            "https://t3.ftcdn.net/jpg/03/82/24/44/360_F_382244401_FNIivSDbE7ojw5sT70WYVgmFsw2R7DHD.jpg"
        ),
        CategoryData(
            "Beach",
            "https://t3.ftcdn.net/jpg/03/82/24/44/360_F_382244401_FNIivSDbE7ojw5sT70WYVgmFsw2R7DHD.jpg"
        ),
        CategoryData(
            "Beach",
            "https://t3.ftcdn.net/jpg/03/82/24/44/360_F_382244401_FNIivSDbE7ojw5sT70WYVgmFsw2R7DHD.jpg"
        ),
        CategoryData(
            "Beach",
            "https://t3.ftcdn.net/jpg/03/82/24/44/360_F_382244401_FNIivSDbE7ojw5sT70WYVgmFsw2R7DHD.jpg"
        ),
        CategoryData(
            "Beach",
            "https://t3.ftcdn.net/jpg/03/82/24/44/360_F_382244401_FNIivSDbE7ojw5sT70WYVgmFsw2R7DHD.jpg"
        )
    )
    val commentsList = mutableListOf<Comment>(
        Comment(0, "", user, "23/02/2024", "Farrukh Buriyev, Ibrohim Mukhtarov, Xoliqov Abdulbosit, Adilov Abdumajid",4.8)
    )
    val destinationList = mutableListOf<Destination>(
        Destination(0, "https://cdn.odysseytraveller.com/app/uploads/2019/07/registan-square-samarkand.jpg", listOf("https://www.whyweseek.com/wp-content/uploads/2019/06/registan-at-night-samarkand.jpg", "https://uzbek-travel.com/images/uz/Landmarks/Samarkand/The_Registan/4241211370_dd3f3136ff_b.jpg", "https://lifejourney4two.com/wp-content/uploads/2020/01/registan-square-samarkand_.jpg"), "The Registan", "Translated from Uzbek, “registan” means a sand place. In the ancient times, this central square was covered by sand. The territory was not initially surrounded by madrassah; those great erections appeared rather later. In that period, authorities of the city were gathering people on the square to announce khan’s orders, held celebrations and public executions, and collected the army leaving to war.\n" +
                "\n" +
                "In the past, one could see many trade rows around the square, where artisans and farmers were selling their goods. All main roads of Samarkand led to Registan where it was always noisy and lively.\n" +
                "\n" +
                "Various rulers during their reign would change the main significance of the square, but since those times and up to now, Registan has always been the center of the city social life.\n" +
                "\n" +
                "There are three madrassahs on the square: Ulugh Beg, Sherdor and Tilla-Kori, that are the main sights of the city. They were erected by two rulers at different times.", 4.8, commentsList, listOf(), "Uzbekistan", "Samarkand", "https://youtu.be/AyF6JsliDIc"),
        Destination(1, "https://cdn.elebase.io/173fe953-8a63-4a8a-8ca3-1bacb56d78a5/85de9252-cff1-4907-beea-e984ed9ccf1a-shutterstock_2084584372.jpg?w=1000&h=500&fit=crop&q=75", listOf("", "", ""), "Itchan Kala", "", 4.8, null, listOf(), "", "", ""),
        Destination(2, "https://uzbek-travel.com/images/uz/Landmarks/Tashkent/Amir_Temur_Square/amir_temur_square_4.jpg", listOf("", "", ""), "Amir Temur Square", "", 4.8, null, listOf(), "", "", ""),
        Destination(3, "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/18/03/00/52/la-moschea-di-kok-gumbaz.jpg?w=500&h=400&s=1", listOf("", "", ""), "Shakhrisabz", "", 4.8, null, listOf(), "", "", ""),
        Destination(4, "https://api.society.uz/media/news/BOZ_4642.medium.webp", listOf("", "", ""), "Jarkurgan Minaret", "", 4.8, null, listOf(), "", "", ""),
        Destination(5, "https://silkroaddestinations.com/wp-content/uploads/2016/12/sam-observatory-ulugbek-srd-860x424.jpg", listOf("", "", ""), "Ulugbek Observatory", "", 4.8, null, listOf(), "", "", "")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.person_default_ic),
                contentDescription = "",
                modifier = Modifier
                    .size(50.dp)
                    .clip(
                        RoundedCornerShape(50)
                    )
            )
            Spacer(modifier = Modifier.width(5.dp))
            Column {
                Text(text = "Hi! \uD83D\uDC4B", fontSize = 15.sp)
                Text(text = "Buriyev Farrukh", fontSize = 15.sp)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp)
        ) {
            items(mutableList) {
                CategoryItem(categoryData = it)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        val selected = remember {
            mutableIntStateOf(0)
        }
        LazyRow (
            Modifier
                .fillMaxWidth()
                .padding(start = 5.dp, end = 5.dp)) {
            val list = listOf("Tashkent", "Samarkand", "Bukhara", "Fergana", "Andijan", "Namangan")
            items(list.size + 1) {
                val name = if (it == 0) "All" else list[it - 1]
                StateItem(name = name, selected, it)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ){
            items(destinationList){
                PlaceItem(destination = it, navController = navController)
            }
        }
//        val destination = Destination(0, "https://helpx.adobe.com/content/dam/help/en/photoshop/using/convert-color-image-black-white/jcr_content/main-pars/before_and_after/image-before/Landscape-Color.jpg", listOf("", "", ""), "", "", (4.8), category = "", locatedCountry = "Uzbekistan", locatedState = "Samarkand", comments = null)
//        PlaceItem(destination, navController)

    }
}

@Composable
@Preview
fun GuideBookPreview() {
//    GuideBook(navController = rememberNavController())
//    PlaceItem(destination = destination, rememberNavController())
}

@Composable
fun CategoryItem(categoryData: CategoryData) {
    Card(
        modifier = Modifier
            .height(40.dp)
            .padding(end = 10.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            SubcomposeAsyncImage(
                model = categoryData.image,
                loading = { CircularProgressIndicator() },
                contentDescription = "",
                modifier = Modifier
                    .size(height = 35.dp, width = 35.dp)
                    .padding(start = (2.5).dp),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = categoryData.type,
                color = Color.Black,
                fontSize = 15.sp,
                modifier = Modifier.padding(start = 5.dp, end = 5.dp)
            )
        }
    }
}

@Composable
fun StateItem(name: String, selectedIndex: MutableState<Int>, index: Int) {
    Column (
        Modifier
            .clickable {
                selectedIndex.value = index
            }
            .padding(end = 5.dp, start = 5.dp)) {
        Text(text = name,
            color = if (selectedIndex.value != index) Color.Black else Color(android.graphics.Color.parseColor("#FF2F7A83")))
    }
}

@Composable
fun PlaceItem(destination: Destination, navController: NavController){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)
        .aspectRatio(0.8f)
        .clickable { navController.navigate("place_screen" + "/${destination}") },
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            SubcomposeAsyncImage(
                model = destination.mainImage,
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
                        Text(text = destination.name, color = Color.White, fontSize = 18.sp,fontWeight = FontWeight.Bold, modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth())
                    }
                }
            }
        }
    }
}


