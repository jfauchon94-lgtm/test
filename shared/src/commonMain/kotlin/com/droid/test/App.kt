package com.droid.test

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.CollectionInfo
import androidx.compose.ui.semantics.CollectionItemInfo
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.collectionInfo
import androidx.compose.ui.semantics.collectionItemInfo
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
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

data class ProjectDetailItem(
    val id: String,
    val label: String,
    val value: String,
)

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
        val formattedTime = remember(currentTime) {
            "${currentTime.hour.toString().padStart(2, '0')}:${currentTime.minute.toString().padStart(2, '0')}:${currentTime.second.toString().padStart(2, '0')}"
        }

        val detailItems = remember(formattedTime) {
            listOf(
                ProjectDetailItem(
                    id = "compose_version",
                    label = "Compose Multiplatform",
                    value = "1.12.1",
                ),
                ProjectDetailItem(
                    id = "kotlin_version",
                    label = "Kotlin",
                    value = "2.4.21",
                ),
                ProjectDetailItem(
                    id = "agp_version",
                    label = "Android Gradle Plugin",
                    value = "9.3.1 (Découplé)",
                ),
                ProjectDetailItem(
                    id = "architecture",
                    label = "Architecture",
                    value = "Nouvel aménagement KMP (shared + apps)",
                ),
                ProjectDetailItem(
                    id = "local_time",
                    label = "Heure locale",
                    value = formattedTime,
                ),
            )
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
                contentDescription = "Logo Compose Multiplatform",
                modifier = Modifier.size(96.dp),
            )

            Text(
                text = "Compose Multiplatform 1.12.1",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .semantics { heading() },
            )

            Text(
                text = "Plateforme active : ${platform.name}",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 8.dp),
            )

            Button(
                onClick = { showDetails = !showDetails },
                modifier = Modifier
                    .padding(top = 24.dp)
                    .semantics {
                        stateDescription = if (showDetails) {
                            "Développé"
                        } else {
                            "Réduit"
                        }
                    },
            ) {
                Text(if (showDetails) "Masquer les informations" else "Plus d'infos")
            }

            AnimatedVisibility(visible = showDetails) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .semantics {
                            liveRegion = LiveRegionMode.Polite
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    ),
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                    ) {
                        Text(
                            text = "Détails du projet 'test'",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp,
                            modifier = Modifier
                                .padding(bottom = 12.dp)
                                .semantics { heading() },
                        )

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(max = 320.dp)
                                .semantics {
                                    collectionInfo = CollectionInfo(
                                        rowCount = detailItems.size,
                                        columnCount = 1,
                                    )
                                },
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            contentPadding = PaddingValues(vertical = 4.dp),
                        ) {
                            itemsIndexed(
                                items = detailItems,
                                key = { _, item -> item.id },
                            ) { index, item ->
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 6.dp)
                                            .semantics(mergeDescendants = true) {
                                                collectionItemInfo = CollectionItemInfo(
                                                    rowIndex = index,
                                                    rowSpan = 1,
                                                    columnIndex = 0,
                                                    columnSpan = 1,
                                                )
                                            },
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Text(
                                            text = "${item.label} :",
                                            fontWeight = FontWeight.Medium,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            modifier = Modifier.weight(0.45f),
                                        )
                                        Text(
                                            text = item.value,
                                            fontWeight = FontWeight.Normal,
                                            color = MaterialTheme.colorScheme.onSurface,
                                            textAlign = TextAlign.End,
                                            modifier = Modifier.weight(0.55f),
                                        )
                                    }
                                    if (index < detailItems.lastIndex) {
                                        HorizontalDivider(
                                            color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
