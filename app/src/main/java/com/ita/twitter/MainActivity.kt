package com.ita.twitter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
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

        // Barra Inferior (Bottom Navigation Bar)
        BottomAppBar(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.White
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                IconButton(onClick = { /* Handle Click */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.home), // Reemplaza con ícono de inicio
                        contentDescription = "Home"
                    )
                }
                IconButton(onClick = { /* Handle Click */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.search), // Reemplaza con ícono de búsqueda
                        contentDescription = "Search"
                    )
                }
                IconButton(onClick = { /* Handle Click */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.notification), // Reemplaza con ícono de notificaciones
                        contentDescription = "Notifications"
                    )
                }
                IconButton(onClick = { /* Handle Click */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.mensaje), // Reemplaza con ícono de mensajes
                        contentDescription = "Messages"
                    )
                }
                IconButton(onClick = { /* Handle Click */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.grok), // Reemplaza con ícono de marcadores
                        contentDescription = "Bookmarks"
                    )
                }
                IconButton(onClick = { /* Handle Click */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.users), // Reemplaza con ícono de perfil
                        contentDescription = "Profile"
                    )
                }
            }
        }
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
                        modifier = Modifier.size(10.dp) // Tamaño más pequeño
                    )
                }
                IconButton(onClick = { /* Handle Retweet */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.rt), // Reemplaza con ícono de retweet
                        contentDescription = "Retweet",
                        modifier = Modifier.size(10.dp) // Tamaño más pequeño
                    )
                }
                IconButton(onClick = { /* Handle Like */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.heart),
                        contentDescription = "Like",
                        modifier = Modifier.size(10.dp) // Tamaño más pequeño
                    )
                }
                IconButton(onClick = { /* Handle Retweet */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.stadistics),
                        contentDescription = "Statistic",
                        modifier = Modifier.size(10.dp) // Tamaño más pequeño
                    )
                }
                IconButton(onClick = { /* Handle Retweet */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.save),
                        contentDescription = "Save",
                        modifier = Modifier.size(10.dp) // Tamaño más pequeño
                    )
                }
                IconButton(onClick = { /* Handle Share */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.share),
                        contentDescription = "Share",
                        modifier = Modifier.size(10.dp) // Tamaño más pequeño
                    )
                }
            }
        }
    }
}