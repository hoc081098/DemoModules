package com.example.demomodules.featurehome.data

import com.example.demomodules.AppCoroutineDispatchers
import com.example.demomodules.domain.model.Product
import com.example.demomodules.domain.repository.ProductRepository
import com.example.demomodules.featurehome.dataremote.ProductApiService
import com.example.demomodules.featurehome.dataremote.toProduct
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class ProductRepositoryImpl @Inject constructor(
  private val productApiService: ProductApiService,
  private val appCoroutineDispatchers: AppCoroutineDispatchers,
) : ProductRepository {
  override suspend fun getProducts(): List<Product> = withContext(appCoroutineDispatchers.io) {
    productApiService.getProducts().map { it.toProduct() }
  }
}
