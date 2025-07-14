package com.nakersolutionid.suitmedia.di

import com.nakersolutionid.suitmedia.BuildConfig
import com.nakersolutionid.suitmedia.data.UserRepository
import com.nakersolutionid.suitmedia.data.remote.network.ApiServices
import com.nakersolutionid.suitmedia.ui.screen.palindrome.PalindromeViewModel
import com.nakersolutionid.suitmedia.ui.screen.userselect.UserSelectViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single<OkHttpClient> {
        val loggingInterceptor = if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.HOSTNAME)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }
    single<ApiServices> {
        get<Retrofit>().create(ApiServices::class.java)
    }
}

val repositoryModule = module {
    single { UserRepository(get()) }
}

val viewModelModule = module {
    viewModel { UserSelectViewModel(get()) }
    viewModel { PalindromeViewModel() }
}

val previewModule = module {
    viewModel { PalindromeViewModel() }
}