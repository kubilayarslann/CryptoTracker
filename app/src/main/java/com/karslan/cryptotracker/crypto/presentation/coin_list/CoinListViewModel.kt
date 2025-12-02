package com.karslan.cryptotracker.crypto.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karslan.cryptotracker.core.domain.util.onError
import com.karslan.cryptotracker.core.domain.util.onSuccess
import com.karslan.cryptotracker.crypto.data.network.RemoteCoinDataSource
import com.karslan.cryptotracker.crypto.presentation.models.toCoinUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinListViewModel(
    private val remoteCoinDataSource: RemoteCoinDataSource
) : ViewModel() {

    private val _state = MutableStateFlow(CoinListState())
    val state = _state
        .onStart { loadCoins() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            CoinListState()
        )

    fun onActions(actions: CoinListActions) {
        when (actions) {
            is CoinListActions.OnCoinClick -> {

            }
        }
    }


    private fun loadCoins(){
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }

            remoteCoinDataSource
                .getCoins()
                .onSuccess { coins ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            coins = coins.map { it.toCoinUiModel() }
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false
                        )
                    }
                }
        }
    }
}