package io.github.o2formm.domain.sheet.repository

import io.github.o2formm.domain.sheet.model.Service

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
interface ServiceSheetRepository {

  suspend fun getAllDataSheetAndInsertToLocal()

  suspend fun getAllServicesFromLocal(): List<Service>
}