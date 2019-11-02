package com.marklynch.invitenearbycustomers.data

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.Assert.assertEquals
import org.junit.Test

class CustomerTest {

    @Test
    fun `Test parsing JSON to Customer`() {
        assertEquals(
            "Json to customer parsing failed", customer,
            mapper.readValue<Customer>(customerJson)
        )
    }


    @Test
    fun `Test distanceTo(Geolocation)`() {
        val baseLocation = Geolocation(53.339428, -6.257664)
        val location0point8339fromBase = Geolocation(53.3362, -6.2690)
        assertEquals(
            "Distance between Customer and Bas location",
            0.8339,
            Customer(1, "Mark Lynch", location0point8339fromBase).distanceTo(baseLocation),
            0.001
        )
    }

    companion object {
        val mapper = jacksonObjectMapper()

        val customer = Customer(12, "Christina McArdle", Geolocation(52.986375, -6.043701))

        const val customerJson =
            """{"latitude": "52.986375", "user_id": 12, "name": "Christina McArdle", "longitude": "-6.043701"}"""

    }
}