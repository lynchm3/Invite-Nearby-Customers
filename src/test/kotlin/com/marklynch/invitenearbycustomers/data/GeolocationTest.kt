package com.marklynch.invitenearbycustomers.data

import org.junit.Assert.assertEquals
import org.junit.Test

class GeolocationTest {

    private val baseLocation = Geolocation(53.339428, -6.257664)

    @Test
    fun `Test distance calc to same location`() {
        val baseLocationAlso = Geolocation(baseLocation.latitude, baseLocation.longitude)
        val officeDistanceActual = baseLocation.distanceTo(baseLocationAlso)
        assertEquals(
            "Distance between duplicate locations incorrect",
            0.0,
            officeDistanceActual,
            0.0
        )
    }

    @Test
    fun `Test distance calc to tiny distance away`() {
        val locationOneMillimetreFromBaseLocation = Geolocation(53.33942800001, -6.25766400001)
        val actual = locationOneMillimetreFromBaseLocation.distanceTo(baseLocation)
        assertEquals(
            "Distance calc of tiny distance away incorrect",
            0.0,
            actual,
            0.001
        )
    }

    @Test
    fun `Test distance calc to small distance ~1km away`() {
        val location0point8339fromBase = Geolocation(53.3362, -6.2690)
        val actual = location0point8339fromBase.distanceTo(baseLocation)
        assertEquals(
            "Distance calc of 0.8339km incorrect",
            0.8339,
            actual,
            0.001
        )
    }

    @Test
    fun `Test distance calc to medium distance ~500km away`() {
        val location463point1468fromBase = Geolocation(51.509865, -0.118092)
        val actual = location463point1468fromBase.distanceTo(baseLocation)
        assertEquals(
            "Distance calc of 463.1468km incorrect",
            463.1468,
            actual,
            0.001
        )
    }

    @Test
    fun `Test distance calc to large distance ~20,000km away`() {
        val location0point8339fromBase = Geolocation(-33.8675, 151.2070)
        val actual = location0point8339fromBase.distanceTo(baseLocation)
        assertEquals(
            "Distance calc of 17214.8122km incorrect",
            17214.8122,
            actual,
            0.001
        )
    }

    @Test
    fun `Test distance calc to North Pole`() {
        val northPoleLocation = Geolocation(90.0, 0.0)
        val actual = northPoleLocation.distanceTo(baseLocation)
        assertEquals(
            "Distance calc to North Pole incorrect",
            4076.4696,
            actual,
            0.001
        )
    }

    @Test
    fun `Test distance calc to South Pole`() {
        val southPoleLocation = Geolocation(-90.0, 0.0)
        val actual = southPoleLocation.distanceTo(baseLocation)
        assertEquals(
            "Distance calc South Pole incorrect",
            15938.6172,
            actual,
            0.001
        )

    }

    @Test
    fun `Test distance calc to Lat 0 Lon 0`() {
        val lat0lon0Location = Geolocation(0.0, 0.0)
        val actual = lat0lon0Location.distanceTo(baseLocation)
        assertEquals(
            "Distance calc to 0,0 incorrect",
            5959.2812,
            actual,
            0.001
        )
    }

    @Test
    fun `Test distance calc to opposite (Antipodal!) spot on sphere`() {
        val oppositeSpotOnSphere = Geolocation(-baseLocation.latitude, -baseLocation.longitude)
        val actual = oppositeSpotOnSphere.distanceTo(baseLocation)
        assertEquals(
            "Distance calc to opposite spot on sphere incorrect",
            11918.5624,
            actual,
            0.001
        )
    }

    @Test
    fun `Test distance calc to same location plus 360 degrees`() {
        val baseLocationPlus360 = Geolocation(baseLocation.latitude + 360, baseLocation.longitude + 360)
        val actual = baseLocationPlus360.distanceTo(baseLocation)
        assertEquals(
            "Distance calc to opposite spot on sphere incorrect",
            0.0,
            actual,
            0.001
        )
    }
}