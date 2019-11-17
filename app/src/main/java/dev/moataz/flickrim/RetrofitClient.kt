package dev.moataz.flickrim

import android.util.Log
import com.google.gson.GsonBuilder
//import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {


    //Callback
//    private fun getOauthKeys() = OauthKeys.getInstance(
//        consumerKey = "f2820dd88b1b11070df6ce1387c63553",
//        consumerSecret = "107536027d586f39"
//
//    )

    val instance: ApiService by lazy {


        val requestInterceptor = Interceptor { chain ->

            val url = chain.request()
                .url()

            Log.d("myApi", "url" + url.toString())

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            chain.proceed(request)
        }


        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(Oauth1SigningInterceptor())
            .build()

        val gson = GsonBuilder().setLenient()
//            .registerTypeAdapter(String::class.java,LenientMoshiConverter())
            .create()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://www.flickr.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()


         retrofit.create(ApiService::class.java)
    }

}