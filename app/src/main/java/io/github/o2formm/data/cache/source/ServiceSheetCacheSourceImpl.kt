package io.github.o2formm.data.cache.source

import io.github.o2formm.O2ForMMDb
import io.github.o2formm.data.cache.mapper.ServicesTableMapper
import io.github.o2formm.data.cache.mapper.ServicesTypeTableMapper
import io.github.o2formm.data.common.repository.sheet.cache.ServiceSheetCacheSource
import io.github.o2formm.data.remote.entity.ServiceRemoteEntity
import io.github.o2formm.data.remote.entity.ServiceTypeRemoteEntity
import io.github.o2formm.domain.sheet.model.Service
import io.github.o2formm.domain.sheet.model.ServiceType

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
class ServiceSheetCacheSourceImpl constructor(private val database: O2ForMMDb) :
  ServiceSheetCacheSource {

  override suspend fun insertOrReplaceService(list: List<ServiceRemoteEntity>) {
    database.transaction {

      list.forEach {
        val phones = listOf(
          it.phone1 ?: "",
          it.phone2 ?: "",
          it.phone3 ?: "",
          it.phone4 ?: "",
          it.phone5 ?: ""
        )

        database.servicesQueries.insertOrReplace(
          service = it.service?.trim(),
          name = it.name,
          nameMM = it.nameMM,
          address = it.address,
          addressMM = it.addressMM,
          phones = phones,
          township = it.township,
          townshipMM = it.townshipMM,
          stateRegion = it.stateRegion,
          stateRegionMM = it.stateRegionMM,
          latLong = it.latLong,
          remark = it.remark,
          url = it.url,
          serviceIfOthers = it.serviceIfOther

        )
      }
    }
  }

  override suspend fun getAllServices(): List<Service> {
    return database.servicesQueries.selectAll().executeAsList().map(ServicesTableMapper::map)
  }

  override suspend fun deleteAllServices() {
    database.servicesQueries.deleteAll()
  }

  override suspend fun insertOrReplaceServiceType(list: List<ServiceTypeRemoteEntity>) {
    database.transaction {
      list.forEach {
        database.servicesTypeQueries.insertOrReplace(type = it.type.trim())
      }
    }
  }

  override suspend fun getAllServicesType(): List<ServiceType> {
    return database.servicesTypeQueries.selectAll().executeAsList()
      .map(ServicesTypeTableMapper::map)
  }

  override suspend fun deleteAllServicesType() {
    database.servicesTypeQueries.deleteAll()
  }
}