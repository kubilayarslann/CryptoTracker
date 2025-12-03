package com.karslan.cryptotracker.core.data.network

import com.karslan.cryptotracker.BuildConfig
import com.karslan.cryptotracker.core.domain.util.CryptoTrackerResult
import com.karslan.cryptotracker.core.domain.util.CryptoTrackerNetworkError
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException

fun constructUrl(url: String): String {
    return when {
        url.contains(BuildConfig.BASE_URL) -> url
        url.startsWith("/") -> BuildConfig.BASE_URL + url.drop(1)
        else -> BuildConfig.BASE_URL + url
    }
}

suspend inline fun <reified T> responseToCryptoTrackerResult (
    response: HttpResponse
): CryptoTrackerResult<T, CryptoTrackerNetworkError> {
    return when(response.status.value) {
        in 200..299 -> {
            try {
                CryptoTrackerResult.Success(response.body<T>())
            } catch (e: NoTransformationFoundException) {
                CryptoTrackerResult.Error(CryptoTrackerNetworkError.SERIALIZATION)
            }
        }
        408 -> CryptoTrackerResult.Error(CryptoTrackerNetworkError.REQUEST_TIMEOUT)
        429 -> CryptoTrackerResult.Error(CryptoTrackerNetworkError.TOO_MANY_REQUESTS)

        in 500..599 -> CryptoTrackerResult.Error(CryptoTrackerNetworkError.SERVER_ERROR)
        else -> CryptoTrackerResult.Error(CryptoTrackerNetworkError.UNKNOWN)
    }
}

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): CryptoTrackerResult<T, CryptoTrackerNetworkError> {
    val response = try {
        execute()
    } catch (e: UnresolvedAddressException) {
        return CryptoTrackerResult.Error(CryptoTrackerNetworkError.NO_INTERNET)
    }catch (e: SerializationException) {
        return CryptoTrackerResult.Error(CryptoTrackerNetworkError.SERIALIZATION)
    } catch (e: Exception) {
        currentCoroutineContext().ensureActive()
        return CryptoTrackerResult.Error(CryptoTrackerNetworkError.UNKNOWN)
    }

    return responseToCryptoTrackerResult(response)
}