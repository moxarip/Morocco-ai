package com.example.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

val coreSteps = listOf(
    "فهم الفكرة...",
    "إنشاء سكريبت احترافي...",
    "توليد مشاهد وصور...",
    "توليد تعليق صوتي...",
    "إضافة تفاصيل ومؤثرات...",
    "تصدير نهائي..."
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreationFlowScreen(prompt: String, onNavigateBack: () -> Unit) {
    var currentStep by remember { mutableStateOf(0) }
    var isComplete by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        for (i in coreSteps.indices) {
            currentStep = i
            delay(1500) // Mock delay for each AI step
        }
        isComplete = true
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("المحرك الذكي الأساسي") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("الفكرة:", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "\"$prompt\"",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(48.dp))

            if (!isComplete) {
                CircularProgressIndicator(modifier = Modifier.size(64.dp), color = MaterialTheme.colorScheme.secondary)
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    coreSteps[currentStep],
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
            } else {
                Icon(
                    Icons.Default.CheckCircle,
                    contentDescription = "Success",
                    tint = Color(0xFF10B981),
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text("تم إنشاء الفيديو بنجاح!", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(24.dp))
                
                // Mocks showing result options
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    Button(onClick = { /* TODO */ }) { Text("معاينة") }
                    Button(onClick = { /* TODO */ }, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)) { Text("جدولة / نشر") }
                }
            }
        }
    }
}
