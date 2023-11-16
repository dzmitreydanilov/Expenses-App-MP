package com.ddanilov.beerlover.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Brewery(
    val id: String,
    val name: String,
    @SerialName("brewery_type")
    val type: String,
    @SerialName("address_1")
    val address1: String,
    @SerialName("address_2")
    val address2: String?,
    @SerialName("address_3")
    val address3: String?,
    val city: String,
    val street: String,
    @SerialName("state_province")
    val stateProvince: String,
    val state: String,
    @SerialName("postal_code")
    val postalCode: String,
    val country: String,
    val longitude: String,
    val latitude: String,
    val phone: String,
    @SerialName("website_url")
    val webSiteUrl: String
)
