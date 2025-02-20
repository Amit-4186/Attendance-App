package com.example.attendance.ui.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


enum class TaskState {
    PRESENT,
    ABSENT,
    UNMARKED,
    CANCELED
}
data class TaskItem(
    val subject: String,
    val state: TaskState
)

data class DaySchedule(
    val dayName: String,
    val tasks: List<TaskItem>
)

data class TimetableDetail(
    val days: List<DaySchedule>,
    val timeSchedule: List<String> // NEW: New field for time schedule headers.
)
@Composable
fun TimeTableDetailScreen() {

    val timetable = TimetableDetail(
        days = listOf(
            DaySchedule(
                dayName = "MON",
                tasks = listOf(
                    TaskItem("BCS-601 SE", TaskState.PRESENT),
                    TaskItem("BAI-601 MLT", TaskState.ABSENT),
                    TaskItem("Lunch Break", TaskState.UNMARKED),
                    TaskItem("BOE-068 SPM", TaskState.PRESENT),
                    TaskItem("Lunch Break", TaskState.UNMARKED),
                    TaskItem("BOE-068 SPM", TaskState.PRESENT)
                )
            ),
            DaySchedule(
                dayName = "TUE",
                tasks = listOf(
                    TaskItem("BAI-601 MLT", TaskState.UNMARKED),
                    TaskItem("BOE-068 SPM", TaskState.CANCELED),
                    TaskItem("Lunch Break", TaskState.UNMARKED),
                    TaskItem("BOE-068 SPM", TaskState.PRESENT),
                    TaskItem("Lunch Break", TaskState.UNMARKED),
                    TaskItem("BOE-068 SPM", TaskState.PRESENT)
                )
            ),
            DaySchedule(
                dayName = "WED",
                tasks = listOf(
                    TaskItem("BCS-601 SE", TaskState.PRESENT),
                    TaskItem("BAI-601 MLT", TaskState.PRESENT),
                    TaskItem("BOE-068 SPM", TaskState.PRESENT),
                    TaskItem("BCS-601 SE", TaskState.PRESENT),
                    TaskItem("BAI-601 MLT", TaskState.PRESENT),
                    TaskItem("BOE-068 SPM", TaskState.PRESENT)
                )
            ),
            DaySchedule(
                dayName = "THU",
                tasks = listOf(
                    TaskItem("BCS-601 SE", TaskState.PRESENT),
                    TaskItem("BAI-601 MLT", TaskState.PRESENT),
                    TaskItem("Lunch Break", TaskState.UNMARKED),
                    TaskItem("BCS-601 SE", TaskState.PRESENT),
                    TaskItem("BAI-601 MLT", TaskState.PRESENT),
                    TaskItem("Lunch Break", TaskState.UNMARKED)
                )
            ),
            DaySchedule(
                dayName = "FRI",
                tasks = listOf(
                    TaskItem("BOE-068 SPM", TaskState.PRESENT),
                    TaskItem("BAI-601 MLT", TaskState.ABSENT),
                    TaskItem("BOE-068 SPM", TaskState.PRESENT),
                    TaskItem("BOE-068 SPM", TaskState.PRESENT),
                    TaskItem("BOE-068 SPM", TaskState.PRESENT),
                    TaskItem("BOE-068 SPM", TaskState.PRESENT),
                    TaskItem("BOE-068 SPM", TaskState.PRESENT)
                )
            ),
            DaySchedule(
                dayName = "SAT",
                tasks = listOf(
                    TaskItem("BCS-601 SE", TaskState.CANCELED),
                    TaskItem("BCS-601 SE", TaskState.PRESENT),
                    TaskItem("BAI-601 MLT", TaskState.PRESENT),
                    TaskItem("Lunch Break", TaskState.UNMARKED),
                    TaskItem("BCS-601 SE", TaskState.PRESENT),
                    TaskItem("BAI-601 MLT", TaskState.PRESENT),
                    TaskItem("Lunch Break", TaskState.UNMARKED)
                )
            )
        ),
        timeSchedule = listOf(
            "10:45-11:45",
            "11:45-12:45",
            "12:45-1:45",
            "1:45-2:45",
            "2:45-3:45",
            "3:45-4:45",
            "4:45-5:45"
        )
    )

    Row {
        Column {
            Spacer(Modifier.height(32.dp))
            timetable.days.forEach { daySchedule ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .padding(horizontal = 2.dp, vertical = 2.dp)
                        .height(56.dp)
                        .width(24.dp)
                ) {
                    Text(
                        text = daySchedule.dayName,
                        modifier = Modifier
                            .rotate(-90f)
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center),
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            }
        }

        Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
            Column {
                // NEW: Time Schedule Header Row.
                Row(modifier = Modifier.padding(2.dp)) {
                    timetable.timeSchedule.forEach { time ->
                        TimeCard(time) // NEW: Composable for time schedule header.
                    }
                }
                // Existing tasks rows.
                timetable.days.forEach { dayScheduleRow ->
                    Row(modifier = Modifier.padding(2.dp)) {
                        dayScheduleRow.tasks.forEach { taskItem ->
                            TaskCard(taskItem)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TaskCard(taskItem: TaskItem) {
    val taskColor = when (taskItem.state) {
        TaskState.PRESENT -> Color(0xFF4CAF50)    // Green
        TaskState.ABSENT -> Color(0xFFF44336)     // Red
        TaskState.UNMARKED -> Color(0xFF9E9E9E)   // Gray
        TaskState.CANCELED -> Color(0xFFFFC107)   // Amber
    }

    Card(
        modifier = Modifier
            .padding(horizontal = 2.dp)
            .height(56.dp)
            .width(106.dp),
        colors = CardDefaults.cardColors(
            containerColor = taskColor
        ),
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(
            text = taskItem.subject,
            fontSize = 14.sp,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }
}

@Composable
fun TimeCard(time: String) {
    Card(
        modifier = Modifier
            .padding(horizontal = 2.dp)
            .height(32.dp)
            .width(106.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = time,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center),
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSecondary,
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun TimetableDetailScreenPreview2() {
    TimeTableDetailScreen()
}