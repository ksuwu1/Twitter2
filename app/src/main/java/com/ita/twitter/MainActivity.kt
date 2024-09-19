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
import androidx.compose.ui.text.style.TextAlign
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
            authorName = "Cerebros",
            username = "@Cerebros ∙ 6h",
            content = "De último momento: Senado aprueba que el 1 de octubre, cada seis años, sea día de descanso obligatorio por el cambio de Gobierno Federal.",
            profileImageResId = R.drawable.user1,
            imageResId = R.drawable.tw1, // Reemplaza con la imagen del primer tweet
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
            imageResId = R.drawable.tw2, // Reemplaza con la imagen del segundo tweet
            commentsCount = "5K",
            retweetsCount = "600",
            likesCount = "100K",
            statisticsCount = "1M",

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
                // Icono de Perfil con sangría hacia la izquierda
                Image(
                    painter = painterResource(id = R.drawable.ksuwu), // Reemplaza con tu foto de perfil
                    contentDescription = "Profile",
                    modifier = Modifier
                        .padding(start = 16.dp) // Agrega sangría hacia la izquierda
                        .size(30.dp)
                        .clip(CircleShape)
                )
                Image(
                    painter = painterResource(id = R.drawable.logox), // Reemplaza con el logo de Twitter
                    contentDescription = "Twitter Logo",
                    modifier = Modifier.size(16.dp)
                )
                // Icono de Configuración con sangría hacia la derecha
                Image(
                    painter = painterResource(id = R.drawable.settings), // Reemplaza con el ícono de configuración
                    contentDescription = "Settings",
                    modifier = Modifier
                        .padding(end = 14.dp) // Agrega sangría hacia la derecha
                        .size(30.dp)
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
                    modifier = Modifier.size(20.dp)
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
                    modifier = Modifier.size(20.dp)
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
                    modifier = Modifier.size(20.dp)
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
                    modifier = Modifier.size(20.dp)
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
                    modifier = Modifier.size(20.dp)
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
                    modifier = Modifier.size(20.dp)
                )
            },
            selected = selectedItem == 5,
            onClick = { onItemSelected(5) }
        )
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
            .padding(vertical = 5.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Encabezado del Tweet
            Row(
                verticalAlignment = Alignment.Top // Alineación superior para la imagen y el texto
            ) {
                // Imagen de perfil
                Image(
                    painter = painterResource(id = tweet.profileImageResId), // Imagen de perfil del autor
                    contentDescription = "Author",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))

                // Columna con nombre del autor, nombre de usuario y contenido del tweet
                Column {
                    // Nombre del autor y nombre de usuario en la misma línea
                    Row {
                        Text(
                            text = tweet.authorName,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black // Color del autor
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = tweet.username,
                            color = Color.Gray // Color del nombre de usuario
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
                                .aspectRatio(1f) // Relación de aspecto 1:1 (cuadrada)
                                .clip(RoundedCornerShape(8.dp)) // Esquinas redondeadas
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
        verticalAlignment = Alignment.CenterVertically, // Alineación vertical al centro
                horizontalArrangement = Arrangement.Center
    ) {
        IconButton(onClick = { /* Handle Click */ }) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = contentDescription,
                modifier = Modifier.size(12.dp) // Tamaño más pequeño para los íconos
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
