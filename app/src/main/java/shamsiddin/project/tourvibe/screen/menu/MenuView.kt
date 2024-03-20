@file:OptIn(ExperimentalMaterial3Api::class)

package shamsiddin.project.tourvibe.screen.menu


import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import shamsiddin.project.tourvibe.model.Destination
import shamsiddin.project.tourvibe.model.Food
import shamsiddin.project.tourvibe.ui.theme.BACKGROUNDCARD
import shamsiddin.project.tourvibe.utils.Manager
import shamsiddin.project.tourvibe.utils.SharedPreferences


var TAG = "TAG"

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Menu(navController: NavController) {
    var search by remember { mutableStateOf("") }
    var state by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val focusRequester = remember { FocusRequester() }
//    val user = viewModel.user.observeAsState().value
    val user = SharedPreferences.getInstance(LocalContext.current).getUser()!!

    var foodList by remember {
        mutableStateOf(listOf<Food>())
    }

    Manager.getFoods {
        foodList = it
        Log.d(TAG, "Foodlist: ${foodList}")
    }

    Column {
        Scaffold(

        )

        {
            Spacer(modifier = Modifier.height(32.dp))
            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly

                ) {
                    IconButton(onClick = { /* Handle settings click */ }) {
                        Image(
                            painter = painterResource(id = shamsiddin.project.tourvibe.R.drawable.person_default_ic),
                            modifier = Modifier.size(32.dp),
                            contentDescription = "Person",
                        )
                    }
                    Column {
                        Text(
                            text = "    Hello ${user.name}",
                            color = Color.Gray,
                            modifier = Modifier.padding(start = 4.dp),

                            )

                        Row {
                            Image(
                                painter = painterResource(id = shamsiddin.project.tourvibe.R.drawable.location),
                                modifier = Modifier.size(28.dp),

                                contentDescription = "location",
                            )
//                            Spacer(modifier = Modifier.height(3.dp))
                            Text(
                                text = "state, country ",
                                color = Color.Black,
                                fontSize = 17.sp,
                                modifier = Modifier.padding(start = 4.dp, top = 5.dp),

                                )
                        }
                    }


                    IconButton(onClick = { /* Handle settings click */ }) {
                        Icon(
                            painter = painterResource(id = shamsiddin.project.tourvibe.R.drawable.settings),
                            modifier = Modifier.size(32.dp),
                            contentDescription = "Settings"
                        )
                    }
                }

                var categories by remember {
                    mutableStateOf(listOf<String>())
                }

                var categoriesFood by remember {
                    mutableStateOf(listOf<Food>())
                }

                Manager.getFoodsAllCategory {
                    categories = it
                }


                val category = remember { mutableStateOf("") }

                categoriesFood = foodList


//////categorylarni foodlari 1ta category tanlasa osha foodlar chiqadi

                Spacer(modifier = Modifier.padding(16.dp))
                LazyRow(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp)
                ) {
                    items(categories.size + 1) {
                        val cnt_category = if (it == 0) "All" else categories[it - 1]
                        Card(
                            modifier = Modifier
                                .height(40.dp)
                                .padding(end = 10.dp)
                                .clickable {
                                    category.value = cnt_category
                                    Log.d(TAG, "Menu: ${category.value}")
                                    if (category.value == "All") {
                                        categoriesFood = foodList
                                    } else {
                                        Manager.getCategoryFoods(category.value) {
                                            categoriesFood = it
                                        }

                                    }
                                },
                            elevation = CardDefaults.cardElevation(5.dp),
                            shape = RoundedCornerShape(5.dp),
                            colors = CardDefaults.cardColors(Color.White)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxHeight(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = cnt_category,
                                    color = if (category.value != cnt_category) Color.Black else Color(
                                        android.graphics.Color.parseColor(
                                            "#A5DD9B"
                                        )
                                    ),
                                    fontSize = 15.sp,
                                    modifier = Modifier.padding(start = 5.dp, end = 5.dp)
                                )
                            }
                        }
                    }
                }



                Spacer(modifier = Modifier.padding(16.dp))
                var foodSearchList by remember { mutableStateOf(mutableListOf<Food>()) }
                val selected = remember { mutableIntStateOf(0) }


/////search bar foodlarni name boyicha filter qilish
                Scaffold(
                ) {
                    SearchBar(
                        query = search,
                        onQueryChange = { search = it },
                        onSearch = {
                            state = false
                        },
                        active = state,
                        onActiveChange = { state = it },
                        placeholder = {
                            Text(text = "Search by food name")
                        },
                        leadingIcon = {
                            Icon(imageVector = Icons.Filled.Search, contentDescription = "")
                        },
                        trailingIcon = {
                            if (state) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "",
                                    modifier = Modifier.clickable {
                                        if (search.isNotEmpty()) {
                                            search = ""
                                        } else {
                                            state = false
                                        }
//                                        foodSearchList.clear()
                                    })
                            }
                        },
                    ) {
                        LazyColumn(
                        ) {
                            if (state) {
                                if (search.isNotEmpty()) {
                                    foodSearchList.clear()
                                    categoriesFood.forEach {
                                        if (it.name.trim().toLowerCase()
                                                .contains(search.trim().toLowerCase())
                                        ) {
                                            Log.d("NIMADIR CHIQISHI KERAK", "JAVOB BERAMAN ")
                                            foodSearchList.add(it)
                                        }
                                    }
                                    if (foodSearchList.isNotEmpty()) {
                                        items(foodSearchList) { food ->
                                            FoodItem(
                                                food = food,
                                                navController = navController
                                            )
                                        }
                                    } else {
                                        Toast.makeText(context, "not found", Toast.LENGTH_SHORT)
                                            .show()

                                    }
                                }
                            }
                        }
                    }
                    if (!state) {
                        LazyColumn(
                            modifier = Modifier.padding(top = 70.dp)
                        ) {
                            items(categoriesFood) {
                                FoodItem(it, navController = navController)
                            }
                        }
                    }
                }


////foodlar massivini chiqaramiz
                LazyColumn(
                    Modifier
                        .fillMaxHeight()
                        .padding(start = 5.dp, end = 5.dp)
                ) {

                    items(categoriesFood) { foodItem ->
                        FoodItem(foodItem, navController)

                    }
                }
            }
        }
    }
}





@Composable
fun FoodItem(food: Food, navController: NavController) {
    Spacer(modifier = Modifier.height(8.dp))
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp)
            .aspectRatio(1.8f)
            .clickable {
                navController.navigate("food_extended_screen" + "/${food}")
            }, shape = RoundedCornerShape(30.dp),

        elevation = CardDefaults.cardElevation(5.dp) // CardDefaults.cardElevation is deprecated, you can directly use dp values
    ) {
        Box(modifier = Modifier.fillMaxHeight()) {
            SubcomposeAsyncImage(model = food.mainImage,
                contentDescription = "",
                modifier = Modifier.fillMaxHeight(),
                contentScale = ContentScale.Crop,
                loading = { CircularProgressIndicator() })
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
                    .align(Alignment.BottomCenter),
                colors = CardDefaults.cardColors(BACKGROUNDCARD),
                elevation = CardDefaults.cardElevation(5.dp)// Same as above
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {


                    Column {
                        Text(
                            color = Color.White,
                            modifier = Modifier.padding(start = 20.dp, top = 10.dp, bottom = 10.dp),
                            text = food.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 19.sp
                        )

                    }
                    Text(
                        color = Color.White,
                        text = food.locatedCountry,
                        modifier = Modifier.padding(end = 20.dp, top = 10.dp, bottom = 10.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    )
                }
            }
        }
    }
}