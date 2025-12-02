package com.karslan.cryptotracker.crypto.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinDto(
    val id : String,
    val rank : Int,
    val name : String,
    val symbol : String,
    val marketCapUsd : Double,
    val priceUsd : Double,
    val changePercentage24Hr : Double,
)
