package com.karslan.cryptotracker.crypto.presentation.coin_list

import com.karslan.cryptotracker.core.domain.util.CryptoTrackerNetworkError


sealed interface CoinListEvents {
    data class Error(val error: CryptoTrackerNetworkError) : CoinListEvents
}