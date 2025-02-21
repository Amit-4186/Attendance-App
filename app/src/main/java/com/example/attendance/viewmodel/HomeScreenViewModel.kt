package com.example.attendance.viewmodel

import androidx.lifecycle.ViewModel
import com.example.attendance.data.model.Timetable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate

class HomeScreenViewModel: ViewModel() {
    val _timetables = MutableStateFlow<List<Timetable?>>(emptyList())
    val timetables: StateFlow<List<Timetable?>>
        get() = _timetables

    fun add(name: String){

    }

    fun delete(index: Int){

    }

    fun initialise(){

    }
}