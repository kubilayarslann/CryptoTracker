package com.karslan.cryptotracker.crypto.presentation.coin_detail.components

import android.widget.Space
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.karslan.cryptotracker.R
import com.karslan.cryptotracker.ui.theme.CryptoTrackerTheme

@Composable
fun InfoCard(
    modifier: Modifier = Modifier,
    title: String,
    formatedText: String,
    icon: ImageVector,
    contentColor: Color = MaterialTheme.colorScheme.onSurface
) {

    Card(
        modifier = modifier
            .padding(8.dp)
            .shadow(
                elevation = 15.dp,
                shape = RectangleShape,
                ambientColor = MaterialTheme.colorScheme.primary,
                spotColor = MaterialTheme.colorScheme.primary
            ),
        shape = RectangleShape,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = contentColor
        )
    ) {

        AnimatedContent(
            targetState = icon,
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            label = "IconAnimation"
        ) { icon ->
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier
                    .size(75.dp)
                    .padding(top = 16.dp),
                tint = contentColor
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        AnimatedContent(
            targetState = formatedText,
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            label = "ValueAnimation"
        ) { formatedText ->
            Text(
                text = formatedText,
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                color = contentColor

            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = title,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            color = contentColor
            )

    }

}


@PreviewLightDark
@Composable
private fun InfoCardPreview() {
    CryptoTrackerTheme {
        InfoCard(
            title = "Price",
            formatedText = "$ 62828.15",
            icon = ImageVector.vectorResource(R.drawable.dollar)
        )
    }
}