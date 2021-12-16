package com.example.demomodules.featurehome.dataremote

import com.example.demomodules.domain.model.Product
import com.example.demomodules.domain.model.Product.Category
import com.example.demomodules.domain.model.Product.Description
import com.example.demomodules.domain.model.Product.Id
import com.example.demomodules.domain.model.Product.Image
import com.example.demomodules.domain.model.Product.Price
import com.example.demomodules.domain.model.Product.Rating
import com.example.demomodules.domain.model.Product.Title
import com.example.demomodules.featurehome.dataremote.response.ProductResponse

fun ProductResponse.toProduct(): Product = Product(
  id = Id(id.toULong()),
  title = Title(title),
  price = Price(price),
  description = Description(description),
  category = Category(category),
  image = Image(image),
  rating = Rating(
    rating.rate,
    rating.count.toUInt(),
  ),
)
