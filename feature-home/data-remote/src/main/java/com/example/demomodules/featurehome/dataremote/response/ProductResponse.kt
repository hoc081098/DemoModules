package com.example.demomodules.featurehome.dataremote.response

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class ProductResponse(
  @field:Json(name = "id")
  val id: Int,
  @field:Json(name = "title")
  val title: String,
  @field:Json(name = "price")
  val price: Double,
  @field:Json(name = "description")
  val description: String,
  @field:Json(name = "category")
  val category: String,
  @field:Json(name = "image")
  val image: String,
  @field:Json(name = "rating")
  val rating: Rating
) {
  @Keep
  data class Rating(
    @field:Json(name = "rate")
    val rate: Double,
    @field:Json(name = "count")
    val count: Int
  )
}
