package com.marklynch.invitenearbycustomers.data

import java.lang.Math.toRadians
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

data class Geolocation(val latitude: Double, val longitude: Double) {

    fun distanceTo(destination: Geolocation): Double {

        val dLongitude = toRadians(destination.longitude - this.longitude)
        val originLatitude = toRadians(this.latitude)
        val destinationLatitude = toRadians(destination.latitude)

        return earthRadiusInKm * acos(
            sin(originLatitude) * sin(destinationLatitude) + cos(
                originLatitude
            ) * cos(destinationLatitude) * cos(dLongitude)
        )
    }

    companion object {
        private const val earthRadiusInKm: Double = 6_371.0
    }
}