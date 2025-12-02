package com.karslan.cryptotracker.crypto.presentation.coin_list

import com.karslan.cryptotracker.crypto.presentation.models.CoinUiModel

interface CoinListActions {
    data class OnCoinClick(val coin: CoinUiModel) : CoinListActions
    data object OnRefresh : CoinListActions
}