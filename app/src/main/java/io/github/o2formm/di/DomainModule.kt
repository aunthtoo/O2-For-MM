package io.github.o2formm.di

import io.github.o2formm.domain.sheet.usecase.GetAllServiceSheetAndInsertToLocal
import io.github.o2formm.domain.sheet.usecase.GetAllServices
import org.koin.dsl.module

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/

val DomainModule = module {
  single { GetAllServiceSheetAndInsertToLocal(get()) }

  single { GetAllServices(get()) }
}