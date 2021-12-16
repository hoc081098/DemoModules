package com.example.demomodules.domain.repository

import com.example.demomodules.domain.model.Product

interface ProductRepository {
  suspend fun getProducts(): List<Product>
}
