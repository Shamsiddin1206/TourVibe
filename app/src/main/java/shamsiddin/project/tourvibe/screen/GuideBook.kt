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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.SubcomposeAsyncImage
import shamsiddin.project.tourvibe.R
import shamsiddin.project.tourvibe.model.CategoryData
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

    }
}

@Composable
@Preview
fun GuideBookPreview() {
    GuideBook(navController = rememberNavController())
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

}
