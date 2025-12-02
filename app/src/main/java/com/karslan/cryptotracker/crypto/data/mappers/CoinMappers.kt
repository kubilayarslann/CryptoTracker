package com.karslan.cryptotracker.crypto.data.mappers

import com.karslan.cryptotracker.crypto.data.network.dto.CoinDto
import com.karslan.cryptotracker.crypto.domain.CoinModel

fun CoinDto.toCoinModel(): CoinModel {
    return CoinModel(
        id = id,
        rank = rank,
        name = name,
        symbol = symbol,
        marketCapUsd = marketCapUsd,
        priceUsd = priceUsd,
        changePercent24Hr = changePercent24Hr
    )
}