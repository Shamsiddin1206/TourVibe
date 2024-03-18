package shamsiddin.project.tourvibe.screen

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Icon
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.IconButton
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.OutlinedTextField
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import shamsiddin.project.tourvibe.ui.theme.ProfilePrimary
import shamsiddin.project.tourvibe.utils.SharedPreferences

@Composable
fun Profile(navController: NavController){
    val shared = SharedPreferences.getInstance(LocalContext.current)
    val context = LocalContext.current
    val user = shared.getUser()!!

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 15.dp, end = 15.dp)
            .align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Profile", fontSize = 25.sp, fontWeight = FontWeight.Bold, color = ProfilePrimary)
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
            Text(text = user.name, fontWeight = FontWeight.Bold, color = ProfilePrimary, fontSize = 25.sp)
            Spacer(modifier = Modifier.height(15.dp))
            ProfileOutlinedEditText(string = user.name, type = "name")
            Spacer(modifier = Modifier.height(10.dp))
            ProfileOutlinedEditText(string = user.email, type = "email")
            Spacer(modifier = Modifier.height(10.dp))
            ProfileOutlinedEditText(string = if (!user.number.isNullOrEmpty()) user.number.toString() else "Add Phone Number", type = "phone")
            Spacer(modifier = Modifier.height(10.dp))
            ProfileOutlinedEditText(string = user.country!!, type = "country")
            Spacer(modifier = Modifier.height(10.dp))

            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
                .height(50.dp)
                .clickable {
                    shared.setUser(null)
                    navController.navigate(ScreenType.Login.route)
                }, shape = RoundedCornerShape(10.dp), elevation = CardDefaults.cardElevation(5.dp), colors = CardDefaults.cardColors(
                Color.White)) {
                Spacer(modifier = Modifier.width(5.dp))
                Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(painter = painterResource(id = R.drawable.logout_ic), contentDescription = "", modifier = Modifier.size(25.dp), tint = ProfilePrimary)
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "Log Out", color = ProfilePrimary, fontSize = 15.sp)
                }
            }
        }
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
                    IconButton(onClick = { state = false }, modifier = Modifier.size(25.dp)) {
                        Icon(painter = painterResource(id = R.drawable.edit_ic), contentDescription = "", tint = ProfilePrimary)
                    }
                }else{
                    IconButton(onClick = {
                        state = true
                    }, modifier = Modifier.size(25.dp)) {
                        Icon(painter = painterResource(id = R.drawable.confirm_ic), contentDescription = "", tint = ProfilePrimary)
                    }
                }
            },
            leadingIcon = { Icon(imageVector = mainIcon, contentDescription = "", tint = ProfilePrimary)},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            textStyle = TextStyle(color = ProfilePrimary, fontSize = 15.sp),
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

