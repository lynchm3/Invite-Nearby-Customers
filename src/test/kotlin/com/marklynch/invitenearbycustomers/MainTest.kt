package com.marklynch.invitenearbycustomers

import com.marklynch.invitenearbycustomers.data.Customer
import com.marklynch.invitenearbycustomers.data.Geolocation
import com.marklynch.invitenearbycustomers.io.TEST_RESOURCES_BASE_PATH
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MainTest {

    @Test
    fun `Test happy path`() {

        val expected = """${customersToInvitePrefixMsg}Ian Kehoe (4)
Nora Dempsey (5)
Theresa Enright (6)
Eoin Ahearn (8)
Richard Finnegan (11)
Christina McArdle (12)
Olive Ahearn (13)
Michael Ahearn (15)
Patricia Cahill (17)
Eoin Gallagher (23)
Rose Enright (24)
Stephen McArdle (26)
Oliver Ahearn (29)
Nick Enright (30)
Alan Behan (31)
Lisa Ahearn (39)"""

        val filePath = "$TEST_RESOURCES_BASE_PATH/customers.txt."
        assertEquals("Happy path test failed", expected, generateInviteList(filePath, officeLocation, cutoffDistanceKm))
    }

    @Test
    fun `Test customers file not found`() {
        val filePath = "../asdfgsdfgdfgfgergdafgsss.txt"
        assertEquals(
            "File not found test failed",
            fileNotFoundMsg.format(filePath),
            generateInviteList(filePath, officeLocation, cutoffDistanceKm)
        )
    }

    @Test
    fun `Test empty customers file`() {
        val filePath = "$TEST_RESOURCES_BASE_PATH/empty.txt."
        assertEquals(
            "Empty customers file incorrect",
            fileEmptyMsg.format(filePath),
            generateInviteList(filePath, officeLocation, cutoffDistanceKm)
        )
    }

    @Test
    fun `Test customers file format invalid`() {
        val filePathInvalidField = "$TEST_RESOURCES_BASE_PATH/invalid_field.txt."
        assertTrue(
            "Invalid field test failed",
            generateInviteList(
                filePathInvalidField,
                officeLocation,
                cutoffDistanceKm
            ).contains("is in an invalid format")
        )

        val filePathInvalidJson = "$TEST_RESOURCES_BASE_PATH/invalid_json.txt."
        assertTrue(
            "Invalid json test failed",
            generateInviteList(
                filePathInvalidJson,
                officeLocation,
                cutoffDistanceKm
            ).contains("is in an invalid format")
        )

        val filePathMissingField = "$TEST_RESOURCES_BASE_PATH/missing_field.txt."
        assertTrue(
            "Missing field test failed",
            generateInviteList(
                filePathMissingField,
                officeLocation,
                cutoffDistanceKm
            ).contains("is in an invalid format")
        )
    }

    @Test
    fun `Test customersJsonToList()`() {
        assertEquals(
            "Parsing file contents to customer list failed",
            customers, customersJson.customersJsonToList()
        )
    }

    @Test
    fun `Test generateStringOfCustomerNamesAndIds()`() {

        val expected = """Christina McArdle (12)"""
        assertEquals(
            "Parsing file contents to customer list failed",
            expected, customers.generateStringOfCustomersNamesAndIdsToInvite(officeLocation, cutoffDistanceKm)
        )
    }

    private val customersJson =
        """{"latitude": "52.986375", "user_id": 12, "name": "Christina McArdle", "longitude": "-6.043701"}
        {"latitude": "51.92893", "user_id": 1, "name": "Alice Cahill", "longitude": "-10.27699"}
        {"latitude": "51.8856167", "user_id": 2, "name": "Ian McArdle", "longitude": "-10.4240951"}
        {"latitude": "52.3191841", "user_id": 3, "name": "Jack Enright", "longitude": "-8.5072391"}"""

    private val customers = listOf(
        Customer(12, "Christina McArdle", Geolocation(52.986375, -6.043701)),
        Customer(1, "Alice Cahill", Geolocation(51.92893, -10.27699)),
        Customer(2, "Ian McArdle", Geolocation(51.8856167, -10.4240951)),
        Customer(3, "Jack Enright", Geolocation(52.3191841, -8.5072391))
    )


}