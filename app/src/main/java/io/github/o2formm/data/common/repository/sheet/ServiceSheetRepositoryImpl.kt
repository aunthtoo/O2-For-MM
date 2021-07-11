package io.github.o2formm.data.common.repository.sheet

import io.github.o2formm.data.common.repository.sheet.cache.ServiceSheetCacheSource
import io.github.o2formm.data.common.repository.sheet.remote.ServiceSheetRemoteSource
import io.github.o2formm.data.remote.entity.ServiceRemoteEntity
import io.github.o2formm.domain.sheet.model.Service
import io.github.o2formm.domain.sheet.repository.ServiceSheetRepository

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
class ServiceSheetRepositoryImpl constructor(
  private val serviceSheetRemoteSource: ServiceSheetRemoteSource,
  private val serviceSheetCacheSource: ServiceSheetCacheSource
) :
  ServiceSheetRepository {

  override suspend fun getAllServicesAndInsertToLocal() {
    val services = serviceSheetRemoteSource.getAllServices()

    //delete all service from local before inserting
    serviceSheetCacheSource.deleteAllServices()

    serviceSheetCacheSource.insertOrReplaceService(services)
  }

  override suspend fun getAllServicesFromLocal(): List<Service> {
    return serviceSheetCacheSource.getAllServices()
  }
}