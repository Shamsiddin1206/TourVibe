package shamsiddin.project.tourvibe.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import shamsiddin.project.tourvibe.R

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Countries(navController: NavController){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .verticalScroll(
            rememberScrollState()
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = R.drawable.person_default_ic),
                contentDescription = "",
                modifier = Modifier
                    .size(50.dp)
                    .clip(
                        RoundedCornerShape(50)
                    ))
            Spacer(modifier = Modifier.width(5.dp))
            Column {
                Text(text = "Hi! \uD83D\uDC4B", fontSize = 15.sp)
                Text(text = "Buriyev Farrukh", fontSize = 15.sp)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp),
            verticalAlignment = Alignment.Bottom) {
            Text(text = "Let's choose ", color = Color.Gray, fontSize = 22.sp)
            Text(text = "Country!", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 22.sp)
        }
        Scaffold {

        }

    }
}

@Composable
@Preview
fun CountriesPreview(){
    Countries(navController = rememberNavController())
}