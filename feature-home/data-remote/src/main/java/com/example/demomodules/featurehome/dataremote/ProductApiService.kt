package com.example.demomodules.featurehome.dataremote

import com.example.demomodules.featurehome.dataremote.response.ProductResponse
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET

interface ProductApiService {
  @GET("products")
  suspend fun getProducts(): List<ProductResponse>

  companion object Factory {
    operator fun invoke(retrofit: Retrofit): ProductApiService = retrofit.create()
  }
}
