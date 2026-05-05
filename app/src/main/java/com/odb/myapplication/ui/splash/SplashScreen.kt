package com.odb.myapplication.ui.splash

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onSplashComplete: () -> Unit,
    modifier: Modifier = Modifier
) {
    var startAnimation by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0.3f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = EaseOutBack
        ),
        label = "scale"
    )

    val alpha by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = FastOutSlowInEasing
        ),
        label = "alpha"
    )

    val textAlpha by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 800,
            delayMillis = 500,
            easing = FastOutSlowInEasing
        ),
        label = "textAlpha"
    )

    // Infinite transition for continuous animations
    val infiniteTransition = rememberInfiniteTransition(label = "infinite")
    
    // Subtle pulse animation for car icon
    val pulse by infiniteTransition.animateFloat(
        initialValue = 0.98f,
        targetValue = 1.02f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse"
    )

    // Wave animation for radiating signals (continuous pulsing)
    val waveProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),

            repeatMode = RepeatMode.Restart
        ),
        label = "waveProgress"
    )
    
    // Glow intensity animation for car icon
    val glowIntensity by infiniteTransition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glowIntensity"
    )


    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(2500L)
        onSplashComplete()
    }

    // Dark blue background with hexagonal pattern
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0F172A)),
        contentAlignment = Alignment.Center
    ) {
        // Hexagonal grid pattern
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            val hexSize = 40.dp.toPx()
            val hexWidth = hexSize * 1.732f // sqrt(3) * radius
            val hexHeight = hexSize * 2f
            
            val cols = (size.width / hexWidth).toInt() + 1
            val rows = (size.height / (hexHeight * 0.75f)).toInt() + 1
            
            val hexColor = Color(0xFF1E293B) // Slightly lighter dark blue for hex outlines
            
            for (row in 0..rows) {
                for (col in 0..cols) {
                    val x = col * hexWidth + if (row % 2 == 1) hexWidth / 2 else 0f
                    val y = row * hexHeight * 0.75f
                    
                    val path = Path().apply {
                        for (i in 0..5) {
                            val angle = PI / 3 * i
                            val px = x + hexSize * cos(angle).toFloat()
                            val py = y + hexSize * sin(angle).toFloat()
                            if (i == 0) {
                                moveTo(px, py)
                            } else {
                                lineTo(px, py)
                            }
                        }
                        close()
                    }
                    
                    drawPath(
                        path = path,
                        color = hexColor,
                        style = Stroke(width = 1.dp.toPx())
                    )
                }
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .fillMaxHeight()
        ) {
            Spacer(modifier = Modifier.weight(0.3f))
            
            // Car outline with radiating waves
            Box(
                modifier = Modifier
                    .size(300.dp, 250.dp)
                    .scale(scale * pulse)
                    .alpha(alpha),
                contentAlignment = Alignment.Center
            ) {
                Canvas(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val centerX = size.width / 2
                    val centerY = size.height / 2
                    val cyanColor = Color(0xFF00D9FF) // Bright cyan/light blue
                    
                    // Radiating signal waves on the left (three concentric curved lines)
                    for (i in 0..2) {
                        val baseRadius = 50.dp.toPx() + (i * 25.dp.toPx())
                        val animatedRadius = baseRadius + (waveProgress * 30.dp.toPx())
                        val waveAlpha = 0.7f - (i * 0.2f) - (waveProgress * 0.3f)
                        val leftCenterX = centerX - 110.dp.toPx()
                        val leftCenterY = centerY
                        
                        // Draw curved wave (like Wi-Fi signal)
                        drawArc(
                            color = cyanColor.copy(alpha = waveAlpha.coerceIn(0f, 1f)),
                            startAngle = -50f,
                            sweepAngle = 100f,
                            useCenter = false,
                            topLeft = Offset(leftCenterX - animatedRadius, leftCenterY - animatedRadius),
                            size = Size(animatedRadius * 2, animatedRadius * 2),
                            style = Stroke(width = 2.5.dp.toPx())
                        )
                    }
                    
                    // Radiating signal waves on the right (three concentric curved lines)
                    for (i in 0..2) {
                        val baseRadius = 50.dp.toPx() + (i * 25.dp.toPx())
                        val animatedRadius = baseRadius + (waveProgress * 30.dp.toPx())
                        val waveAlpha = 0.7f - (i * 0.2f) - (waveProgress * 0.3f)
                        val rightCenterX = centerX + 110.dp.toPx()
                        val rightCenterY = centerY
                        
                        // Draw curved wave (like Wi-Fi signal)
                        drawArc(
                            color = cyanColor.copy(alpha = waveAlpha.coerceIn(0f, 1f)),
                            startAngle = 130f,
                            sweepAngle = 100f,
                            useCenter = false,
                            topLeft = Offset(rightCenterX - animatedRadius, rightCenterY - animatedRadius),
                            size = Size(animatedRadius * 2, animatedRadius * 2),
                            style = Stroke(width = 2.5.dp.toPx())
                        )
                    }
                    
                    // Car outline (front view - symmetric)
                    val carPath = Path().apply {
                        val carWidth = 140.dp.toPx()
                        val carHeight = 90.dp.toPx()
                        val carX = centerX - carWidth / 2
                        val carY = centerY - carHeight / 2
                        
                        // Car body outline (symmetric front view)
                        moveTo(carX + 25.dp.toPx(), carY + carHeight) // Bottom left
                        lineTo(carX + 25.dp.toPx(), carY + 60.dp.toPx()) // Left side
                        lineTo(carX + 35.dp.toPx(), carY + 40.dp.toPx()) // Left front corner
                        lineTo(carX + 50.dp.toPx(), carY + 20.dp.toPx()) // Left windshield corner
                        lineTo(carX + 70.dp.toPx(), carY + 15.dp.toPx()) // Front top center
                        lineTo(carX + 90.dp.toPx(), carY + 20.dp.toPx()) // Right windshield corner
                        lineTo(carX + 105.dp.toPx(), carY + 40.dp.toPx()) // Right front corner
                        lineTo(carX + 115.dp.toPx(), carY + 60.dp.toPx()) // Right side
                        lineTo(carX + 115.dp.toPx(), carY + carHeight) // Bottom right
                        close()
                        
                        // Windshield (symmetric)
                        moveTo(carX + 40.dp.toPx(), carY + 25.dp.toPx())
                        lineTo(carX + 50.dp.toPx(), carY + 20.dp.toPx())
                        lineTo(carX + 70.dp.toPx(), carY + 18.dp.toPx())
                        lineTo(carX + 90.dp.toPx(), carY + 20.dp.toPx())
                        lineTo(carX + 100.dp.toPx(), carY + 25.dp.toPx())
                        lineTo(carX + 95.dp.toPx(), carY + 40.dp.toPx())
                        lineTo(carX + 70.dp.toPx(), carY + 42.dp.toPx())
                        lineTo(carX + 45.dp.toPx(), carY + 40.dp.toPx())
                        close()
                        
                        // Grille (symmetric front grille)
                        moveTo(carX + 50.dp.toPx(), carY + 50.dp.toPx())
                        lineTo(carX + 70.dp.toPx(), carY + 48.dp.toPx())
                        lineTo(carX + 90.dp.toPx(), carY + 50.dp.toPx())
                        lineTo(carX + 90.dp.toPx(), carY + 65.dp.toPx())
                        lineTo(carX + 70.dp.toPx(), carY + 67.dp.toPx())
                        lineTo(carX + 50.dp.toPx(), carY + 65.dp.toPx())
                        close()
                        
                        // Grille vertical lines
                        for (i in 1..3) {
                            val xOffset = carX + 50.dp.toPx() + (i * 10.dp.toPx())
                            moveTo(xOffset, carY + 50.dp.toPx())
                            lineTo(xOffset, carY + 65.dp.toPx())
                        }
                        
                        // Left headlight
                        addOval(
                            Rect(
                                left = carX + 20.dp.toPx(),
                                top = carY + 45.dp.toPx(),
                                right = carX + 35.dp.toPx(),
                                bottom = carY + 60.dp.toPx()
                            )
                        )
                        
                        // Right headlight
                        addOval(
                            Rect(
                                left = carX + 105.dp.toPx(),
                                top = carY + 45.dp.toPx(),
                                right = carX + 120.dp.toPx(),
                                bottom = carY + 60.dp.toPx()
                            )
                        )
                        
                        // Left wheel (front view - circular)
                        addOval(
                            Rect(
                                left = carX + 20.dp.toPx(),
                                top = carY + 75.dp.toPx(),
                                right = carX + 40.dp.toPx(),
                                bottom = carY + carHeight - 5.dp.toPx()
                            )
                        )
                        
                        // Right wheel (front view - circular)
                        addOval(
                            Rect(
                                left = carX + 100.dp.toPx(),
                                top = carY + 75.dp.toPx(),
                                right = carX + 120.dp.toPx(),
                                bottom = carY + carHeight - 5.dp.toPx()
                            )
                        )
                    }
                    
                    // Draw car outline with animated glowing effect
                    // Outer glow (animated)
                    drawPath(
                        path = carPath,
                        color = cyanColor.copy(alpha = glowIntensity),
                        style = Stroke(width = 10.dp.toPx())
                    )
                    
                    // Middle glow
                    drawPath(
                        path = carPath,
                        color = cyanColor.copy(alpha = 0.4f),
                        style = Stroke(width = 6.dp.toPx())
                    )
                    
                    // Main car outline
                    drawPath(
                        path = carPath,
                        color = cyanColor,
                        style = Stroke(width = 3.dp.toPx())
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Primary Text: "OBD Scanner"
            Text(
                text = "OBD Scanner",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.alpha(textAlpha)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Secondary Text: "Professional Vehicle Diagnostics"
            Text(
                text = "Professional Vehicle Diagnostics",
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFFCBD5E1), // Light gray
                textAlign = TextAlign.Center,
                modifier = Modifier.alpha(textAlpha)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Tertiary Text: "Mistri ji Technology"
            Text(
                text = "Mistri ji Technology",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF00D9FF), // Vibrant cyan matching car icon
                textAlign = TextAlign.Center,
                modifier = Modifier.alpha(textAlpha)
            )
            
            Spacer(modifier = Modifier.weight(0.4f))
        }
    }
}
