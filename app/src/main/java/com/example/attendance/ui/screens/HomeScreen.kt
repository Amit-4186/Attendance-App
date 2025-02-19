package com.example.attendance.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.attendance.ui.theme.AttendanceTheme

@Composable
fun HomeScreen() {
    val mockSemesters = listOf(
        "Semester 1" to 50,
        "Semester 2" to 80
    )

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {}, shape = CircleShape) {
                Icon(Icons.Default.Add, contentDescription = "Add Timetable")
            }
        },
        modifier = Modifier.background(color = MaterialTheme.colorScheme.primary)
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues, modifier = Modifier
                .padding(12.dp)
                .background(
                    MaterialTheme.colorScheme.background
                )
        ) {
            items(mockSemesters.size) { index ->
                SemesterCard(mockSemesters[index].first, mockSemesters[index].second)
            }
        }
    }
}

@Composable
fun SemesterCard(timetableName: String, percentage: Int) {
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
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "$percentage%",
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    AttendanceTheme {
        HomeScreen()
    }
}