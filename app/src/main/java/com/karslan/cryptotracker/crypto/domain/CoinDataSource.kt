package com.karslan.cryptotracker.crypto.domain

import com.karslan.cryptotracker.core.domain.util.CryptoTrackerResult
import com.karslan.cryptotracker.core.domain.util.CryptoTrackerNetworkError

interface CoinDataSource {
    suspend fun getCoins(): CryptoTrackerResult<List<CoinModel>, CryptoTrackerNetworkError>
}