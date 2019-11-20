package dev.moataz.flickrim.model.repository.remote.api

import com.google.gson.GsonBuilder
//import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dev.moataz.flickrim.application.Consts.API.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val instance: ApiService by lazy {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(ApiInterceptor())
            .build()

        val gson = GsonBuilder().setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))

            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
         retrofit.create(ApiService::class.java)
    }

}