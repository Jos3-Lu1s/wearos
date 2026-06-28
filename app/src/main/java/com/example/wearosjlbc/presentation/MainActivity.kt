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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.style.TextOverflow
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

fun evaluateExpression(expr: String): String {
    try {
        if (expr.isEmpty()) return "0"
        
        // Remove trailing operators for friendly auto-completion
        var clean = expr.trim()
        while (clean.isNotEmpty() && (clean.last() in listOf('+', '-', '×', '÷') || clean.last().toString() in listOf("+", "-", "*", "/"))) {
            clean = clean.dropLast(1)
        }
        if (clean.isEmpty()) return "0"

        // Replace percentage with /100* if followed by digit or parenthesis or √, else /100
        clean = clean.replace(Regex("%(?=[0-9.(√])"), "/100*")
        clean = clean.replace("%", "/100")
        
        // Helper to evaluate expression without parentheses
        fun evalSimple(simpleExpr: String): Double {
            val cleanSimple = simpleExpr.replace("×", "*").replace("÷", "/")
            val tokens = mutableListOf<String>()
            var currentNumber = StringBuilder()
            
            var i = 0
            while (i < cleanSimple.length) {
                val char = cleanSimple[i]
                if (char.isDigit() || char == '.') {
                    currentNumber.append(char)
                } else if (char == '+' || char == '-' || char == '*' || char == '/') {
                    if (currentNumber.isNotEmpty()) {
                        tokens.add(currentNumber.toString())
                        currentNumber = StringBuilder()
                    } else if (char == '-' && (tokens.isEmpty() || tokens.last() in listOf("+", "-", "*", "/"))) {
                        currentNumber.append(char)
                        i++
                        continue
                    }
                    tokens.add(char.toString())
                }
                i++
            }
            if (currentNumber.isNotEmpty()) {
                tokens.add(currentNumber.toString())
            }
            
            if (tokens.isEmpty()) return 0.0
            
            // Precedence: * and /
            val midTokens = mutableListOf<String>()
            var idx = 0
            while (idx < tokens.size) {
                val token = tokens[idx]
                if (token == "*" || token == "/") {
                    if (midTokens.isEmpty() || idx + 1 >= tokens.size) throw Exception("Error")
                    val left = midTokens.removeAt(midTokens.size - 1).toDoubleOrNull() ?: throw Exception("Error")
                    val right = tokens[idx + 1].toDoubleOrNull() ?: throw Exception("Error")
                    val res = if (token == "*") left * right else {
                        if (right == 0.0) throw Exception("Error")
                        left / right
                    }
                    midTokens.add(res.toString())
                    idx += 2
                } else {
                    midTokens.add(token)
                    idx++
                }
            }
            
            // Precedence: + and -
            if (midTokens.isEmpty()) return 0.0
            var finalResult = midTokens[0].toDoubleOrNull() ?: throw Exception("Error")
            var j = 1
            while (j < midTokens.size) {
                val op = midTokens[j]
                if (j + 1 >= midTokens.size) throw Exception("Error")
                val nextVal = midTokens[j + 1].toDoubleOrNull() ?: throw Exception("Error")
                finalResult = if (op == "+") {
                    finalResult + nextVal
                } else if (op == "-") {
                    finalResult - nextVal
                } else {
                    throw Exception("Error")
                }
                j += 2
            }
            return finalResult
        }
        
        // Handle square root first if it is of the form √num or √(expr)
        // Resolve simple √num (like √9 -> 3)
        var tempExpr = clean
        while (tempExpr.contains("√")) {
            val match = Regex("√([0-9.]+)").find(tempExpr)
            if (match != null) {
                val valStr = match.groupValues[1]
                val value = valStr.toDoubleOrNull() ?: return "Error"
                if (value < 0.0) return "Error"
                val sqrtVal = Math.sqrt(value)
                tempExpr = tempExpr.replaceFirst("√$valStr", sqrtVal.toString())
            } else {
                break
            }
        }
        
        // Recursively solve innermost parentheses
        while (tempExpr.contains("(")) {
            val openIdx = tempExpr.lastIndexOf("(")
            val closeIdx = tempExpr.indexOf(")", openIdx)
            if (closeIdx == -1) {
                // Auto-close missing parenthesis
                tempExpr += ")"
                continue
            }
            val subExpr = tempExpr.substring(openIdx + 1, closeIdx)
            val subResult = evalSimple(subExpr)
            
            // Check if there is a '√' right before this parenthesis, e.g. "√(subExpr)"
            if (openIdx > 0 && tempExpr[openIdx - 1] == '√') {
                if (subResult < 0.0) return "Error"
                val sqrtVal = Math.sqrt(subResult)
                tempExpr = tempExpr.substring(0, openIdx - 1) + sqrtVal.toString() + tempExpr.substring(closeIdx + 1)
            } else {
                tempExpr = tempExpr.substring(0, openIdx) + subResult.toString() + tempExpr.substring(closeIdx + 1)
            }
        }
        
        // Clean remaining square roots (after parenthesis evaluation)
        while (tempExpr.contains("√")) {
            val match = Regex("√([0-9.]+)").find(tempExpr)
            if (match != null) {
                val valStr = match.groupValues[1]
                val value = valStr.toDoubleOrNull() ?: return "Error"
                if (value < 0.0) return "Error"
                val sqrtVal = Math.sqrt(value)
                tempExpr = tempExpr.replaceFirst("√$valStr", sqrtVal.toString())
            } else {
                return "Error"
            }
        }
        
        val finalVal = evalSimple(tempExpr)
        return if (finalVal % 1.0 == 0.0) {
            finalVal.toLong().toString()
        } else {
            val formatted = String.format(java.util.Locale.US, "%.8f", finalVal)
            formatted.replace(Regex("0+$"), "").replace(Regex("\\.$"), "")
        }
    } catch (e: Exception) {
        return "Error"
    }
}

@Composable
fun CalculatorScreen() {
    var expression by remember { mutableStateOf("") }
    var isShowingResult by remember { mutableStateOf(false) }
    var isPage2 by remember { mutableStateOf(false) }

    fun handleButtonClick(value: String) {
        when (value) {
            "C" -> {
                expression = ""
                isShowingResult = false
            }
            "1/2", "2/2" -> {
                isPage2 = !isPage2
            }
            "()" -> {
                if (isShowingResult) {
                    expression = ""
                    isShowingResult = false
                }
                val openCount = expression.count { it == '(' }
                val closeCount = expression.count { it == ')' }
                val lastChar = expression.lastOrNull()
                if (openCount > closeCount && lastChar != null && (lastChar.isDigit() || lastChar == ')')) {
                    expression += ")"
                } else {
                    expression += "("
                }
            }
            "%" -> {
                if (isShowingResult) {
                    isShowingResult = false
                }
                if (expression.isNotEmpty() && expression.last().isDigit()) {
                    expression += "%"
                }
            }
            "+/-" -> {
                if (isShowingResult) {
                    isShowingResult = false
                }
                if (expression.isNotEmpty()) {
                    val match = Regex("([+\\-×÷]?)([0-9.]+)$").find(expression)
                    if (match != null) {
                        val (op, num) = match.destructured
                        val prefix = expression.substring(0, match.range.first)
                        if (op == "-") {
                            val prefixLast = prefix.lastOrNull()
                            if (prefixLast in listOf('+', '-', '×', '÷') || prefix.isEmpty()) {
                                expression = prefix + num
                            } else {
                                expression = prefix + "+-" + num
                            }
                        } else if (op == "+") {
                            expression = prefix + "-" + num
                        } else if (op in listOf("×", "÷")) {
                            expression = prefix + op + "-" + num
                        } else {
                            expression = "-" + num
                        }
                    }
                } else {
                    expression = "-"
                }
            }
            "√" -> {
                if (isShowingResult) {
                    expression = ""
                    isShowingResult = false
                }
                expression += "√"
            }
            "=" -> {
                if (expression.isNotEmpty()) {
                    expression = evaluateExpression(expression)
                    isShowingResult = true
                }
            }
            "backspace" -> {
                if (isShowingResult) {
                    expression = ""
                    isShowingResult = false
                } else if (expression.isNotEmpty()) {
                    expression = expression.dropLast(1)
                }
            }
            "+", "-", "×", "÷" -> {
                if (isShowingResult) {
                    isShowingResult = false
                }
                if (expression.isNotEmpty()) {
                    val lastChar = expression.last().toString()
                    if (lastChar in listOf("+", "-", "×", "÷")) {
                        expression = expression.dropLast(1) + value
                    } else {
                        expression += value
                    }
                } else if (value == "-") {
                    expression = "-"
                }
            }
            else -> { // Digits and decimal point
                if (isShowingResult) {
                    expression = ""
                    isShowingResult = false
                }
                if (value == ".") {
                    val lastNumberToken = expression.split(Regex("[+\\-×÷()√%]")).lastOrNull() ?: ""
                    if (!lastNumberToken.contains(".")) {
                        expression += if (lastNumberToken.isEmpty()) "0." else "."
                    }
                } else {
                    expression += value
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF000000))
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
                text = buildAnnotatedString {
                    if (isShowingResult) {
                        withStyle(style = SpanStyle(color = Color(0xFF7AE142))) {
                            append(expression.ifEmpty { "0" })
                        }
                    } else {
                        val displayExpr = expression.ifEmpty { "0" }
                        for (char in displayExpr) {
                            val color = if (char in listOf('+', '-', '×', '÷', '(', ')', '%', '√')) {
                                Color(0xFF7AE142)
                            } else {
                                Color.White
                            }
                            withStyle(style = SpanStyle(color = color)) {
                                append(char)
                            }
                        }
                    }
                },
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.End,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
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
                    .clickable { handleButtonClick("backspace") }
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        // --- 2. Línea Divisora ---
        Row(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .height(1.dp)
                .background(Color(0xFF333333))
        ) {}

        Spacer(modifier = Modifier.height(6.dp))

        // --- 3. Grid del Teclado ---
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                CalculatorButton("C", Color(0xFFE55541), Color.White, 36.dp, 24.dp) { handleButtonClick("C") }
                val pageLabel = if (isPage2) "2/2" else "1/2"
                CalculatorButton(pageLabel, Color(0xFF333333), Color(0xFF7AE142), 36.dp, 24.dp) { handleButtonClick(pageLabel) }
                val divOp = if (isPage2) "()" else "÷"
                CalculatorButton(divOp, Color(0xFF333333), Color(0xFF7AE142), 36.dp, 24.dp) { handleButtonClick(divOp) }
                val multOp = if (isPage2) "%" else "×"
                CalculatorButton(multOp, Color(0xFF333333), Color(0xFF7AE142), 36.dp, 24.dp) { handleButtonClick(multOp) }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                CalculatorButton("7", Color(0xFF181818), Color.White, 36.dp, 24.dp) { handleButtonClick("7") }
                CalculatorButton("8", Color(0xFF181818), Color.White, 36.dp, 24.dp) { handleButtonClick("8") }
                CalculatorButton("9", Color(0xFF181818), Color.White, 36.dp, 24.dp) { handleButtonClick("9") }
                val minusOp = if (isPage2) "+/-" else "-"
                CalculatorButton(minusOp, Color(0xFF333333), Color(0xFF7AE142), 36.dp, 24.dp) { handleButtonClick(minusOp) }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                CalculatorButton("4", Color(0xFF181818), Color.White, 36.dp, 24.dp) { handleButtonClick("4") }
                CalculatorButton("5", Color(0xFF181818), Color.White, 36.dp, 24.dp) { handleButtonClick("5") }
                CalculatorButton("6", Color(0xFF181818), Color.White, 36.dp, 24.dp) { handleButtonClick("6") }
                val plusOp = if (isPage2) "√" else "+"
                CalculatorButton(plusOp, Color(0xFF333333), Color(0xFF7AE142), 36.dp, 24.dp) { handleButtonClick(plusOp) }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                CalculatorButton("1", Color(0xFF181818), Color.White, 36.dp, 24.dp) { handleButtonClick("1") }
                CalculatorButton("2", Color(0xFF181818), Color.White, 36.dp, 24.dp) { handleButtonClick("2") }
                CalculatorButton("3", Color(0xFF181818), Color.White, 36.dp, 24.dp) { handleButtonClick("3") }
                CalculatorButton("=", Color(0xFF388E3C), Color.White, 36.dp, 24.dp) { handleButtonClick("=") }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Spacer(modifier = Modifier.width(36.dp))
                CalculatorButton("0", Color(0xFF181818), Color.White, 36.dp, 24.dp) { handleButtonClick("0") }
                CalculatorButton(".", Color(0xFF181818), Color.White, 36.dp, 24.dp) { handleButtonClick(".") }
                Spacer(modifier = Modifier.width(36.dp))
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
    height: Dp,
    onClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .size(width = width, height = height)
            .clip(RoundedCornerShape(percent = 50)) // Forma de píldora
            .background(backgroundColor)
            .clickable { onClick() }
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = if (text == "1/2" || text == "2/2" || text == "+/-") 13.sp else 18.sp,
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