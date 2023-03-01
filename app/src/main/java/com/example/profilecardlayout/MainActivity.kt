package com.example.profilecardlayout

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import coil.compose.rememberAsyncImagePainter
import com.example.profilecardlayout.ui.theme.MyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme() {
                UsersApplication(userProfileList)
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UserListScreen(userProfiles: List<User> = userProfileList, navController: NavHostController?) {
    Scaffold(topBar = { AppBar()}) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ){
            LazyColumn{
                items(userProfiles){ userProfile ->
                    if(!userProfile.status)
                        CompositionLocalProvider(values = *arrayOf(LocalContentAlpha provides ContentAlpha.disabled)) {
                            ProfileCard(user = userProfile){ navController?.navigate("user_details") }
                        }
                    else
                        ProfileCard(user = userProfile){ run { navController?.navigate("user_details") } }
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UserProfileDetailsScreen(userProfile: User = userProfileList[0]) {
    Scaffold(topBar = { AppBar() }) {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                ProfilePicture(userProfile.drawableId, userProfile.status, 240.dp)
                ProfileContent(userProfile.name, userProfile.status, Alignment.CenterHorizontally)
            }
        }
    }
}

@Composable
fun ProfileCard(user: User, clickAction: () -> Unit){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
            .wrapContentHeight(align = Alignment.Top)
            .clickable { clickAction.invoke() },
        elevation = 8.dp

    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ){
            ProfilePicture(user.drawableId, user.status, 72.dp)
            ProfileContent(user.name, user.status, Alignment.Start)
        }
    }
}

@Composable
fun ProfilePicture(drawableId: Int, status: Boolean, imageSize: Dp){
    val image: Painter = painterResource(id = R.drawable.ic_cat_letuce)
    Card(
        shape = CircleShape,
        border = BorderStroke(width = 2.dp, color = if(status)
            Color.Green
        else
            Color.Red
        ),
        modifier = Modifier.padding(16.dp),
        elevation = 4.dp
    ){
        Image(
            painter = rememberAsyncImagePainter("https://picsum.photos/200"),
            contentDescription = "letuce cat",
            modifier = Modifier.size(imageSize),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ProfileContent(userName: String, userStatus: Boolean, alignment: Alignment.Horizontal){
    Column(
        modifier = Modifier
            .padding(8.dp),
        horizontalAlignment = alignment
    )
    {
        Text(text=userName, style=MaterialTheme.typography.h5)
        //CompositionLocalProvider(values = *arrayOf(LocalContentAlpha provides ContentAlpha.medium)){
            Text(text= if(userStatus)"Online" else ("Offline"), style=MaterialTheme.typography.body2)
        //}
    }
}

@Composable
fun AppBar(){
    TopAppBar(
        title = { Text(text = "Messaging Application users") },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Home",
                Modifier.padding(horizontal = 12.dp)
            )
        },
    )
}

@Composable
fun UsersApplication(userProfiles: List<User> = userProfileList){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "users_list"){
        composable("users_list"){
            UserListScreen(userProfiles, null)
        }
        composable("user_details"){
            UserProfileDetailsScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserProfileDetailsPreview() {
    MyTheme() {
        UserProfileDetailsScreen()
    }
}

@Preview(showBackground = true)

@Composable
fun UserListPreview() {
    MyTheme() {
        UserListScreen(userProfiles = userProfileList, null)
    }
}
