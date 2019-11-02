package com.marklynch.invitenearbycustomers.data

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class Customer(val id: Int, val name: String, val geolocation: Geolocation) {

    @JsonCreator
    constructor(
        @JsonProperty("user_id", required = true) id: Int, @JsonProperty("name", required = true) name: String,
        @JsonProperty("latitude", required = true) latitude: Double, @JsonProperty(
            "longitude",
            required = true
        ) longitude: Double
    ) : this(id, name, Geolocation(latitude, longitude))

    fun distanceTo(otherGeolocation: Geolocation) = this.geolocation.distanceTo(otherGeolocation)
}