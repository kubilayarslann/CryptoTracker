package com.karslan.cryptotracker.crypto.presentation.coin_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.karslan.cryptotracker.crypto.presentation.coin_list.CoinListState
import com.karslan.cryptotracker.ui.theme.CryptoTrackerTheme

@Composable
fun CoinListScreen(
    modifier: Modifier = Modifier,
    state: CoinListState
) {

    if (state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = state.coins){ coinUiModel ->
                CoinListItem(
                    modifier = Modifier.fillMaxWidth(),
                    coinUIModel = coinUiModel,
                    onClick = {}
                )
                HorizontalDivider()
            }
        }
    }
}


@PreviewLightDark
@Composable
private fun CoinListScreenPreview () {
    CryptoTrackerTheme {
        CoinListScreen(
            state = CoinListState (
                coins = (1..20).map {
                    previewCoinUiModel.copy(id = it.toString())
                }
            )
        )
    }
}

@PreviewLightDark
@Composable
private fun CoinListScreenPreview2 () {
    CryptoTrackerTheme {
        CoinListScreen(
            state = CoinListState (
                isLoading = true,
                coins = listOf(previewCoinUiModel)
            )
        )
    }
}