package com.example.proximitysenzoraplikacija

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class Viewmodel @Inject constructor(
    private val proximitySensor: ProximitySensor
): ViewModel() {

    var distance = mutableStateOf(999f)

    init {
        proximitySensor.startListening()
        proximitySensor.setOnSensorValuesChangedListener { value ->
            distance.value = value
        }
    }
}