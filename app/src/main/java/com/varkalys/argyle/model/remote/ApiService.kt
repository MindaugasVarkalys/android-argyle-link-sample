package com.varkalys.argyle.model.remote

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.varkalys.argyle.BuildConfig
import com.varkalys.argyle.model.remote.response.LinkItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search/link-items")
    suspend fun getSubscriptionPlans(
        @Query("q") query: String,
        @Query("limit") limit: Int = 15
    ): List<LinkItem>

    companion object {
        private const val BASE_URL = "https://api-sandbox.argyle.com/v1/"
        private const val USERNAME = "9b40eed7b1d14f16ba3abfad216167e8"
        private const val PASSWORD = "[TO BE ADDED]"

        fun create(): ApiService {
            val clientBuilder = OkHttpClient.Builder()
                .addInterceptor(BasicAuthInterceptor(USERNAME, PASSWORD))
            if (BuildConfig.DEBUG) {
                clientBuilder.addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }

            val gson =
                GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(clientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(ApiService::class.java)
        }
    }
}