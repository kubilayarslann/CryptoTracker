package com.karslan.cryptotracker.core.presentation.util

import android.content.Context
import com.karslan.cryptotracker.R
import com.karslan.cryptotracker.core.domain.util.CryptoTrackerNetworkError

fun CryptoTrackerNetworkError.toString(context: Context): String {

    val resId = when(this) {
        CryptoTrackerNetworkError.REQUEST_TIMEOUT -> R.string.error_request_timeout
        CryptoTrackerNetworkError.TOO_MANY_REQUESTS -> R.string.error_too_many_requests
        CryptoTrackerNetworkError.NO_INTERNET -> R.string.error_no_internet
        CryptoTrackerNetworkError.SERVER_ERROR -> R.string.error_unknown
        CryptoTrackerNetworkError.SERIALIZATION -> R.string.error_serialization
        CryptoTrackerNetworkError.UNKNOWN -> R.string.error_unknown
    }
    return context.getString(resId)

}