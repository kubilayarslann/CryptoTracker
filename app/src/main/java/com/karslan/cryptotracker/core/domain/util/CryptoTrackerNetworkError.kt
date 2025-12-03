package com.karslan.cryptotracker.core.domain.util

enum class CryptoTrackerNetworkError: CryptoTrackerError {
    REQUEST_TIMEOUT,
    TOO_MANY_REQUESTS,
    NO_INTERNET,
    SERVER_ERROR,
    SERIALIZATION,
    UNKNOWN
}