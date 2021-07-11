package io.github.o2formm.domain.sheet.repository

import io.github.o2formm.domain.sheet.model.Service
import io.github.o2formm.domain.sheet.model.ServiceId
import io.github.o2formm.domain.sheet.model.ServiceType

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
interface ServiceSheetRepository {

  suspend fun getAllDataSheetAndInsertToLocal()

  suspend fun getAllServicesFromLocal(): List<Service>

  suspend fun getAllServiceTypeFromLocal(): List<ServiceType>

  suspend fun getServicesByType(type: String): List<Service>

  suspend fun getServiceById(id: ServiceId): Service
}