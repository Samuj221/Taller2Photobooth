package com.example.taller2photobooth.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taller2photobooth.R

@Composable
fun PermissionScreen(onRequest: () -> Unit) {
    val isDark = isSystemInDarkTheme()

    val gradient = if (isDark) {
        Brush.verticalGradient(0f to Color(0xFF0F172A), 1f to Color(0xFF111827))
    } else {
        Brush.verticalGradient(0f to Color(0xFFEAF1FF), 1f to Color(0xFFF7F8FF))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(20.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (isDark) Color(0xFF161B22) else Color(0xFFFFFFFF)
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 28.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                        .background(if (isDark) Color(0xFF243B55) else Color(0xFF3B82F6).copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.PhotoCamera,
                        contentDescription = null,
                        tint = if (isDark) Color(0xFF93C5FD) else Color(0xFF2563EB),
                        modifier = Modifier.size(36.dp)
                    )
                }

                Spacer(Modifier.height(18.dp))

                val title = buildAnnotatedString {
                    withStyle(SpanStyle(color = if (isDark) Color(0xFF93C5FD) else Color(0xFF1D4ED8), fontWeight = FontWeight.ExtraBold)) {
                        append(stringResource(R.string.pb_title_photobooth))
                    }
                    withStyle(SpanStyle(color = if (isDark) Color(0xFFE5E7EB) else Color(0xFF0F172A), fontWeight = FontWeight.ExtraBold)) {
                        append(stringResource(R.string.pb_title_booth))
                    }
                }
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineLarge,
                    letterSpacing = 0.5.sp,
                    textAlign = TextAlign.Center
                )

                // (línea debajo del título eliminada)

                Spacer(Modifier.height(12.dp))

                Text(
                    text = stringResource(R.string.need_camera_perm_title),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = stringResource(R.string.need_camera_perm_desc),
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (isDark) Color(0xFF9CA3AF) else Color(0xFF6B7280),
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(22.dp))

                Button(
                    onClick = onRequest,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isDark) Color(0xFF2563EB) else Color(0xFF1D4ED8)
                    ),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
                ) {
                    Text(
                        text = stringResource(R.string.unlock_camera),
                        style = MaterialTheme.typography.titleSmall,
                        letterSpacing = 0.3.sp
                    )
                }
            }
        }

        Text(
            text = stringResource(R.string.authors),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp),
            style = MaterialTheme.typography.labelLarge,
            color = if (isDark) Color(0xFF93C5FD) else Color(0xFF1F2937)
        )
    }
}
