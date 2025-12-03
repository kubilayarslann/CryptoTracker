package com.karslan.cryptotracker.crypto.data.network

import com.karslan.cryptotracker.core.data.network.constructUrl
import com.karslan.cryptotracker.core.data.network.safeCall
import com.karslan.cryptotracker.core.domain.util.CryptoTrackerResult
import com.karslan.cryptotracker.core.domain.util.CryptoTrackerNetworkError
import com.karslan.cryptotracker.core.domain.util.map
import com.karslan.cryptotracker.crypto.data.mappers.toCoinModel
import com.karslan.cryptotracker.crypto.data.network.dto.CoinsResponseDto
import com.karslan.cryptotracker.crypto.domain.CoinDataSource
import com.karslan.cryptotracker.crypto.domain.CoinModel
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteCoinDataSource(
    private val httpClient: HttpClient
): CoinDataSource {

    override suspend fun getCoins(): CryptoTrackerResult<List<CoinModel>, CryptoTrackerNetworkError> {
        return safeCall<CoinsResponseDto> {
                    httpClient.get(
                        urlString = constructUrl(url ="/assets")
                    )
        }.map { coinsResponseDto ->
            coinsResponseDto.data.map {
                it.toCoinModel()
            }
        }
    }
}