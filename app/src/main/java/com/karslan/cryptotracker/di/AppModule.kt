package com.karslan.cryptotracker.di

import com.karslan.cryptotracker.core.data.network.HttpClientFactory
import com.karslan.cryptotracker.crypto.data.network.RemoteCoinDataSource
import com.karslan.cryptotracker.crypto.domain.CoinDataSource
import com.karslan.cryptotracker.crypto.presentation.coin_list.CoinListViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { HttpClientFactory.create(CIO.create()) }
    singleOf(::RemoteCoinDataSource).bind<CoinDataSource>()

    viewModelOf(::CoinListViewModel)
}