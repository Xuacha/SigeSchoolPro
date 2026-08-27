package com.sigeschool.presentation.screens.splash

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sigeschool.core.theme.SigeSchoolTheme
// import com.sigeschool.ui.generated.resources.Res
// import com.sigeschool.ui.generated.resources.ic_launcher_foreground
// import org.jetbrains.compose.resources.painterResource
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onSplashFinished: () -> Unit) {
    var startAnimation by remember { mutableStateOf(false) }
    val scale = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0.8f,
        animationSpec = tween(800, easing = FastOutSlowInEasing)
    )
    val alpha = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(800)
    )

    LaunchedEffect(Unit) {
        startAnimation = true
        delay(2200)           // Duración total del splash
        onSplashFinished()
    }

    SigeSchoolTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Logo animado
                Box(modifier = Modifier.size(140.dp).scale(scale.value).alpha(alpha.value), contentAlignment = Alignment.Center) {
                    Text("LOGO", color = Color.White, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Título principal
                Text(
                    text = "SigeSchool",
                    color = Color.White,
                    fontSize = 42.sp,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = "Pro",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.alpha(alpha.value)
                )

                Spacer(modifier = Modifier.height(48.dp))

                // Texto secundario
                Text(
                    text = "Gestión Escolar Inteligente",
                    color = Color.White.copy(alpha = 0.85f),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .alpha(alpha.value)
                        .padding(horizontal = 32.dp)
                )
            }

            // Indicador de carga en la parte inferior
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 64.dp)
            ) {
                Text(
                    text = "Cargando...",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 14.sp
                )
            }
        }
    }
}
