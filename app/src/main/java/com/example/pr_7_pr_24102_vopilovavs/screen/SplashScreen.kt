package com.example.pr_7_pr_24102_vopilovavs.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

private val GaamePink = Color(0xFFFF6480)
private val GaamePinkDark = Color(0xFFF22E63)
private val White = Color(0xFFFFFFFF)

fun Modifier.cssGradient(angleDeg: Float, colors: List<Color>): Modifier = drawBehind {
    val rad = Math.toRadians(angleDeg.toDouble())
    val dx = sin(rad).toFloat()
    val dy = -cos(rad).toFloat()
    val half = (abs(size.width * dx) + abs(size.height * dy)) / 2f
    val center = Offset(size.width / 2f, size.height / 2f)
    drawRect(
        brush = Brush.linearGradient(
            colors = colors,
            start = Offset(center.x - dx * half, center.y - dy * half),
            end = Offset(center.x + dx * half, center.y + dy * half)
        )
    )
}

@Composable
fun SplashScreen(
    onFinished: () -> Unit = {},
    durationMillis: Long = 1600L
) {

    val scale = LocalConfiguration.current.screenWidthDp / 375f

    LaunchedEffect(Unit) {
        delay(durationMillis)
        onFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .cssGradient(angleDeg = 135f, colors = listOf(GaamePink, GaamePinkDark))
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            GaameLogo(
                markWidth = (56f * scale).dp,
                modifier = Modifier.offset(y = (-70f * scale).dp)
            )
        }

        SplashDoodles(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
        )
    }
}

@Composable
fun GaameLogo(
    modifier: Modifier = Modifier,
    markWidth: Dp = 56.dp,
    showTagline: Boolean = true
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LogoMark(width = markWidth)
        Spacer(Modifier.height(14.dp))
        Text(
            text = "GAAMETIIME",
            color = White,
            fontSize = (markWidth.value * 0.43f).sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.5.sp,
            textAlign = TextAlign.Center
        )
        if (showTagline) {
            Spacer(Modifier.height(4.dp))
            Text(
                text = "CONNECT WITH YOUR REALITY",
                color = White,
                fontSize = (markWidth.value * 0.16f).sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 1.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun LogoMark(width: Dp) {
    Canvas(modifier = Modifier.width(width).height(width * (64f / 110f))) {
        val k = size.width / 110f
        val strokeW = 3f * k

        drawRoundRect(
            color = White,
            topLeft = Offset(1.5f * k, 6f * k),
            size = Size(45f * k, 44f * k),
            cornerRadius = CornerRadius(10f * k, 10f * k),
            style = Stroke(width = strokeW)
        )
        drawLine(White, Offset(24f * k, 18f * k), Offset(24f * k, 36f * k), strokeWidth = strokeW, cap = StrokeCap.Round)
        drawLine(White, Offset(15f * k, 27f * k), Offset(33f * k, 27f * k), strokeWidth = strokeW, cap = StrokeCap.Round)

        val bubble = Path().apply {
            moveTo(42f * k, 22f * k)
            quadraticTo(42f * k, 14f * k, 52f * k, 14f * k)
            lineTo(100f * k, 14f * k)
            quadraticTo(108f * k, 14f * k, 108f * k, 22f * k)
            lineTo(108f * k, 40f * k)
            quadraticTo(108f * k, 48f * k, 100f * k, 48f * k)
            lineTo(58f * k, 48f * k)
            lineTo(50f * k, 58f * k)
            lineTo(50f * k, 48f * k)
            quadraticTo(42f * k, 48f * k, 42f * k, 40f * k)
            close()
        }
        drawPath(bubble, color = White, style = Stroke(width = strokeW))

        val r = 3.2f * k
        listOf(
            66f to 24f,
            54f to 31f,
            78f to 31f,
            66f to 39f
        ).forEach { (x, y) ->
            drawCircle(color = White, radius = r, center = Offset(x * k, y * k))
        }
    }
}

@Composable
fun SplashDoodles(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxWidth().height(285.dp)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val u = size.width / 375f
            val h = size.height

            drawWaves(u, size.width, h)
            drawHeart(Offset(180f * u, 30f * u), 20f * u)
            drawPin(Offset(140f * u, 20f * u), 18f * u)
            drawStar(Offset(200f * u, 12f * u), 12f * u)
            drawBulb(Offset(196f * u, 150f * u), 22f * u)
            drawCheck(Offset(300f * u, 50f * u), 18f * u)
            drawCard(Offset(120f * u, 150f * u), 26f * u)
            drawCircle(color = White.copy(alpha = 0.8f), radius = 5f * u, center = Offset(40f * u, 26f * u))
        }
    }
}

private fun DrawScope.drawWaves(u: Float, w: Float, h: Float) {
    val left = Path().apply {
        moveTo(-10f * u, h)
        cubicTo(30f * u, h - 150f * u, 60f * u, h - 60f * u, 40f * u, h - 20f * u)
        cubicTo(20f * u, h + 20f * u, -5f * u, h - 10f * u, -20f * u, h)
        close()
    }
    drawPath(left, color = White.copy(alpha = 0.5f), style = Stroke(width = 2.5f * u))

    val right = Path().apply {
        moveTo(w + 10f * u, h)
        cubicTo(w - 40f * u, h - 130f * u, w - 70f * u, h - 70f * u, w - 55f * u, h - 30f * u)
        cubicTo(w - 35f * u, h + 10f * u, w + 5f * u, h - 5f * u, w + 20f * u, h)
        close()
    }
    drawPath(right, color = White.copy(alpha = 0.5f), style = Stroke(width = 2.5f * u))
}

private fun DrawScope.drawHeart(c: Offset, s: Float) {
    val p = Path().apply {
        moveTo(c.x, c.y + s * 0.35f)
        cubicTo(c.x - s, c.y - s * 0.35f, c.x - s * 0.45f, c.y - s, c.x, c.y - s * 0.35f)
        cubicTo(c.x + s * 0.45f, c.y - s, c.x + s, c.y - s * 0.35f, c.x, c.y + s * 0.35f)
        close()
    }
    drawPath(p, color = White.copy(alpha = 0.5f), style = Stroke(width = s * 0.18f))
}

private fun DrawScope.drawPin(c: Offset, s: Float) {
    drawPath(
        Path().apply {
            moveTo(c.x, c.y + s)
            lineTo(c.x - s * 0.55f, c.y - s * 0.1f)
            lineTo(c.x + s * 0.55f, c.y - s * 0.1f)
            close()
        },
        color = White.copy(alpha = 0.7f)
    )
    drawCircle(color = White.copy(alpha = 0.7f), radius = s * 0.5f, center = Offset(c.x, c.y - s * 0.2f))
    drawCircle(color = Color.Transparent, radius = s * 0.22f, center = Offset(c.x, c.y - s * 0.2f))
}

private fun DrawScope.drawStar(c: Offset, r: Float) {
    val p = Path().apply {
        for (i in 0 until 10) {
            val angle = Math.toRadians((i * 36 - 90).toDouble())
            val radius = if (i % 2 == 0) r else r * 0.45f
            val x = c.x + radius * cos(angle).toFloat()
            val y = c.y + radius * sin(angle).toFloat()
            if (i == 0) moveTo(x, y) else lineTo(x, y)
        }
        close()
    }
    drawPath(p, color = White.copy(alpha = 0.8f), style = Stroke(width = r * 0.16f))
}

private fun DrawScope.drawBulb(c: Offset, s: Float) {
    drawCircle(color = White.copy(alpha = 0.6f), radius = s * 0.5f, center = c, style = Stroke(width = s * 0.16f))
    drawRoundRect(
        color = White.copy(alpha = 0.6f),
        topLeft = Offset(c.x - s * 0.25f, c.y + s * 0.5f),
        size = Size(s * 0.5f, s * 0.4f),
        cornerRadius = CornerRadius(s * 0.1f, s * 0.1f),
        style = Stroke(width = s * 0.14f)
    )
}

private fun DrawScope.drawCheck(c: Offset, s: Float) {
    drawCircle(color = White.copy(alpha = 0.6f), radius = s * 0.5f, center = c, style = Stroke(width = s * 0.14f))
    drawLine(
        White.copy(alpha = 0.6f), Offset(c.x - s * 0.22f, c.y), Offset(c.x - s * 0.05f, c.y + s * 0.18f),
        strokeWidth = s * 0.14f, cap = StrokeCap.Round
    )
    drawLine(
        White.copy(alpha = 0.6f), Offset(c.x - s * 0.05f, c.y + s * 0.18f), Offset(c.x + s * 0.25f, c.y - s * 0.18f),
        strokeWidth = s * 0.14f, cap = StrokeCap.Round
    )
}

private fun DrawScope.drawCard(c: Offset, s: Float) {
    drawRoundRect(
        color = White.copy(alpha = 0.6f),
        topLeft = Offset(c.x - s / 2f, c.y - s / 2f),
        size = Size(s, s * 0.7f),
        cornerRadius = CornerRadius(s * 0.15f, s * 0.15f),
        style = Stroke(width = s * 0.12f)
    )
}