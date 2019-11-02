package com.marklynch.invitenearbycustomers

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.marklynch.invitenearbycustomers.data.Customer
import com.marklynch.invitenearbycustomers.data.Geolocation
import com.marklynch.invitenearbycustomers.io.PROD_RESOURCES_BASE_PATH
import com.marklynch.invitenearbycustomers.io.readFile
import java.io.FileNotFoundException

val officeLocation = Geolocation(53.339428, -6.257664)
const val cutoffDistanceKm = 100.0
const val defaultCustomersFileName = "customers.txt"
const val customersToInvitePrefixMsg = "Customers to invite:\n"
const val noCustomersToInviteMsg = "There are no customers to invite"
const val invalidFileFormatMsg = "The file %s is in an invalid format error: %s"
const val fileNotFoundMsg = "The file %s was not found"
const val fileEmptyMsg = "The file %s is empty"

fun main(args: Array<String>) {
    val inputFile = args.getOrNull(0) ?: "$PROD_RESOURCES_BASE_PATH$defaultCustomersFileName"
    println(
        generateInviteList(
            inputFile,
            officeLocation,
            cutoffDistanceKm
        )
    )
}

fun generateInviteList(filePath: String, officeLocation: Geolocation, cutoffDistanceKm: Double): String {

    //Read file
    val customersJson = try {
        readFile(filePath)
    } catch (fileNotFoundException: FileNotFoundException) {
        return fileNotFoundMsg.format(filePath)
    }

    if (customersJson.isBlank()) return fileEmptyMsg.format(filePath)

    //Convert file contents to List of Customers
    val customers = try {
        customersJson.customersJsonToList()
    } catch (jsonProcessingException: JsonProcessingException) {
        return invalidFileFormatMsg.format(filePath, jsonProcessingException.message)
    }

    //Filter by distance, sort by id, then map to list of names
    val customerNamesAndIdsString =
        customers.generateStringOfCustomersNamesAndIdsToInvite(officeLocation, cutoffDistanceKm)

    //Return our list of customer list of names and ids if not empty
    return if (customerNamesAndIdsString.isBlank()) noCustomersToInviteMsg
    else "$customersToInvitePrefixMsg$customerNamesAndIdsString"
}

fun String.customersJsonToList() =
    split("\n")
        .map { jacksonObjectMapper().readValue<Customer>(it) }

fun List<Customer>.generateStringOfCustomersNamesAndIdsToInvite(officeLocation: Geolocation, cutoffDistanceKm: Double) =
    filter { it.distanceTo(officeLocation) < cutoffDistanceKm }
        .sortedBy { it.id }
        .map { "${it.name} (${it.id})" }
        .joinToString("\n")