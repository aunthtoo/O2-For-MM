package io.github.o2formm.di

import io.github.o2formm.domain.sheet.usecase.GetAllDataSheetAndInsertToLocal
import io.github.o2formm.domain.sheet.usecase.GetAllServices
import io.github.o2formm.domain.sheet.usecase.GetAllServicesType
import io.github.o2formm.domain.sheet.usecase.GetServicesByType
import org.koin.dsl.module

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/

val DomainModule = module {
  single { GetAllDataSheetAndInsertToLocal(get()) }

  single { GetAllServices(get()) }

  single { GetAllServicesType(get()) }

  single { GetServicesByType(get()) }
}