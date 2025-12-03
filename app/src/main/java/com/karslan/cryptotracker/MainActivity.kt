package com.karslan.cryptotracker

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.karslan.cryptotracker.core.domain.util.CryptoTrackerNetworkError
import com.karslan.cryptotracker.core.presentation.util.ObserveAsEvents
import com.karslan.cryptotracker.core.presentation.util.toString
import com.karslan.cryptotracker.crypto.presentation.coin_list.CoinListEvents
import com.karslan.cryptotracker.crypto.presentation.coin_list.CoinListScreen
import com.karslan.cryptotracker.crypto.presentation.coin_list.CoinListViewModel
import com.karslan.cryptotracker.ui.theme.CryptoTrackerTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel = koinViewModel<CoinListViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    val context = LocalContext.current
                    ObserveAsEvents(events = viewModel.events) { event ->
                        when(event) {
                            is CoinListEvents.Error -> {
                                Toast.makeText(
                                    context,
                                    event.error.toString(context),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }

                    CoinListScreen(
                        modifier = Modifier.padding(innerPadding),
                        state = state
                    )

                }
            }
        }
    }
}