package shamsiddin.project.tourvibe.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import shamsiddin.project.tourvibe.R
import shamsiddin.project.tourvibe.utils.SharedPreferences

@Composable
fun Profile(navController: NavController){
    val shared = SharedPreferences.getInstance(LocalContext.current)
    val user = shared.getUser()

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 15.dp, end = 15.dp)
            .align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Profile", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(modifier = Modifier.height(30.dp))
            Box(modifier = Modifier.size(150.dp)) {
                SubcomposeAsyncImage(model = if (user!!.image.isNullOrEmpty()) R.drawable.person_default_ic else user.image, contentDescription = "", modifier = Modifier
                    .fillMaxSize()
                    .clip(
                        CircleShape
                    ))
                Card(modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.BottomEnd)
                    .padding(end = 10.dp, bottom = 10.dp),
                    shape = RoundedCornerShape(50),
                    backgroundColor = Color.White) {
                    IconButton(onClick = { /*TODO*/ }, modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                        .padding(2.dp)) {
                        Icon(imageVector = Icons.Outlined.Edit, contentDescription = "")
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = user!!.name, fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 25.sp)

        }
    }
}

@Composable
fun ProfileCard(){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(40.dp), shape = RoundedCornerShape(5.dp), backgroundColor = Color.White) {

    }
}