package com.ita.twitter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TwitterMainScreen()
        }
    }
}

@Composable
fun TwitterMainScreen() {
    // Lista de Tweets
    val tweets = listOf(
        TweetData(
            authorName = "Mahir",
            username = "@ScrewderiaF1",
            content = "JAJAJAJAJAJA.",
            imageResId = R.drawable.t1 // Reemplaza con la imagen del primer tweet
        ),
        TweetData(
            authorName = "En Júpiter",
            username = "@En_jupiter",
            content = "#Yo #meidentifico    ",
            imageResId = R.drawable.t2 // Reemplaza con la imagen del segundo tweet
        )
    )

    var selectedItem by remember { mutableStateOf(0) } // Para manejar la selección de la barra de navegación

    Scaffold(
        bottomBar = {
            BottomNavigationBar(selectedItem = selectedItem, onItemSelected = { selectedItem = it })
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
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ksuwu), // Reemplaza con tu foto de perfil
                    contentDescription = "Profile",
                    modifier = Modifier.size(40.dp).clip(CircleShape)
                )
                Image(
                    painter = painterResource(id = R.drawable.logox), // Reemplaza con el logo de Twitter
                    contentDescription = "Twitter Logo",
                    modifier = Modifier.size(40.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.settings), // Reemplaza con el ícono de configuración
                    contentDescription = "Settings",
                    modifier = Modifier.size(40.dp)
                )
            }

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
                    painter = painterResource(id = R.drawable.home), // Ícono de inicio
                    contentDescription = "Home",
                    modifier = Modifier.size(15.dp)
                )
            },
            selected = selectedItem == 0,
            onClick = { onItemSelected(0) }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.search), // Ícono de búsqueda
                    contentDescription = "Search",
                    modifier = Modifier.size(15.dp)
                )
            },
            selected = selectedItem == 1,
            onClick = { onItemSelected(1) }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.slash), // Ícono de marcadores (Grok)
                    contentDescription = "Grok",
                    modifier = Modifier.size(15.dp)
                )
            },
            selected = selectedItem == 2,
            onClick = { onItemSelected(2) }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.users), // Ícono de tweets de amigos
                    contentDescription = "Friends",
                    modifier = Modifier.size(15.dp)
                )
            },
            selected = selectedItem == 3,
            onClick = { onItemSelected(3) }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.notification), // Ícono de notificaciones
                    contentDescription = "Notifications",
                    modifier = Modifier.size(15.dp)
                )
            },
            selected = selectedItem == 4,
            onClick = { onItemSelected(4) }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.mensaje), // Ícono de mensajes
                    contentDescription = "Messages",
                    modifier = Modifier.size(15.dp)
                )
            },
            selected = selectedItem == 5,
            onClick = { onItemSelected(5) }
        )
    }
}

// Datos del tweet
data class TweetData(
    val authorName: String,
    val username: String,
    val content: String,
    val imageResId: Int?
)

@Composable
fun TweetCard(tweet: TweetData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Encabezado del Tweet
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.us1), // Reemplaza con la imagen de perfil del autor
                    contentDescription = "Author",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(text = tweet.authorName, fontWeight = FontWeight.Bold)
                    Text(text = tweet.username, color = Color.Gray)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Contenido del Tweet
            Text(text = tweet.content)
            Spacer(modifier = Modifier.height(8.dp))

            // Imagen del Tweet (opcional)
            tweet.imageResId?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = "Tweet Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Íconos de interacción (Comentario, Retweet, Me gusta, Compartir)
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* Handle Comment */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.comentario), // Reemplaza con ícono de comentario
                        contentDescription = "Comment",
                        modifier = Modifier.size(10.dp)
                    )
                }
                IconButton(onClick = { /* Handle Retweet */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.rt), // Reemplaza con ícono de retweet
                        contentDescription = "Retweet",
                        modifier = Modifier.size(10.dp)
                    )
                }
                IconButton(onClick = { /* Handle Like */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.heart),
                        contentDescription = "Like",
                        modifier = Modifier.size(10.dp)
                    )
                }
                IconButton(onClick = { /* Handle Like */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.save),
                        contentDescription = "Save",
                        modifier = Modifier.size(10.dp)
                    )
                }
                IconButton(onClick = { /* Handle Share */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.share),
                        contentDescription = "Share",
                        modifier = Modifier.size(10.dp)
                    )
                }
            }
        }
    }
}
