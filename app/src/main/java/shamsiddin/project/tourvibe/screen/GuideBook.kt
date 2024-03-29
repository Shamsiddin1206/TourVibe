package shamsiddin.project.tourvibe.screen

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import coil.compose.SubcomposeAsyncImage
import shamsiddin.project.tourvibe.R
import shamsiddin.project.tourvibe.model.Comment
import shamsiddin.project.tourvibe.model.Destination
import shamsiddin.project.tourvibe.model.User
import shamsiddin.project.tourvibe.utils.Manager
import shamsiddin.project.tourvibe.utils.SharedPreferences

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "MutableCollectionMutableState")
@Composable
fun GuideBook(navController: NavController) {
    val context = LocalContext.current
    val shared = SharedPreferences.getInstance(LocalContext.current)
    val user = shared.getUser()!!


    var allDestinations by remember { mutableStateOf(listOf<Destination>()) }
    var destinationList by remember { mutableStateOf(mutableListOf<Destination>()) }
    Manager.getDestinations {
        allDestinations = it.toMutableList()
    }

    //Category
    var list by remember { mutableStateOf(listOf<String>()) }
    Manager.getDestinationStates {
        list = it.reversed()
    }

    val selected = remember { mutableIntStateOf(0) }

    if (selected.intValue == 0){
        destinationList = allDestinations.toMutableList()
    }else{
        destinationList.clear()
        allDestinations.forEach {
            if (it.locatedState == list[selected.intValue-1]){
                destinationList.add(it)
            }
        }
        Log.d("Dest", "GuideBook: ${destinationList.joinToString()}")
    }


    //Search
    val searchList = remember { mutableStateListOf<Destination>() }
    var searchText by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
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
                Text(text = user.name, fontSize = 15.sp)
            }
        }
//        Spacer(modifier = Modifier.height(10.dp))
//        LazyRow(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = 10.dp)
//        ) {
//            items(mutableList) {
//                CategoryItem(categoryData = it)
//            }
//        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow (
            Modifier
                .fillMaxWidth()
                .padding(start = 5.dp, end = 5.dp)) {
            items(list.size + 1) {
                val name = if (it == 0) "All" else list[it - 1]
                Column (
                    Modifier
                        .clickable {
                            selected.intValue = it
                        }
                        .padding(end = 5.dp, start = 5.dp)) {
                    Text(text = name,
                        color = if (selected.intValue != it) Color.Black else Color(android.graphics.Color.parseColor("#FF2F7A83")))
                }
            }
        }

        Scaffold(modifier = Modifier.background(Color.White), containerColor = Color.White) {
            SearchBar(
                modifier = Modifier.fillMaxWidth().padding(start = if (active) 0.dp else 10.dp, end = if (active) 0.dp else 10.dp),
                query = searchText,
                onQueryChange = { searchText = it},
                onSearch = {
                    active = false
                },
                active = active,
                onActiveChange = { active = it },
                placeholder = {
                    Text(text = "Search by name")
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "")
                },
                trailingIcon = {
                    if (active){
                        Icon(imageVector = Icons.Default.Clear, contentDescription = "", modifier = Modifier.clickable {
                            if (searchText.isNotEmpty()){
                                searchText = ""
                            }else {
                                active = false
                            }
                            searchList.clear()
                        })
                    }
                },
            ){
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2)
                ){
                    if (active){
                        if (searchText.isNotEmpty()){
                            searchList.clear()
                            allDestinations.forEach {
                                if (it.name.trim().toLowerCase().contains(searchText.trim().toLowerCase())){
                                    if (selected.intValue == 0){
                                        searchList.add(it)
                                    }else if (it.locatedState == list[selected.intValue-1]){
                                        searchList.add(it)
                                    }
                                }
                            }
                            if (searchList.isNotEmpty()){
                                items(searchList.size){ index ->
                                    PlaceItem(destination = searchList[index], navController = navController)
                                }
                            }else{
                                Toast.makeText(context, "Not Found", Toast.LENGTH_SHORT).show()
                                return@LazyVerticalGrid
                            }
                        }
                    }
                }
            }
            if (!active){
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(top = 70.dp)
                ){
                    items(destinationList){
                        PlaceItem(destination = it, navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun GuideBookPreview() {
//    GuideBook(navController = rememberNavController())
//    PlaceItem(destination = destination, rememberNavController())
}

//@Composable
//fun CategoryItem(categoryData: CategoryData) {
//    Card(
//        modifier = Modifier
//            .height(40.dp)
//            .padding(end = 10.dp),
//        elevation = CardDefaults.cardElevation(5.dp),
//        shape = RoundedCornerShape(5.dp),
//        colors = CardDefaults.cardColors(Color.White)
//    ) {
//        Row(
//            modifier = Modifier.fillMaxHeight(),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.Center
//        ) {
//            SubcomposeAsyncImage(
//                model = categoryData.image,
//                loading = { CircularProgressIndicator() },
//                contentDescription = "",
//                modifier = Modifier
//                    .size(height = 35.dp, width = 35.dp)
//                    .padding(start = (2.5).dp),
//                contentScale = ContentScale.FillBounds
//            )
//            Text(
//                text = categoryData.type,
//                color = Color.Black,
//                fontSize = 15.sp,
//                modifier = Modifier.padding(start = 5.dp, end = 5.dp)
//            )
//        }
//    }
//}

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


