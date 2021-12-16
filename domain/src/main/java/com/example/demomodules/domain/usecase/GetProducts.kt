package com.example.demomodules.domain.usecase

import com.example.demomodules.domain.model.Product
import com.example.demomodules.domain.repository.ProductRepository
import javax.inject.Inject

class GetProducts @Inject constructor(private val repository: ProductRepository) {
  suspend operator fun invoke(): List<Product> = repository.getProducts()
}
