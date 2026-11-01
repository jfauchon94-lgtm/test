package com.droid.test

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.time.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.painterResource
import test.shared.generated.resources.Res
import test.shared.generated.resources.ic_cyclone

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showDetails by remember { mutableStateOf(false) }
        val platform = remember { getPlatform() }
        val currentTime = remember {
            val now = Clock.System.now()
            now.toLocalDateTime(TimeZone.currentSystemDefault())
        }

        Column(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(Res.drawable.ic_cyclone),
                contentDescription = "Compose Multiplatform Logo",
                modifier = Modifier.size(96.dp),
            )

            Text(
                text = "Compose Multiplatform 1.12.1",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 16.dp),
            )

            Text(
                text = "Plateforme active : ${platform.name}",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 8.dp),
            )

            Button(
                onClick = { showDetails = !showDetails },
                modifier = Modifier.padding(top = 24.dp),
            ) {
                Text(if (showDetails) "Masquer les informations" else "En savoir plus")
            }

            AnimatedVisibility(visible = showDetails) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    ),
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Text(
                            text = "Détails du projet 'test'",
                            fontWeight = FontWeight.SemiBold,
                        )
                        Text(text = "• Compose Multiplatform: 1.12.1")
                        Text(text = "• Kotlin: 2.4.21")
                        Text(text = "• Android Gradle Plugin: 9.3.1 (Découplé)")
                        Text(text = "• Architecture : Nouvel aménagement KMP (shared + apps)")
                        Text(text = "• Heure locale : ${currentTime.hour.toString().padStart(2, '0')}:${currentTime.minute.toString().padStart(2, '0')}:${currentTime.second.toString().padStart(2, '0')}")
                    }
                }
            }
        }
    }
}
