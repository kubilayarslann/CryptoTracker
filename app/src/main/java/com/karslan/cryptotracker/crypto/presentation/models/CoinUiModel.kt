package com.karslan.cryptotracker.crypto.presentation.models

import android.icu.text.NumberFormat
import androidx.annotation.DrawableRes
import com.karslan.cryptotracker.crypto.domain.CoinModel
import com.karslan.cryptotracker.core.presentation.util.getDrawableIdForCoin
import java.util.Locale

data class CoinUiModel(
    val id : String,
    val rank : Int,
    val name : String,
    val symbol : String,
    val marketCapUsd : DisplayableNumber,
    val priceUsd : DisplayableNumber,
    val changePercentage24Hr : DisplayableNumber,
    @param:DrawableRes val iconRes: Int
)

data class DisplayableNumber(
    val value: Double,
    val formatted: String
)


fun CoinModel.toCoinUiModel () : CoinUiModel {
    return CoinUiModel (
        id = this.id,
        rank = this.rank,
        name = this.name,
        symbol = this.symbol,
        priceUsd = this.priceUsd.toDisplayableNumber(),
        marketCapUsd = this.marketCapUsd.toDisplayableNumber(),
        changePercentage24Hr = this.changePercentage24Hr.toDisplayableNumber(),
        iconRes = getDrawableIdForCoin(this.symbol)
    )
}

fun Double.toDisplayableNumber() : DisplayableNumber {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        maximumFractionDigits = 2
        minimumFractionDigits = 2
    }

    return DisplayableNumber(
        value = this,
        formatted = formatter.format(this)
    )
}