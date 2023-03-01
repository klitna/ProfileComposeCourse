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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

import coil.compose.rememberAsyncImagePainter
import com.example.profilecardlayout.ui.theme.MyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme() {
                UsersApplication()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UserListScreen(userProfiles: List<User> = userProfileList, navController: NavHostController?) {
    Scaffold(topBar = {
        AppBar(
            title = "Users list",
            icon = Icons.Default.Home
        ) { }
    }) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ){
            LazyColumn {
                items(userProfiles) { user ->
                    ProfileCard(user = user) {
                        navController?.navigate("user_details/${user.id}")
                    }
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UserProfileDetailsScreen(userId: Int, navController: NavHostController?) {
    val userProfile = userProfileList.first { userProfile -> userId == userProfile.id }
    Scaffold(topBar = {
        AppBar(
            title = "User profile details",
            icon = Icons.Default.ArrowBack
        ) {
            navController?.navigateUp()
        }
    }) {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                ProfilePicture(userProfile.drawableId, userProfile.name, userProfile.status, 240.dp)
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
            ProfilePicture(user.drawableId, user.name, user.status, 72.dp)
            ProfileContent(user.name, user.status, Alignment.Start)
        }
    }
}

@Composable
fun ProfilePicture(drawableId: String, userName: String, status: Boolean, imageSize: Dp){
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
            painter = rememberAsyncImagePainter(drawableId+userName),
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
fun AppBar(title: String, icon: ImageVector, iconClickAction: () -> Unit){
    TopAppBar(
        navigationIcon = {
            Icon(
                icon,
                contentDescription = "",
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .clickable(onClick = { iconClickAction.invoke() })
            )
        },
        title = { Text(title) }
    )
}

@Composable
fun UsersApplication(userProfiles: List<User> = userProfileList){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "users_list") {
        composable("users_list") {
            UserListScreen(userProfiles, navController)
        }
        composable(
            route = "user_details/{userId}",
            arguments = listOf(navArgument("userId") {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            UserProfileDetailsScreen(navBackStackEntry.arguments!!.getInt("userId"), navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserProfileDetailsPreview() {
    MyTheme() {
        UserProfileDetailsScreen(userId = 0, null)
    }
}

@Preview(showBackground = true)

@Composable
fun UserListPreview() {
    MyTheme() {
        UserListScreen(userProfiles = userProfileList, null)
    }
}
