package com.example.proximitysenzoraplikacija

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class ProximitySensor(
    private val context: Context
): SensorEventListener {

    private val sensorFeature = PackageManager.FEATURE_SENSOR_PROXIMITY
    private val sensorType = Sensor.TYPE_PROXIMITY



    protected var onSensorValuesChanged: ((Float) -> Unit)? = null

    val doesSensorExist: Boolean
        get() = context.packageManager.hasSystemFeature(sensorFeature)

    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null

    fun startListening() {
        if(!doesSensorExist) {
            return
        }
        if(!::sensorManager.isInitialized && sensor == null) {
            sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
            sensor = sensorManager.getDefaultSensor(sensorType)
        }
        sensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(!doesSensorExist) {
            return
        }
        if(event?.sensor?.type == sensorType) {
            onSensorValuesChanged?.invoke(event.values.toList()[0])
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) = Unit

    fun setOnSensorValuesChangedListener(listener: (Float) -> Unit) {
        onSensorValuesChanged = listener
    }
}