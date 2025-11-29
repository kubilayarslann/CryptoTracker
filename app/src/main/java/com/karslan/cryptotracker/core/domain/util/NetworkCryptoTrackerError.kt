package com.karslan.cryptotracker.core.domain.util

import com.plcoding.cryptotracker.util.CryptoTrackerError

enum class NetworkCryptoTrackerError: CryptoTrackerError {
    REQUEST_TIMEOUT,
    TOO_MANY_REQUESTS,
    NO_INTERNET,
    SERVER_ERROR,
    SERIALIZATION,
    UNKNOWN
}