package com.example.attendance.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.attendance.R
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.attendance.viewmodel.HomeScreenViewModel

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel = HomeScreenViewModel()) {

    val timetablesList = viewModel.timetables.collectAsState().value
    val showDialog = remember { mutableStateOf(false) }
    val newTimetableName = remember { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog.value = true }, shape = CircleShape) {
                Icon(Icons.Default.Add, contentDescription = "Add Timetable")
            }
        },
        modifier = Modifier.background(color = MaterialTheme.colorScheme.primary)
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {
            if (timetablesList.isNotEmpty()) {
                LazyColumn(
                    contentPadding = paddingValues, modifier = Modifier
                        .padding(12.dp)
                        .background(
                            MaterialTheme.colorScheme.background
                        )
                ) {
                    items(timetablesList.size) { index ->
                        TimetableCard(
                            timetablesList[index]!!.name,
                            timetablesList[index]!!.percentage
                        )
                    }

                }
            } else {
                Image(
                    painter = painterResource(id = R.drawable.empty),
                    contentDescription = "Empty"
                )
            }

            if (showDialog.value) {
                Dialog(onDismissRequest = { showDialog.value = false }) {
                    Column(
                        modifier = Modifier
                            .size(280.dp, 160.dp)
                            .background(
                                color = MaterialTheme.colorScheme.secondary,
                                shape = RoundedCornerShape(12.dp)
                            ),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,

                        ) {
                        TextField(
                            value = newTimetableName.value,
                            onValueChange = { newTimetableName.value = it },
                            label = { Text("Enter Timetable Name") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp)
                        )
                        Spacer(modifier = Modifier.height(24.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Button(onClick = { showDialog.value = false }) {
                                Text("Cancel")
                            }
                            Button(onClick = {
                                viewModel.add(newTimetableName.value)
                                showDialog.value = false
                            }) {
                                Text(text = "Add")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TimetableCard(timetableName: String, percentage: Int) {
    Card(
        onClick = {},
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .height(56.dp)
            .border(1.dp, Color.DarkGray, RoundedCornerShape(24.dp)),
        shape = RoundedCornerShape(24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp, 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = CenterVertically
        ) {
            Text(
                text = timetableName,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "$percentage%",
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}