package com.odb.myapplication.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.odb.myapplication.ui.ObdViewModel
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween

@Composable
fun ObdDashboardScreen(modifier: Modifier = Modifier, vm: ObdViewModel) {
    val rpmRaw = vm.rpm.collectAsState().value
    val speedRaw = vm.speed.collectAsState().value
    val coolantRaw = vm.coolant.collectAsState().value
    val fuelLevelRaw = vm.fuelLevel.collectAsState().value
    val engineLoadRaw = vm.engineLoad.collectAsState().value
    val throttlePositionRaw = vm.throttlePosition.collectAsState().value
    val mafFlowRaw = vm.mafFlow.collectAsState().value
    val intakeTempRaw = vm.intakeTemp.collectAsState().value
    val fuelPressureRaw = vm.fuelPressure.collectAsState().value
    val fuelRailPressureRaw = vm.fuelRailPressure.collectAsState().value
    val o2Sensor1Raw = vm.o2Sensor1.collectAsState().value
    val o2Sensor2Raw = vm.o2Sensor2.collectAsState().value
    val o2Sensor3Raw = vm.o2Sensor3.collectAsState().value
    val o2Sensor4Raw = vm.o2Sensor4.collectAsState().value
    val ambientTempRaw = vm.ambientTemp.collectAsState().value
    val barometricPressureRaw = vm.barometricPressure.collectAsState().value
    val manifoldPressureRaw = vm.manifoldPressure.collectAsState().value
    val controlVoltageRaw = vm.controlVoltage.collectAsState().value
    val shortFuelTrim1Raw = vm.shortFuelTrim1.collectAsState().value
    val longFuelTrim1Raw = vm.longFuelTrim1.collectAsState().value
    val shortFuelTrim2Raw = vm.shortFuelTrim2.collectAsState().value
    val longFuelTrim2Raw = vm.longFuelTrim2.collectAsState().value

    val rpm by animateFloatAsState(targetValue = rpmRaw, animationSpec = tween(350), label = "rpm")
    val speed by animateFloatAsState(targetValue = speedRaw, animationSpec = tween(350), label = "speed")
    val coolant by animateFloatAsState(targetValue = coolantRaw, animationSpec = tween(350), label = "coolant")
    val fuelLevel by animateFloatAsState(targetValue = fuelLevelRaw, animationSpec = tween(350), label = "fuelLevel")
    val engineLoad by animateFloatAsState(targetValue = engineLoadRaw, animationSpec = tween(350), label = "engineLoad")
    val throttlePosition by animateFloatAsState(targetValue = throttlePositionRaw, animationSpec = tween(350), label = "throttlePosition")
    val mafFlow by animateFloatAsState(targetValue = mafFlowRaw, animationSpec = tween(350), label = "mafFlow")
    val intakeTemp by animateFloatAsState(targetValue = intakeTempRaw, animationSpec = tween(350), label = "intakeTemp")
    val fuelPressure by animateFloatAsState(targetValue = fuelPressureRaw, animationSpec = tween(350), label = "fuelPressure")
    val fuelRailPressure by animateFloatAsState(targetValue = fuelRailPressureRaw, animationSpec = tween(350), label = "fuelRailPressure")
    val o2Sensor1 by animateFloatAsState(targetValue = o2Sensor1Raw, animationSpec = tween(350), label = "o2Sensor1")
    val o2Sensor2 by animateFloatAsState(targetValue = o2Sensor2Raw, animationSpec = tween(350), label = "o2Sensor2")
    val o2Sensor3 by animateFloatAsState(targetValue = o2Sensor3Raw, animationSpec = tween(350), label = "o2Sensor3")
    val o2Sensor4 by animateFloatAsState(targetValue = o2Sensor4Raw, animationSpec = tween(350), label = "o2Sensor4")
    val ambientTemp by animateFloatAsState(targetValue = ambientTempRaw, animationSpec = tween(350), label = "ambientTemp")
    val barometricPressure by animateFloatAsState(targetValue = barometricPressureRaw, animationSpec = tween(350), label = "barometricPressure")
    val manifoldPressure by animateFloatAsState(targetValue = manifoldPressureRaw, animationSpec = tween(350), label = "manifoldPressure")
    val controlVoltage by animateFloatAsState(targetValue = controlVoltageRaw, animationSpec = tween(350), label = "controlVoltage")
    val shortFuelTrim1 by animateFloatAsState(targetValue = shortFuelTrim1Raw, animationSpec = tween(350), label = "shortFuelTrim1")
    val longFuelTrim1 by animateFloatAsState(targetValue = longFuelTrim1Raw, animationSpec = tween(350), label = "longFuelTrim1")
    val shortFuelTrim2 by animateFloatAsState(targetValue = shortFuelTrim2Raw, animationSpec = tween(350), label = "shortFuelTrim2")
    val longFuelTrim2 by animateFloatAsState(targetValue = longFuelTrim2Raw, animationSpec = tween(350), label = "longFuelTrim2")

    val showConnect = remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0F172A)),
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        // Header section (fixed at top)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF0F172A))
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            if (showConnect.value) {
                val devices = vm.pairedDeviceNames()
                val selected = remember { mutableStateOf<String?>(null) }
                AlertDialog(
                    onDismissRequest = { showConnect.value = false },
                    title = { Text("Connect to ESP32") },
                    text = {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text("Tap Connect to try 'ESP32_OBD2_Scanner' or pick a paired device below:")
                            if (devices.isNotEmpty()) {
                                LazyColumn(modifier = Modifier.height(160.dp)) {
                                    items(devices) { name ->
                                        TextButton(onClick = { selected.value = name }) { Text(name) }
                                    }
                                }
                                if (selected.value != null) {
                                    Text("Selected: ${'$'}{selected.value}")
                                }
                            } else {
                                Text("No paired devices found.")
                            }
                            Text("Status: ${'$'}{vm.status.collectAsState().value}")
                        }
                    },
                    confirmButton = {
                        val label = if (selected.value != null) "Connect" else "Connect Default"
                        TextButton(onClick = {
                            vm.connect(selected.value ?: "ESP32_OBD2_Scanner")
                            showConnect.value = false
                        }) { Text(label) }
                    },
                    dismissButton = {
                        TextButton(onClick = { showConnect.value = false }) { Text("Cancel") }
                    }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "OBD Dashboard",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.White
                )
                

            }
        }

        // Scrollable dashboard content
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Main metrics row 1
            item {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    MetricCard(title = "Engine RPM", value = String.format(java.util.Locale.US, "%.0f", rpm), unit = "RPM", modifier = Modifier.weight(1f))
                    MetricCard(title = "Speed", value = String.format(java.util.Locale.US, "%.0f", speed), unit = "km/h", modifier = Modifier.weight(1f))
                }
            }

            // Main metrics row 2
            item {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    MetricCard(title = "Coolant Temp", value = String.format(java.util.Locale.US, "%.0f", coolant), unit = "°C", modifier = Modifier.weight(1f))
                    MetricCard(title = "Fuel Level", value = String.format(java.util.Locale.US, "%.0f", fuelLevel), unit = "%", modifier = Modifier.weight(1f))
                }
            }

            // Engine Performance section
            item {
                SectionCard(title = "Engine Performance") {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        SmallMetric(title = "Engine Load", value = String.format(java.util.Locale.US, "%.1f", engineLoad), unit = "%", modifier = Modifier.weight(1f))
                        SmallMetric(title = "Throttle Position", value = String.format(java.util.Locale.US, "%.1f", throttlePosition), unit = "%", modifier = Modifier.weight(1f))
                    }
                    Spacer(Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        SmallMetric(title = "MAF Flow Rate", value = String.format(java.util.Locale.US, "%.1f", mafFlow), unit = "g/s", modifier = Modifier.weight(1f))
                        SmallMetric(title = "Intake Temp", value = String.format(java.util.Locale.US, "%.0f", intakeTemp), unit = "°C", modifier = Modifier.weight(1f))
                    }
                }
            }


            
            // RPM Trend section
            item {
                SectionCard(title = "RPM Trend") {
                    PlaceholderChart()
                }
            }

            // Fuel System section
            item {
                SectionCard(title = "Fuel System") {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        SmallMetric(title = "Fuel Pressure", value = String.format(java.util.Locale.US, "%.1f", fuelPressure), unit = "bar", modifier = Modifier.weight(1f))
                        SmallMetric(title = "Fuel Rail Pressure", value = String.format(java.util.Locale.US, "%.1f", fuelRailPressure), unit = "bar", modifier = Modifier.weight(1f))
                    }
                }
            }

            // Additional sections for more OBD2 data
            item {
                SectionCard(title = "Oxygen Sensors") {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        SmallMetric(title = "O2 Sensor 1", value = String.format(java.util.Locale.US, "%.3f", o2Sensor1), unit = "V", modifier = Modifier.weight(1f))
                        SmallMetric(title = "O2 Sensor 2", value = String.format(java.util.Locale.US, "%.3f", o2Sensor2), unit = "V", modifier = Modifier.weight(1f))
                    }
                    Spacer(Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        SmallMetric(title = "O2 Sensor 3", value = String.format(java.util.Locale.US, "%.3f", o2Sensor3), unit = "V", modifier = Modifier.weight(1f))
                        SmallMetric(title = "O2 Sensor 4", value = String.format(java.util.Locale.US, "%.3f", o2Sensor4), unit = "V", modifier = Modifier.weight(1f))
                    }
                }
            }

            item {
                SectionCard(title = "Environmental") {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        SmallMetric(title = "Ambient Temp", value = String.format(java.util.Locale.US, "%.0f", ambientTemp), unit = "°C", modifier = Modifier.weight(1f))
                        SmallMetric(title = "Barometric Press", value = String.format(java.util.Locale.US, "%.0f", barometricPressure), unit = "kPa", modifier = Modifier.weight(1f))
                    }
                    Spacer(Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        SmallMetric(title = "Manifold Press", value = String.format(java.util.Locale.US, "%.0f", manifoldPressure), unit = "kPa", modifier = Modifier.weight(1f))
                        SmallMetric(title = "Control Voltage", value = String.format(java.util.Locale.US, "%.2f", controlVoltage), unit = "V", modifier = Modifier.weight(1f))
                    }
                }
            }

            item {
                SectionCard(title = "Fuel Trims") {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        SmallMetric(title = "Short Fuel Trim 1", value = String.format(java.util.Locale.US, "%.1f", shortFuelTrim1), unit = "%", modifier = Modifier.weight(1f))
                        SmallMetric(title = "Long Fuel Trim 1", value = String.format(java.util.Locale.US, "%.1f", longFuelTrim1), unit = "%", modifier = Modifier.weight(1f))
                    }
                    Spacer(Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        SmallMetric(title = "Short Fuel Trim 2", value = String.format(java.util.Locale.US, "%.1f", shortFuelTrim2), unit = "%", modifier = Modifier.weight(1f))
                        SmallMetric(title = "Long Fuel Trim 2", value = String.format(java.util.Locale.US, "%.1f", longFuelTrim2), unit = "%", modifier = Modifier.weight(1f))
                    }
                }
            }

            // Bottom padding for better scrolling experience
            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
private fun MetricCard(title: String, value: String, unit: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                color = Color.White,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Circular gauge with value
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(
                        color = Color(0xFF0F172A),
                        shape = CircleShape
                    )
                    .padding(4.dp)
                    .background(
                        color = Color(0xFF1E293B),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = value,
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
                    color = when {
                        title.contains("Fuel Level") && value.toFloatOrNull() == 0f -> Color(0xFFEF4444) // Red for fuel
                        else -> Color(0xFF60A5FA) // Light blue for others
                    }
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Unit
            Text(
                text = unit,
                style = MaterialTheme.typography.labelMedium,
                color = Color(0xFF94A3B8)
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Range info
            Text(
                text = when {
                    title.contains("Engine RPM") -> "Max: 8000 RPM"
                    title.contains("Speed") -> "Max: 200 km/h"
                    title.contains("Coolant Temp") -> "Normal: 85-105°C"
                    title.contains("Fuel Level") -> "Range: ~0km"
                    else -> ""
                },
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF64748B),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun SmallMetric(title: String, value: String, unit: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                color = Color(0xFF94A3B8)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = value,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = unit,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF64748B)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            // Progress bar line
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(
                        color = Color(0xFF334155),
                        shape = RoundedCornerShape(1.dp)
                    )
            )
        }
    }
}

@Composable
private fun SectionCard(title: String, content: @Composable () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Lightning bolt icon
                Text(
                    text = "⚡",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color(0xFFFBBF24)
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                    color = Color.White
                )
            }
            content()
        }
    }
}

@Composable
private fun PlaceholderChart() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF1F2937))
    )
}
