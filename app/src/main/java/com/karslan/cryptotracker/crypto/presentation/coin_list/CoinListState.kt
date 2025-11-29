package com.karslan.cryptotracker.crypto.presentation.coin_list

import androidx.compose.runtime.Immutable
import com.karslan.cryptotracker.crypto.presentation.models.CoinUiModel

@Immutable
data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<CoinUiModel> = emptyList(),
    val selectedCoin: CoinUiModel? = null
)
