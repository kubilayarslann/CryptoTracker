package com.karslan.cryptotracker.core.data.network

import com.karslan.cryptotracker.BuildConfig
import com.karslan.cryptotracker.core.domain.util.CryptoTrackerResult
import com.karslan.cryptotracker.core.domain.util.NetworkCryptoTrackerError
import com.plcoding.cryptotracker.util.CryptoTrackerError
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.isActive
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext

fun constructUrl(url: String): String {
    return when {
        url.contains(BuildConfig.BASE_URL) -> url
        url.startsWith("/") -> BuildConfig.BASE_URL + url.drop(1)
        else -> BuildConfig.BASE_URL + url
    }
}

suspend inline fun <reified T> responseToCryptoTrackerResult (
    response: HttpResponse
): CryptoTrackerResult<T, NetworkCryptoTrackerError> {
    return when(response.status.value) {
        in 200..299 -> {
            try {
                CryptoTrackerResult.Success(response.body<T>())
            } catch (e: NoTransformationFoundException) {
                CryptoTrackerResult.Error(NetworkCryptoTrackerError.SERIALIZATION)
            }
        }
        408 -> CryptoTrackerResult.Error(NetworkCryptoTrackerError.REQUEST_TIMEOUT)
        429 -> CryptoTrackerResult.Error(NetworkCryptoTrackerError.TOO_MANY_REQUESTS)

        in 500..599 -> CryptoTrackerResult.Error(NetworkCryptoTrackerError.SERVER_ERROR)
        else -> CryptoTrackerResult.Error(NetworkCryptoTrackerError.UNKNOWN)
    }
}

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): CryptoTrackerResult<T, NetworkCryptoTrackerError> {
    val response = try {
        execute()
    } catch (e: UnresolvedAddressException) {
        return CryptoTrackerResult.Error(NetworkCryptoTrackerError.NO_INTERNET)
    }catch (e: SerializationException) {
        return CryptoTrackerResult.Error(NetworkCryptoTrackerError.SERIALIZATION)
    } catch (e: Exception) {
        currentCoroutineContext().ensureActive()
        return CryptoTrackerResult.Error(NetworkCryptoTrackerError.UNKNOWN)
    }

    return responseToCryptoTrackerResult(response)
}