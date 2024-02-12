package shamsiddin.project.tourvibe.screen

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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import shamsiddin.project.tourvibe.ui.theme.GreenPrimary
import shamsiddin.project.tourvibe.ui.theme.PurpleGrey40

@Composable
fun GuideBook(navController: NavController) {
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(
                rememberScrollState()
            ),
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
        val destination = Destination(0, "https://cdn.odysseytraveller.com/app/uploads/2019/07/registan-square-samarkand.jpg", listOf("", "", ""), "", "", (4.8), category = "", locatedCountry = "Uzbekistan", locatedState = "Samarkand", comments = null)
        PlaceItem(destination = destination)

    }
}

@Composable
@Preview
fun GuideBookPreview() {
    val destination = Destination(0,"data:image/jpeg;base64,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", listOf("", "", ""), "", "", (4.8), category = "", locatedCountry = "Uzbekistan", locatedState = "Samarkand", comments = null)
//    GuideBook(navController = rememberNavController())
    PlaceItem(destination = destination)
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
//            AsyncImage(model = categoryData.image, contentDescription = "")
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
fun PlaceItem(destination: Destination){
    Card(modifier = Modifier
        .fillMaxWidth()
        .aspectRatio(1.5f),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            SubcomposeAsyncImage(model = destination.mainImage,
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds,
                loading = { CircularProgressIndicator()})
            Card(modifier = Modifier
                .blur(7.dp)
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, bottom = 15.dp)
                .align(Alignment.BottomCenter)) {
                Column {
                    Text(text = "The Registan", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 17.sp)
                }
            }
        }
    }
}
