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

        // Botón de Redactar Tweet
        FloatingActionButton(
            onClick = { /* Handle Click */ },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.share), // Reemplaza con el ícono de redactar tweet
                contentDescription = "Compose Tweet",
                tint = Color.White
            )
        }

        // Feed de Tweets
        LazyColumn {
            items(10) { index -> // Puedes cambiar el número 10 por la cantidad real de tweets
                TweetCard()
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

@Composable
fun TweetCard() {
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
                    modifier = Modifier.size(40.dp).clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(text = "Author Name", fontWeight = FontWeight.Bold)
                    Text(text = "@username", color = Color.Gray)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "This is the content of the tweet. It can include text, hashtags, mentions, and more.")
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = painterResource(id = R.drawable.t1), // Reemplaza con imagen adjunta al tweet
                contentDescription = "Tweet Image",
                modifier = Modifier.fillMaxWidth().height(200.dp).clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { /* Handle Comment */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.comentario), // Reemplaza con ícono de comentario
                        contentDescription = "Comment"
                    )
                }
                IconButton(onClick = { /* Handle Retweet */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.retweet), // Reemplaza con ícono de retweet
                        contentDescription = "Retweet"
                    )
                }
                IconButton(onClick = { /* Handle Like */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.heart), // Reemplaza con ícono de me gusta
                        contentDescription = "Like"
                    )
                }
                IconButton(onClick = { /* Handle Share */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.share), // Reemplaza con ícono de compartir
                        contentDescription = "Share"
                    )
                }
            }
        }
    }
}
