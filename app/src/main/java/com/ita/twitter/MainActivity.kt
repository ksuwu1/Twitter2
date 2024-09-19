package com.ita.twitter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TwitterMainScreen()
        }

        // Configurar el color de la barra de estado y la barra de navegación
        WindowCompat.getInsetsController(window, window.decorView).let { controller ->
            controller.isAppearanceLightStatusBars = true
            controller.isAppearanceLightNavigationBars = true
        }

        window.statusBarColor = ContextCompat.getColor(this, R.color.white) // Cambia R.color.white por el color deseado
        window.navigationBarColor = ContextCompat.getColor(this, R.color.white) // Cambia R.color.white por el color deseado
    }
}


@Composable
fun TwitterMainScreen() {
    // Lista de Tweets
    val tweets = listOf(
        TweetData(
            authorName = "Cerebros",
            username = "@Cerebros ∙ 6h",
            content = "De último momento: Senado aprueba que el 1 de octubre, cada seis años, sea día de descanso obligatorio por el cambio de Gobierno Federal.",
            profileImageResId = R.drawable.user1,
            imageResId = R.drawable.tw1,
            commentsCount = "59",
            retweetsCount = "414",
            likesCount = "7.9K",
            statisticsCount = "215K",
        ),
        TweetData(
            authorName = "Xo",
            username = "@xoytoxica ∙ 1d",
            content = "El google maps se puso 'alarmante'",
            profileImageResId = R.drawable.user2,
            imageResId = R.drawable.tw2,
            commentsCount = "5K",
            retweetsCount = "600",
            likesCount = "100K",
            statisticsCount = "1M",
        )
    )

    var selectedItem by remember { mutableStateOf(0) }
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(selectedItem = selectedItem, onItemSelected = { selectedItem = it })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Manejar redacción de nuevo tweet */ },
                backgroundColor = Color(0xFF1DA1F2)
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "New Tweet")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Barra Superior (Header)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                    .height(50.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Icono de Perfil con sangría hacia la izquierda
                Image(
                    painter = painterResource(id = R.drawable.ksuwu),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .size(30.dp)
                        .clip(CircleShape)
                )
                Image(
                    painter = painterResource(id = R.drawable.logox),
                    contentDescription = "Twitter Logo",
                    modifier = Modifier.size(16.dp)
                )
                // Icono de Configuración con sangría hacia la derecha
                Image(
                    painter = painterResource(id = R.drawable.settings),
                    contentDescription = "Settings",
                    modifier = Modifier
                        .padding(end = 14.dp)
                        .size(30.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            // Barra de pestañas "Para ti" y "Siguiendo"
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 95.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TabButton(
                    text = "Para ti",
                    isSelected = selectedTab == 0,
                    onClick = { selectedTab = 0 }
                )
                TabButton(
                    text = "Siguiendo",
                    isSelected = selectedTab == 1,
                    onClick = { selectedTab = 1 }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            // Feed de Tweets
            LazyColumn {
                items(tweets) { tweet ->
                    TweetCard(tweet)
                }
            }
        }
    }
}

// Barra de navegación inferior
@Composable
fun BottomNavigationBar(selectedItem: Int, onItemSelected: (Int) -> Unit) {
    BottomNavigation(
        backgroundColor = Color.White
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Home",
                    modifier = Modifier.size(20.dp)
                )
            },
            selected = selectedItem == 0,
            onClick = { onItemSelected(0) }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "Search",
                    modifier = Modifier.size(20.dp)
                )
            },
            selected = selectedItem == 1,
            onClick = { onItemSelected(1) }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.slash),
                    contentDescription = "Grok",
                    modifier = Modifier.size(20.dp)
                )
            },
            selected = selectedItem == 2,
            onClick = { onItemSelected(2) }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.users),
                    contentDescription = "Friends",
                    modifier = Modifier.size(20.dp)
                )
            },
            selected = selectedItem == 3,
            onClick = { onItemSelected(3) }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.notification),
                    contentDescription = "Notifications",
                    modifier = Modifier.size(20.dp)
                )
            },
            selected = selectedItem == 4,
            onClick = { onItemSelected(4) }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.mensaje),
                    contentDescription = "Messages",
                    modifier = Modifier.size(20.dp)
                )
            },
            selected = selectedItem == 5,
            onClick = { onItemSelected(5) }
        )
    }
}

@Composable
fun TabButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    textSize: TextUnit = 16.sp,
    textColor: Color = Color.Gray,
    indicatorColor: Color = Color(0xFF1DA1F2),
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = textSize,
            color = if (isSelected) indicatorColor else textColor,
            modifier = Modifier.clickable(onClick = onClick)
        )
        if (isSelected) {
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .height(2.dp)
                    .width(100.dp)
                    .background(indicatorColor)
            )
        }
    }
}

data class TweetData(
    val authorName: String,
    val username: String,
    val content: String,
    val profileImageResId: Int,
    val imageResId: Int? = null,
    val commentsCount: String = "",
    val retweetsCount: String = "",
    val likesCount: String = "",
    val statisticsCount: String = "",
    val savesCount: String = "",
    val sharesCount: String = ""
)

@Composable
fun TweetCard(tweet: TweetData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 1.dp),
        elevation = 1.dp
    ) {
        Column(modifier = Modifier.padding(7.dp)) {
            // Encabezado del Tweet
            Row(
                verticalAlignment = Alignment.Top
            ) {
                // Imagen de perfil
                Image(
                    painter = painterResource(id = tweet.profileImageResId),
                    contentDescription = "Author",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(5.dp))

                // Columna con nombre del autor, nombre de usuario y contenido del tweet
                Column {
                    // Nombre del autor y nombre de usuario en la misma línea
                    Row {
                        Text(
                            text = tweet.authorName,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = tweet.username,
                            color = Color.Gray
                        )
                    }

                    Spacer(modifier = Modifier.height(3.dp))

                    // Contenido del Tweet alineado con el nombre y usuario
                    Text(text = tweet.content)

                    // Imagen del Tweet (opcional)
                    tweet.imageResId?.let {
                        Spacer(modifier = Modifier.height(8.dp))

                        // Modificar la imagen para que sea cuadrada y más grande
                        Image(
                            painter = painterResource(id = it),
                            contentDescription = "Tweet Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .clip(RoundedCornerShape(8.dp))
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Íconos de interacción con conteo
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                InteractionIconWithCount(
                    iconResId = R.drawable.comentario,
                    count = tweet.commentsCount,
                    contentDescription = "Comment"
                )
                InteractionIconWithCount(
                    iconResId = R.drawable.rt,
                    count = tweet.retweetsCount,
                    contentDescription = "Retweet"
                )
                InteractionIconWithCount(
                    iconResId = R.drawable.heart,
                    count = tweet.likesCount,
                    contentDescription = "Like"
                )
                InteractionIconWithCount(
                    iconResId = R.drawable.stadistics,
                    count = tweet.statisticsCount,
                    contentDescription = "Statistics"
                )
                InteractionIconWithCount(
                    iconResId = R.drawable.save,
                    count = tweet.savesCount,
                    contentDescription = "Save"
                )
                InteractionIconWithCount(
                    iconResId = R.drawable.share,
                    count = tweet.sharesCount,
                    contentDescription = "Share"
                )
            }
        }
    }
}

@Composable
fun InteractionIconWithCount(iconResId: Int, count: String, contentDescription: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        IconButton(onClick = { /* Handle Click */ }) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = contentDescription,
                modifier = Modifier.size(12.dp)
            )
        }
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = count,
            color = Color.Gray,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Right,
            modifier = Modifier.padding(vertical = 2.dp)
        )
    }
}
