package io.github.o2formm.data.common.repository.sheet

import io.github.o2formm.data.common.repository.sheet.cache.ServiceSheetCacheSource
import io.github.o2formm.data.common.repository.sheet.remote.ServiceSheetRemoteSource
import io.github.o2formm.data.remote.entity.ServiceRemoteEntity
import io.github.o2formm.domain.sheet.model.Service
import io.github.o2formm.domain.sheet.model.ServiceId
import io.github.o2formm.domain.sheet.model.ServiceType
import io.github.o2formm.domain.sheet.model.Township
import io.github.o2formm.domain.sheet.repository.ServiceSheetRepository

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
class ServiceSheetRepositoryImpl constructor(
  private val serviceSheetRemoteSource: ServiceSheetRemoteSource,
  private val serviceSheetCacheSource: ServiceSheetCacheSource
) :
  ServiceSheetRepository {

  override suspend fun getAllDataSheetAndInsertToLocal() {
    //service
    var services = serviceSheetRemoteSource.getAllServices()
      .filter { item ->
        (item.service ?: "").equals("testing", ignoreCase = true).not()
      }

    services = services.filter { item ->
      (item.nameMM.isNullOrEmpty() && item.addressMM.isNullOrEmpty() && item.townshipMM.isNullOrEmpty() && item.stateRegionMM.isNullOrEmpty() && item.phone1.isNullOrEmpty() && item.phone2.isNullOrEmpty() && item.phone3.isNullOrEmpty() && item.phone4.isNullOrEmpty() && item.phone5.isNullOrEmpty()).not()
    }

    //delete all service from local before inserting
    serviceSheetCacheSource.deleteAllServices()
    serviceSheetCacheSource.insertOrReplaceService(services)

    //service type
    val servicesType = serviceSheetRemoteSource.getAllServicesType()
      .filter { item -> (item.type ?: "").equals("testing", ignoreCase = true).not() }
    //delete all service type from local before inserting
    serviceSheetCacheSource.deleteAllServicesType()
    serviceSheetCacheSource.insertOrReplaceServiceType(servicesType)

    //township
    val townships =
      serviceSheetRemoteSource.getAllTownships().filter {
        it.townnameMM.isNullOrEmpty().not() && (it.townnameMM ?: "").equals(
          "online",
          ignoreCase = true
        ).not()
      }
    //delete all townships data from local befor inserting
    serviceSheetCacheSource.deleteAllTownships()
    serviceSheetCacheSource.insertOrReplaceTownship(townships)
  }

  override suspend fun getAllServicesFromLocal(): List<Service> {
    return serviceSheetCacheSource.getAllServices()
  }

  override suspend fun getAllServiceTypeFromLocal(): List<ServiceType> {
    return serviceSheetCacheSource.getAllServicesType()
  }

  override suspend fun getServicesByType(type: String): List<Service> {
    return serviceSheetCacheSource.getServiceByType(type = type)
  }

  override suspend fun getServiceById(id: ServiceId): Service {
    return serviceSheetCacheSource.getServiceById(id)
  }

  override suspend fun getServicesByTownshipNameMMAndServiceType(
    serviceType: ServiceType,
    townshipNameMM: String
  ): List<Service> {
    return serviceSheetCacheSource.getServicesByTownshipNameMMAndServiceType(
      townshipNameMM = townshipNameMM,
      serviceType = serviceType
    )
  }

  override suspend fun getAllTownshipsFromLocal(): List<Township> {
    return serviceSheetCacheSource.getAllTownships()
  }
}