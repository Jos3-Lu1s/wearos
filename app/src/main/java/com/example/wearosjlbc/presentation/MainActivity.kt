package com.example.wearosjlbc.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.wear.compose.material3.Text
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import androidx.wear.compose.ui.tooling.preview.WearPreviewDevices
import androidx.wear.compose.ui.tooling.preview.WearPreviewFontScales
import com.example.wearosjlbc.R



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WearAppNavigation()
        }
    }
}

@Composable
fun WearAppNavigation() {
    val navController = rememberSwipeDismissableNavController()

    SwipeDismissableNavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController = navController) }
        composable("app_launcher") { AppLauncherScreen(navController = navController) }

        composable("lock") { DummyScreen("Lock Screen", navController) }
        composable("previous") { DummyScreen("Previous", navController) }
        composable("next") { DummyScreen("Next", navController) }
        composable("textmind") { DummyScreen("Text Mind", navController) }
        composable("calculator") { DummyScreen("Calculator", navController) }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFDCE4EF))
            .clickable { navController.navigate("app_launcher") },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "JUE 25", color = Color(0xFF0056D2), fontWeight = FontWeight.Bold, fontSize = 16.sp)

        Text(
            text = "02:08:59",
            color = Color(0xFF0056D2),
            fontWeight = FontWeight.Medium,
            fontSize = 42.sp,
            modifier = Modifier.offset(y = (-4).dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.offset(y = (-4).dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_shoe),
                contentDescription = "Pasos",
                colorFilter = ColorFilter.tint(Color(0xFF0056D2)),
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Pasos 4,786",
                color = Color(0xFF0056D2),
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp
            )
        }

        ActivityGraph()

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            HeartRateComplication()
            CompassComplication()
            WeatherComplication()
        }
    }
}

@Composable
fun ActivityGraph() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(0.7f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_activity_graph),
            contentDescription = "Gráfica de actividad",
            modifier = Modifier
                .fillMaxWidth()
                .height(35.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("12AM", color = Color(0xFF0056D2), fontSize = 10.sp, fontWeight = FontWeight.Medium)
            Text("6", color = Color(0xFF0056D2), fontSize = 10.sp, fontWeight = FontWeight.Medium)
            Text("12PM", color = Color(0xFF0056D2), fontSize = 10.sp, fontWeight = FontWeight.Medium)
            Text("6", color = Color(0xFF0056D2), fontSize = 10.sp, fontWeight = FontWeight.Medium)
            Text("(h)", color = Color(0xFF0056D2), fontSize = 10.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun HeartRateComplication() {
    Column(
        modifier = Modifier
            .size(54.dp)
            .clip(CircleShape)
            .background(Color(0xFFC2D8ED)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_heart),
            contentDescription = "Heart Rate",
            colorFilter = ColorFilter.tint(Color(0xFF0056D2)),
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = "102",
            color = Color(0xFF0056D2),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun CompassComplication() {
    Column(
        modifier = Modifier
            .size(56.dp)
            .clip(CircleShape)
            .background(Color(0xFFC2D8ED)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_compass_with_ticks),
            contentDescription = "Compass",
            colorFilter = ColorFilter.tint(Color(0xFF0056D2)),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = "334°",
            color = Color(0xFF0056D2),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun WeatherComplication() {
    Column(
        modifier = Modifier
            .size(54.dp)
            .clip(CircleShape)
            .background(Color(0xFFC2D8ED)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_weather),
            contentDescription = "Weather",
            colorFilter = ColorFilter.tint(Color(0xFF0056D2)),
            modifier = Modifier.size(28.dp)
        )
    }
}

@Composable
fun AppLauncherScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1E1E1E))
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        Image(
            painter = painterResource(id = R.drawable.radio),
            contentDescription = "Indicador superior",
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppIcon(drawableId = R.drawable.icongoogle) { navController.navigate("textmind") }
            AppIcon(drawableId = R.drawable.iconwhatsapp) { /* Acción vacía */ }
            AppIcon(drawableId = R.drawable.iconsgoogleplay) { /* Acción vacía */ }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppIcon(drawableId = R.drawable.iconsgooglemaps) { /* Acción vacía */ }
            AppIcon(drawableId = R.drawable.iconsgaleria) { /* Acción vacía */ }
            AppIcon(drawableId = R.drawable.iconsmusica) { /* Acción vacía */ }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppIcon(drawableId = R.drawable.iconsgemini) { /* Acción vacía */ }
            AppIcon(drawableId = R.drawable.iconscontactos) { /* Acción vacía */ }
            AppIcon(drawableId = R.drawable.iconsbrujula) { /* Acción vacía */ }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppIcon(drawableId = R.drawable.iconscalendario) { /* Acción vacía */ }
            AppIcon(drawableId = R.drawable.iconsajustes) { /* Acción vacía */ }
            AppIcon(drawableId = R.drawable.iconscalculadora) { /* Acción vacía */ }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppIcon(drawableId = R.drawable.iconsnublado) { /* Acción vacía */ }
            AppIcon(drawableId = R.drawable.icons8mensajes) { /* Acción vacía */ }
            AppIcon(drawableId = R.drawable.iconsllamada) { /* Acción vacía */ }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

// -------------------------------------------------------------
// AQUÍ ESTÁ LA MAGIA DEL FONDO BLANCO
// -------------------------------------------------------------
@Composable
fun AppIcon(drawableId: Int, onClick: () -> Unit) {
    Image(
        painter = painterResource(id = drawableId),
        contentDescription = "App Icon",
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
            .background(Color.White) // 1. Agregamos el fondo blanco
            .clickable { onClick() }
            .padding(10.dp) // 2. Agregamos padding para que la imagen quede dentro de la esfera
    )
}

@Composable
fun DummyScreen(title: String, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = title, color = Color.White, fontSize = 20.sp)
    }
}

@WearPreviewDevices
@WearPreviewFontScales
@Composable
fun DefaultPreview() {
    WearAppNavigation()
}