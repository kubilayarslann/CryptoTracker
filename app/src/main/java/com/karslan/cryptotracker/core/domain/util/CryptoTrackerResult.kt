package com.karslan.cryptotracker.core.domain.util

import com.plcoding.cryptotracker.util.CryptoTrackerError

typealias DomainError = CryptoTrackerError

sealed interface CryptoTrackerResult<out D, out E: CryptoTrackerError> {
    data class Success<out D>(val data: D): CryptoTrackerResult<D, Nothing>
    data class Error<out E: DomainError>(val error: E): CryptoTrackerResult<Nothing, E>
}

inline fun <T, E: CryptoTrackerError, R> CryptoTrackerResult<T, E>.map(map: (T) -> R): CryptoTrackerResult<R, E> {
    return when(this) {
        is CryptoTrackerResult.Error -> CryptoTrackerResult.Error(error)
        is CryptoTrackerResult.Success -> CryptoTrackerResult.Success(map(data))
    }
}

fun <T, E: CryptoTrackerError> CryptoTrackerResult<T, E>.asEmptyDataResult(): EmptyResult<E> {
    return map {  }
}

inline fun <T, E: CryptoTrackerError> CryptoTrackerResult<T, E>.onSuccess(action: (T) -> Unit): CryptoTrackerResult<T, E> {
    return when(this) {
        is CryptoTrackerResult.Error -> this
        is CryptoTrackerResult.Success -> {
            action(data)
            this
        }
    }
}
inline fun <T, E: CryptoTrackerError> CryptoTrackerResult<T, E>.onError(action: (E) -> Unit): CryptoTrackerResult<T, E> {
    return when(this) {
        is CryptoTrackerResult.Error -> {
            action(error)
            this
        }
        is CryptoTrackerResult.Success -> this
    }
}

typealias EmptyResult<E> = CryptoTrackerResult<Unit, E>