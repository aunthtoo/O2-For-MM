package io.github.o2formm.di

import io.github.o2formm.data.common.repository.sheet.ServiceSheetRepositoryImpl
import io.github.o2formm.domain.sheet.repository.ServiceSheetRepository
import org.koin.dsl.module

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
val DataModule = module {
  single<ServiceSheetRepository> { ServiceSheetRepositoryImpl(get(), get()) }
}