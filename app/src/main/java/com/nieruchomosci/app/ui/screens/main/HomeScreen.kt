package com.nieruchomosci.app.ui.screens.main

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nieruchomosci.app.ui.viewmodels.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val totalAnnualCost by viewModel.totalAnnualCost.collectAsState()
    // Placeholder for paid amount - we'll connect this later
    val paidAmount = totalAnnualCost * 0.3f

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Roczny koszt utrzymania:",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "%.2f zł".format(totalAnnualCost),
                style = MaterialTheme.typography.headlineMedium.copy(fontSize = 48.sp),
                color = MaterialTheme.colorScheme.primary
            )
        }

        AnimatedHouse()

        CostsChart(total = totalAnnualCost, paid = paidAmount)
    }
}

@Composable
fun CostsChart(total: Double, paid: Double) {
    val paidPercentage = if (total > 0) (paid / total).toFloat() else 0f

    Box(contentAlignment = Alignment.Center, modifier = Modifier.size(150.dp)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawArc(
                color = MaterialTheme.colorScheme.surface,
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = 15f)
            )
            drawArc(
                color = MaterialTheme.colorScheme.primary,
                startAngle = -90f,
                sweepAngle = 360 * paidPercentage,
                useCenter = false,
                style = Stroke(width = 15f)
            )
        }
        Text(
            text = "${(paidPercentage * 100).toInt()}%",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}


@Composable
fun AnimatedHouse() {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulse by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "pulse"
    )

    val houseColor = MaterialTheme.colorScheme.primary

    Canvas(modifier = Modifier.size(120.dp)) {
        val size = this.size.minDimension
        val strokeWidth = size * 0.05f

        // Draw the house body
        drawRect(
            color = houseColor,
            topLeft = Offset(size * 0.2f, size * 0.5f),
            size = Size(size * 0.6f * pulse, size * 0.5f),
            style = Stroke(width = strokeWidth)
        )

        // Draw the roof
        val path = androidx.compose.ui.graphics.Path()
        path.moveTo(size * 0.1f, size * 0.5f)
        path.lineTo(size * 0.5f, size * 0.2f * (2 - pulse))
        path.lineTo(size * 0.9f, size * 0.5f)
        drawPath(
            path = path,
            color = houseColor,
            style = Stroke(width = strokeWidth)
        )
    }
}