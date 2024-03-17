package shamsiddin.project.tourvibe.screen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.SubcomposeAsyncImage
import shamsiddin.project.tourvibe.R
import shamsiddin.project.tourvibe.navigation.ScreenType
import shamsiddin.project.tourvibe.ui.theme.GreenPrimary
import shamsiddin.project.tourvibe.ui.theme.profile_primary
import shamsiddin.project.tourvibe.utils.Manager
import shamsiddin.project.tourvibe.utils.SharedPreferences

@Composable
fun Profile(navController: NavController){
    val shared = SharedPreferences.getInstance(LocalContext.current)
    val context = LocalContext.current
//    val user = shared.getUser()!!

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 15.dp, end = 15.dp)
            .align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Profile", fontSize = 25.sp, fontWeight = FontWeight.Bold, color = profile_primary)
            Spacer(modifier = Modifier.height(30.dp))
            Box(modifier = Modifier.size(130.dp)) {
                Card(modifier = Modifier.fillMaxSize(), shape = RoundedCornerShape(50), border = BorderStroke(2.dp, GreenPrimary)) {
                    SubcomposeAsyncImage(model = R.drawable.person_default_ic, contentDescription = "", modifier = Modifier
                        .fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
                }
                Card(modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.BottomEnd)
                    .padding(end = 10.dp, bottom = 10.dp),
                    shape = RoundedCornerShape(50),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = BorderStroke(width = 1.dp, color = GreenPrimary)) {
                    IconButton(onClick = { /*TODO*/ }, modifier = Modifier
                        .fillMaxSize()
                        .padding(2.dp)) {
                        Icon(imageVector = Icons.Outlined.Edit, contentDescription = "")
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Shamsiddin Tohirov", fontWeight = FontWeight.Bold, color = profile_primary, fontSize = 25.sp)
            Spacer(modifier = Modifier.height(15.dp))
            ProfileOutlinedEditText(string = "Shamsiddin Tohirov", type = "name")
            Spacer(modifier = Modifier.height(10.dp))
            ProfileOutlinedEditText(string = "takhirovshamsiddin@gmail.com", type = "email")
            Spacer(modifier = Modifier.height(10.dp))
            ProfileOutlinedEditText(string = "+998 99 008 65 08", type = "phone")
            Spacer(modifier = Modifier.height(10.dp))
            ProfileOutlinedEditText(string = "Uzbekistan", type = "country")
            Spacer(modifier = Modifier.height(10.dp))
//            Card(modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = 10.dp, end = 10.dp), shape = RoundedCornerShape(10.dp), elevation = CardDefaults.cardElevation(5.dp), colors = CardDefaults.cardColors(
//                Color.White)) {
//                Row(modifier = Modifier.fillMaxWidth()) {
//                    Icon(painter = painterResource(id = R.drawable.logout_ic), contentDescription = )
//                }
//            }


//            Button(onClick = {
//                Manager.giveToken(context,"")
//                navController.navigate(ScreenType.Login.route)
//            }) {
//                androidx.compose.material3.Text(text = "log out", color = Color.Red)
//            }
        }
    }
}

@Composable
fun ProfileCard(){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(40.dp), shape = RoundedCornerShape(5.dp), colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {

    }
}

@Composable
fun ProfileOutlinedEditText(string: String, type: String){
    var text by remember { mutableStateOf(string) }
    var state by remember { mutableStateOf(true) }

    val mainIcon = when (type) {
        "phone" -> Icons.Outlined.Phone
        "email" -> Icons.Outlined.Email
        "name" -> Icons.Outlined.Person
        else -> Icons.Outlined.LocationOn
    }
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 10.dp, end = 10.dp), shape = RoundedCornerShape(10.dp), elevation = CardDefaults.cardElevation(5.dp), colors = CardDefaults.cardColors(
        Color.White)) {
        OutlinedTextField(
            value = text,
            onValueChange = {text = it},
            readOnly = state,
            trailingIcon = {
                if (state){
                    IconButton(onClick = { state = false }, modifier = Modifier.size(30.dp)) {
                        Icon(painter = painterResource(id = R.drawable.edit_ic), contentDescription = "", tint = profile_primary)
                    }
                }else{
                    IconButton(onClick = {
                        state = true
                    }, modifier = Modifier.size(30.dp)) {
                        Icon(painter = painterResource(id = R.drawable.confirm_ic), contentDescription = "", tint = profile_primary)
                    }
                }
            },
            leadingIcon = { Icon(imageVector = mainIcon, contentDescription = "", tint = profile_primary)},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            textStyle = TextStyle(color = profile_primary, fontSize = 15.sp),
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = GreenPrimary, unfocusedBorderColor = Color.Unspecified),
            singleLine = true
        )
    }
}

@Composable
@Preview
fun ProfilePreview(){
    Profile(navController = rememberNavController())
}

