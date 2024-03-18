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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import shamsiddin.project.tourvibe.R
import shamsiddin.project.tourvibe.model.Hotel
import shamsiddin.project.tourvibe.utils.Manager

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Hotels(navController: NavController){
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF)),

        ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "Hotel",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
            Box(
                modifier = Modifier
                    .size(248.dp)
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFF292929),
                                Color.Transparent
                            ),
                            startX = 100f
                        )
                    )
            )
            Column(
                Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 10.dp, top = 7.dp)
            ) {
                Text(
                    text = "Let's",
                    color = Color.White,
                    fontSize = 20.sp
                )
                Spacer(Modifier.fillMaxWidth(0.2f))
                Text(
                    "Explore",
                    color = Color.White,
                    fontSize = 28.sp
                )
            }
        }
        //Category
        var list by remember { mutableStateOf(listOf<String>()) }
        Manager.getHotelCities {
            list = it.reversed()
        }

        var allhotels by remember { mutableStateOf(listOf<Hotel>()) }
        var hotellist by remember { mutableStateOf(mutableListOf<Hotel>()) }
        Manager.getHotels {
            allhotels = it.toMutableList()
        }

        val selected = remember { mutableIntStateOf(0) }
        if (selected.intValue == 0) {
            hotellist = allhotels.toMutableList()
        } else {
            hotellist.clear()
            allhotels.forEach {
                if (it.locatedCountry == list[selected.intValue - 1]) {
                    hotellist.add(it)
                }
            }
            Log.d("Dest", "GuideBook: ${hotellist.joinToString()}")
        }
        //Search
        val searchList = remember { mutableStateListOf<Hotel>() }
        var searchText by remember { mutableStateOf("") }
        var active by remember { mutableStateOf(false) }

        Spacer(modifier = Modifier.height(20.dp))
        LazyRow(
            Modifier
                .fillMaxWidth()
                .padding(start = 5.dp, end = 5.dp)
        ) {
            items(list.size + 1) {
                val name = if (it == 0) "All" else list[it - 1]
                Column(
                    Modifier
                        .clickable {
                            selected.intValue = it
                        }
                        .padding(end = 5.dp, start = 5.dp)) {

                    Surface(
                        color = if (selected.intValue != it) Color.White else Color(
                            android.graphics.Color.parseColor(
                                "#FF2F7A83"
                            )
                        ),
                        shape = RoundedCornerShape(10.dp),
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(5.dp),
                            text = name,
                            fontSize = 20.sp,
                            color = if (selected.intValue != it) Color.Black else Color.White
                        )

                    }
                }
            }
        }


        Scaffold(modifier = Modifier.background(Color.White), containerColor = Color.White) {
            SearchBar(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                query = searchText,
                onQueryChange = { searchText = it },
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
                    if (active) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "",
                            modifier = Modifier.clickable {
                                if (searchText.isNotEmpty()) {
                                    searchText = ""
                                } else {
                                    active = false
                                }
                                searchList.clear()
                            })
                    }
                },
            ) {
                LazyColumn(
//                    rows = GridCells.Fixed(2)
                ) {

                    if (active) {
                        if (searchText.isNotEmpty()) {
                            searchList.clear()
                            allhotels.forEach {
                                if (it.name.trim().toLowerCase()
                                        .contains(searchText.trim().toLowerCase())
                                ) {
                                    if (selected.intValue == 0) {
                                        searchList.add(it)
                                    } else if (it.locatedCountry == list[selected.intValue - 1]) {
                                        searchList.add(it)
                                    }
                                }
                            }
                            if (searchList.isNotEmpty()) {
                                items(searchList.size) { index ->
                                    HotelItem(
                                        hotel = searchList[index],
                                        navHostController = navController
                                    )
                                }
                            } else {
                                Toast.makeText(context, "Not Found", Toast.LENGTH_SHORT).show()
                                return@LazyColumn
                            }
                        }
                    }
                }
            }
            if (!active) {

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(top = 70.dp)
                ) {

                    items(hotellist.size) {
                        Log.d("Nima gap", "HotelScreen: ${hotellist.size}")
                        HotelItem(hotel = hotellist[it], navHostController = navController)
                    }
                }
            }
        }


        Spacer(modifier = Modifier.height(1000.dp))
    }
}

@Composable
fun HotelItem(hotel: Hotel, navHostController: NavController) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFFDAE1E7),
        modifier = Modifier
            .width(250.dp)
            .padding(10.dp)
            .clickable { navHostController.navigate("single_hotel" + "/${hotel}") },
        shadowElevation = 10.dp
    ) {
        Log.d("TAG", "HotelItem: ${hotel.name}")
        Column(
            Modifier.background(Color(0xFF292929))
        ) {
            SubcomposeAsyncImage(
                model = hotel.mainImage,
                contentDescription = "",
                loading = { CircularProgressIndicator() },
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(200.dp)
            )
            Column(
                Modifier.padding(10.dp)
            ) {
                Text(
                    text = hotel.name,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_location),
                        contentDescription = "Location"
                    )
                    Log.d("Citysi", "HotelItem: ${hotel.locatedState}")
                    Text(
                        text = hotel.locatedState,
                        color = Color(0xFF878787)
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$${hotel.price.toInt()}/", color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )

                    Text(
                        text = "night", color = Color.White,
                        fontSize = 15.sp,
                    )
                }
            }
        }
    }
}