@file:OptIn(ExperimentalMaterial3Api::class)

package shamsiddin.project.tourvibe.screen.menu


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import shamsiddin.project.tourvibe.model.Food
import shamsiddin.project.tourvibe.ui.theme.BACKGROUNDCARD
import shamsiddin.project.tourvibe.ui.theme.GreenPrimary
import shamsiddin.project.tourvibe.utils.Manager


var TAG = "TAG"
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Menu(navController: NavController) {
    var search = ""
    var foodlist by remember {
        mutableStateOf(listOf<Food>())
    }
    val focusRequester = remember { FocusRequester() }

    Manager.getFoods {
        foodlist = it.toMutableList()
        Log.d(TAG, "Menu: ${foodlist.joinToString()}")
    }


    Column {


        Scaffold(

        )

        {
            Spacer(modifier = Modifier.height(32.dp))
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 24.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly

                ) {
                    IconButton(onClick = { /* Handle settings click */ }) {
                        Icon(
                            painter = painterResource(id = shamsiddin.project.tourvibe.R.drawable.person),
                            modifier = Modifier.size(32.dp),
                            contentDescription = "Person",
                        )
                    }
                    Column {
                        Text(
                            text = "    Hello username",
                            color = Color.Gray,
                            modifier = Modifier.padding(start = 4.dp),

                            )

                        Row {
                            Icon(
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
                            painter = painterResource(id =shamsiddin.project.tourvibe.R.drawable.settings ),
                            modifier = Modifier.size(32.dp),
                            contentDescription = "Settings"
                        )
                    }
                }







                Spacer(modifier = Modifier.height(16.dp))
                // Search Bar and Filter Icon

                TextField(
                    value = search,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester = focusRequester),
                    onValueChange = {
                        search = it
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    shape = RoundedCornerShape(16.dp),
                    placeholder = {
                        Text(text = "Search", fontSize = 14.sp)
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "",
                            tint = Color.Black
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
//                textColor = Text2,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
//                cursorColor = Text2,
//                containerColor = Secondary
                    ),
                    textStyle = TextStyle(fontSize = 16.sp),
                )


                Spacer(modifier = Modifier.height(8.dp))
                // Lazy Column Content
                LazyColumn(
                    Modifier
                        .fillMaxHeight()
                        .padding(start = 5.dp, end = 5.dp)
                ) {

                    items(foodlist) { foodItem ->
                        FoodItem(foodItem,navController)

                    }
                }
            }
        }
    }
}


@Composable
fun FoodItem(food: Food,navController:NavController) {
    Spacer(modifier = Modifier.height(8.dp))
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp)
            .aspectRatio(1.8f)
            .clickable {
                navController.navigate("food_extended_screen" + "/${food}")
            },
        shape = RoundedCornerShape(30.dp),

        elevation = CardDefaults.cardElevation(5.dp) // CardDefaults.cardElevation is deprecated, you can directly use dp values
    ) {
        Box(modifier = Modifier.fillMaxHeight()) {
            SubcomposeAsyncImage(
                model = food.mainImage,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop,
                loading = { CircularProgressIndicator() }
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
                    .align(Alignment.BottomCenter),
                colors = CardDefaults.cardColors(BACKGROUNDCARD),
                elevation = CardDefaults.cardElevation(5.dp)// Same as above
            ) {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {


                    Column {
                        Text(
                            color = Color.White,
                            modifier = Modifier.padding(start = 20.dp),
                            text = food.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp
                        )

                        Text(
                            color = Color.White,
                            modifier = Modifier.padding(start = 20.dp, top = 5.dp),
                            text = food.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp
                        )
                    }
                    Text(
                        color = Color.White,
                        text = food.name,
                        modifier = Modifier.padding(end = 20.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp)
                }
            }
        }
    }
}


//@Composable
//fun Menu(navController: NavController) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White)
//            .verticalScroll(
//                rememberScrollState()
//            ),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(10.dp), verticalAlignment = Alignment.CenterVertically
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.person_default_ic),
//                contentDescription = "",
//                modifier = Modifier
//                    .size(70.dp).padding(10.dp)
//                    .clip(
//                        RoundedCornerShape(50)
//                    )
//            )
//            Spacer(modifier = Modifier.width(10.dp))
//            Column(horizontalAlignment = Alignment.CenterHorizontally) {
//
//                Text(text = "Hello"+" name", fontSize = 15.sp)
//            }
//
//            Image(
//                painter = painterResource(id = R.drawable.settings
//                ),
//                contentDescription = "",
//                modifier = Modifier
//                    .size(50.dp).padding(10.dp)
//                    .clip(
//                        RoundedCornerShape(50)
//                    )
//            )
//        }
//    }
//}