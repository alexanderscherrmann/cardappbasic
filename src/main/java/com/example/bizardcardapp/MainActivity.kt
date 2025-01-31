package com.example.bizardcardapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.bizardcardapp.ui.theme.BizardcardAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BizardcardAppTheme {
                BizCardTest()
            }
        }
    }
}

@Composable
fun BizCardTest(){
    val buttonClickedState = remember{
        mutableStateOf(value = false)
    }
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
    ){
        Card(modifier = Modifier
            .width(200.dp)
            .height(390.dp)
            .padding(12.dp)
            .clickable { },
//            colors= CardDefaults.cardColors(
//                containerColor = Color.White
//            ),
            shape = RoundedCornerShape(
                corner = CornerSize(15.dp)
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp)
        ){
            Column(modifier = Modifier
                .fillMaxHeight(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                CreateProfileImage()
                // fillMaxWidth fills 70% of the available width. Around that the content is centered
                // and from these borders the padding is inserted
                HorizontalDivider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 90.dp, end = 90.dp),
                    color=Color.Gray,
//                    thickness = 2.dp
                )
                CreateProfileInfo()
                Button(
                    onClick = {
                    buttonClickedState.value = !buttonClickedState.value
                    // provoke by logd
                    //Log.d("ClickedTag", "BizCardTest: Button clicked")
                    },
                    modifier = Modifier.padding(top = 10.dp)
                ){
                    Text("Portfolio",
                        style = MaterialTheme.typography.labelLarge)
                }
                if (buttonClickedState.value){
                    Portfolios()
                }
                else{
                    Box{}
                }
            }
        }
    }
}

//@Preview
@Composable
private fun Portfolios(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)
    ){
        Surface(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(width=2.dp,
                color = Color.LightGray)
        ){
            Portfolio(data = listOf("Portfolio1","Portfolio2",
                "Portfolio3","Portfolio4"))
        }
    }

}

@Composable
fun Portfolio(data: List<String>) {
    LazyColumn {
        items(data){ item ->
            Card(modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
                shape = RectangleShape,
                elevation = CardDefaults.cardElevation(4.dp)){
                Row(modifier = Modifier
                    .padding(4.dp)
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(8.dp)
                ){
                    CreateProfileImage(modifier = Modifier.size(50.dp))
                    Column(modifier = Modifier
                        .padding(4.dp)
                        .align(alignment = Alignment.CenterVertically)
                    ){
                        Text(text = item,fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodyMedium)
                        Text(text="Nice first Project",
                            style = MaterialTheme.typography.bodySmall)
                    }

                }
            }
        }
    }
}

@Composable
private fun CreateProfileInfo() {
    Column(modifier = Modifier.padding(5.dp))
    {
        Text(
            text = "Alexander Scherrmann",
            color = Color.Blue,
            style = MaterialTheme.typography.headlineSmall,
            fontFamily = FontFamily.SansSerif,
            fontSize = TextUnit.Unspecified
        )
        Text(
            text = "Data Scientist & Gesundheitsvernatiker",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(3.dp)
        )
        Text(
            text = "Based in Zurich",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(3.dp)
        )
    }
}


@Composable
private fun CreateProfileImage(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .size(size = 150.dp) //width = 150.dp, height = 150.dp
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(width = 0.5.dp, color = Color.LightGray),
        shadowElevation = 4.dp,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.cvbild),
            contentDescription = "Profile image",
            modifier = modifier.size(100.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BizardcardAppTheme {
        BizCardTest()
    }
}