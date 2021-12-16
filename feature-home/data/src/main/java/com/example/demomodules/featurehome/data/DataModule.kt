package com.example.demomodules.featurehome.data

import com.example.demomodules.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {
  @Binds
  @Singleton
  fun productRepository(impl: ProductRepositoryImpl): ProductRepository
}
