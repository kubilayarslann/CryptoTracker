package com.karslan.cryptotracker.crypto.presentation.coin_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.karslan.cryptotracker.crypto.domain.CoinModel
import com.karslan.cryptotracker.crypto.presentation.models.CoinUiModel
import com.karslan.cryptotracker.crypto.presentation.models.toCoinUiModel
import com.karslan.cryptotracker.ui.theme.CryptoTrackerTheme
import com.karslan.cryptotracker.ui.theme.Typography

@Composable
fun CoinListItem(
    modifier: Modifier = Modifier,
    coinUIModel: CoinUiModel,
    onClick: () -> Unit
) {

    val textColor = if (isSystemInDarkTheme()) Color.White else Color.Black

    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon (
            modifier = Modifier.size(85.dp),
            imageVector = ImageVector.vectorResource(id = coinUIModel.iconRes),
            contentDescription = coinUIModel.name,
            tint = MaterialTheme.colorScheme.primary
        )
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                modifier = Modifier,
                text = coinUIModel.name,
                color =  textColor,
                style = Typography.headlineLarge
            )

            Text(
                modifier = Modifier,
                text = coinUIModel.name,
                color =  textColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light
            )
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                modifier = Modifier,
                text = "$ ${coinUIModel.priceUsd.formatted}",
                color =  textColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer (modifier = Modifier
                .height(8.dp))

            PriceChangeLayer(
                change = coinUIModel.changePercentage24Hr
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun CoinListItemPreview () {
    CryptoTrackerTheme {
        CoinListItem(
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            coinUIModel = previewCoinUiModel,
            onClick = {}
        )
    }
}

internal val previewCoinUiModel = CoinModel(
    id = "1",
    rank = 1,
    name = "BitCoin",
    symbol = "BTC",
    marketCapUsd = 1241273958896.75,
    priceUsd = 62828.15,
    changePercentage24Hr = -0.1
).toCoinUiModel()