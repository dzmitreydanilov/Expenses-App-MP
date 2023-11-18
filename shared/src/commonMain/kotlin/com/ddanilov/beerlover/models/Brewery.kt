package com.ddanilov.beerlover.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Brewery(
    val id: String,
    val name: String? = null,
    @SerialName("brewery_type")
    val type: String? = null,
    @SerialName("address_1")
    val address1: String? = null,
    @SerialName("address_2")
    val address2: String? = null,
    @SerialName("address_3")
    val address3: String? = null,
    val city: String? = null,
    val street: String? = null,
    @SerialName("state_province")
    val stateProvince: String?= null,
    val state: String?= null,
    @SerialName("postal_code")
    val postalCode: String?= null,
    val country: String?= null,
    val longitude: String? = null,
    val latitude: String? = null,
    val phone: String? = null,
    @SerialName("website_url")
    val webSiteUrl: String?= null
)
