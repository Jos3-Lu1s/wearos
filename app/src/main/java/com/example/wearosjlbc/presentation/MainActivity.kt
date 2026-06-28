package com.example.wearosjlbc.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material3.Text
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
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController = navController) }
        composable("app_launcher") { AppLauncherScreen(navController = navController) }
        composable("calculator") { CalculatorScreen() }
        composable("app") { DummyScreen("App Screen") { navController.popBackStack() } }
    }
}

// -------------------------------------------------------------
// PANTALLAS EXISTENTES (HOME & LAUNCHER)
// -------------------------------------------------------------

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
                colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(Color(0xFF0056D2)),
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
            colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(Color(0xFF0056D2)),
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
            colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(Color(0xFF0056D2)),
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
            colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(Color(0xFF0056D2)),
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
                .clickable { navController.popBackStack() }
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppIcon(drawableId = R.drawable.icongoogle) { navController.navigate("app") }
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
            AppIcon(drawableId = R.drawable.iconscalculadora) { navController.navigate("calculator") }
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

@Composable
fun AppIcon(drawableId: Int, onClick: () -> Unit) {
    Image(
        painter = painterResource(id = drawableId),
        contentDescription = "App Icon",
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
            .background(Color.White)
            .clickable { onClick() }
            .padding(10.dp)
    )
}

@Composable
fun DummyScreen(title: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = title, color = Color.White, fontSize = 20.sp)
    }
}

// -------------------------------------------------------------
// PANTALLA DE CALCULADORA (DISEÑO SAMSUNG GALAXY WATCH 7)
// -------------------------------------------------------------

@Composable
fun CalculatorScreen() {
    // Paleta de colores exacta extraída de la UI de Samsung
    val bgDark = Color(0xFF000000)
    val btnRed = Color(0xFFE55541)       // Rojo Samsung
    val btnDarkGray = Color(0xFF333333)  // Gris oscuro (Operadores)
    val btnNumGray = Color(0xFF181818)   // Casi negro (Números)
    val textGreen = Color(0xFF7AE142)    // Verde brillante
    val btnGreen = Color(0xFF388E3C)     // Verde oscuro (=)

    // Ajustamos ancho y alto para hacer botones "Píldora/Óvalo"
    // Esto evita que se corten arriba y abajo, dejando espacio para la pantalla
    val btnWidth = 36.dp
    val btnHeight = 24.dp
    val btnSpacing = 4.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgDark)
            .padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        // --- 1. Área Superior (Historial, Resultado, Borrar) ---
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 22.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.history),
                contentDescription = "Historial",
                colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(Color(0xFF666666)),
                modifier = Modifier.size(18.dp)
            )

            Text(
                text = "0",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.backspace),
                contentDescription = "Eliminar",
                colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(Color(0xFF7AE142)),
                modifier = Modifier
                    .size(24.dp)
                    .clickable { /* Acción borrar */ }
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        // --- 2. Línea Divisora ---
        Box(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .height(1.dp)
                .background(Color(0xFF333333))
        )

        Spacer(modifier = Modifier.height(6.dp))

        // --- 3. Grid del Teclado ---
        Column(
            verticalArrangement = Arrangement.spacedBy(btnSpacing),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(btnSpacing)) {
                CalculatorButton("C", btnRed, Color.White, btnWidth, btnHeight)
                CalculatorButton("1/2", btnDarkGray, textGreen, btnWidth, btnHeight)
                CalculatorButton("÷", btnDarkGray, textGreen, btnWidth, btnHeight)
                CalculatorButton("×", btnDarkGray, textGreen, btnWidth, btnHeight)
            }
            Row(horizontalArrangement = Arrangement.spacedBy(btnSpacing)) {
                CalculatorButton("7", btnNumGray, Color.White, btnWidth, btnHeight)
                CalculatorButton("8", btnNumGray, Color.White, btnWidth, btnHeight)
                CalculatorButton("9", btnNumGray, Color.White, btnWidth, btnHeight)
                CalculatorButton("-", btnDarkGray, textGreen, btnWidth, btnHeight)
            }
            Row(horizontalArrangement = Arrangement.spacedBy(btnSpacing)) {
                CalculatorButton("4", btnNumGray, Color.White, btnWidth, btnHeight)
                CalculatorButton("5", btnNumGray, Color.White, btnWidth, btnHeight)
                CalculatorButton("6", btnNumGray, Color.White, btnWidth, btnHeight)
                CalculatorButton("+", btnDarkGray, textGreen, btnWidth, btnHeight)
            }
            Row(horizontalArrangement = Arrangement.spacedBy(btnSpacing)) {
                CalculatorButton("1", btnNumGray, Color.White, btnWidth, btnHeight)
                CalculatorButton("2", btnNumGray, Color.White, btnWidth, btnHeight)
                CalculatorButton("3", btnNumGray, Color.White, btnWidth, btnHeight)
                CalculatorButton("=", btnGreen, Color.White, btnWidth, btnHeight)
            }
            Row(horizontalArrangement = Arrangement.spacedBy(btnSpacing)) {
                Spacer(modifier = Modifier.width(btnWidth))
                CalculatorButton("0", btnNumGray, Color.White, btnWidth, btnHeight)
                CalculatorButton(".", btnNumGray, Color.White, btnWidth, btnHeight)
                Spacer(modifier = Modifier.width(btnWidth))
            }
        }
    }
}

@Composable
fun CalculatorButton(
    text: String,
    backgroundColor: Color,
    textColor: Color,
    width: Dp,
    height: Dp
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(width = width, height = height)
            .clip(RoundedCornerShape(percent = 50)) // Forma de píldora
            .background(backgroundColor)
            .clickable { /* Acción del número/operador */ }
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = if (text == "1/2") 13.sp else 18.sp,
            fontWeight = FontWeight.Medium
        )
    }
}



@WearPreviewDevices
@WearPreviewFontScales
@Composable
fun DefaultPreview() {
    WearAppNavigation()
}