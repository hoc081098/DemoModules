package com.example.demomodules.domain.model

import java.net.URL

data class Product(
  val id: Id,
  val title: Title,
  val price: Price,
  val description: Description,
  val category: Category,
  val image: Image,
  val rating: Rating?
) {
  @JvmInline
  value class Id(val value: ULong)

  @JvmInline
  value class Title(val value: String) {
    init {
      require(value.isNotBlank()) { "Title is blank" }
    }
  }

  @JvmInline
  value class Price(val value: Double) {
    init {
      require(value > 0) { "Price is not positive" }
    }
  }

  @JvmInline
  value class Description(val value: String) {
    init {
      require(value.isNotBlank()) { "Description is blank" }
    }
  }

  @JvmInline
  value class Category(val value: String) {
    init {
      require(value.isNotBlank()) { "Category is blank" }
    }
  }

  @JvmInline
  value class Image(val value: String) {
    init {
      require(runCatching { URL(value); true }.getOrDefault(false)) {
        "Image url is invalid"
      }
    }
  }

  data class Rating(
    val rate: Double,
    val count: UInt,
  ) {
    init {
      require(!rate.isNaN()) { "Rate is Nan" }
      require(rate >= 0) { "Rate is negative" }
    }
  }
}
