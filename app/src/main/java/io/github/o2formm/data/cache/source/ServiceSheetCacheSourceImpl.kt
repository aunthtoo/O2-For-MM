package io.github.o2formm.data.cache.source

import io.github.o2formm.O2ForMMDb
import io.github.o2formm.ServicesTable
import io.github.o2formm.data.cache.mapper.ServicesTableMapper
import io.github.o2formm.data.cache.mapper.ServicesTypeTableMapper
import io.github.o2formm.data.cache.mapper.TownshipsTableMapper
import io.github.o2formm.data.common.repository.sheet.cache.ServiceSheetCacheSource
import io.github.o2formm.data.remote.entity.ServiceRemoteEntity
import io.github.o2formm.data.remote.entity.ServiceTypeRemoteEntity
import io.github.o2formm.data.remote.entity.TownshipRemoteEntity
import io.github.o2formm.domain.sheet.model.*

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
class ServiceSheetCacheSourceImpl constructor(private val database: O2ForMMDb) :
  ServiceSheetCacheSource {

  override suspend fun insertOrReplaceService(list: List<ServiceRemoteEntity>) {
    database.transaction {

      for (i in list.indices) {
        val it = list[i]

        val phones = listOf(
          it.phone1 ?: "",
          it.phone2 ?: "",
          it.phone3 ?: "",
          it.phone4 ?: "",
          it.phone5 ?: ""
        )

        database.servicesQueries.insertOrReplace(
          id = (i + 1).toLong(),
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

  override suspend fun getServiceByType(type: String): List<Service> {
    return database.servicesQueries.selectByService(service = type).executeAsList()
      .map(ServicesTableMapper::map)
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

  override suspend fun getServiceById(id: ServiceId): Service {
    return ServicesTableMapper.map(
      database.servicesQueries.selectById(id = id.id.toLong()).executeAsOne()
    )
  }

  override suspend fun insertOrReplaceTownship(list: List<TownshipRemoteEntity>) {
    database.transaction {
      for (i in list.indices) {

        val item = list[i]

        database.townshipsQueries.insertOrReplace(
          id = (i + 1).toLong(),
          townNameMM = item.townnameMM,
          townNameEN = item.townnameEN,
          nameEN = item.nameEN,
          stateRegionNameEN = item.stateRegionNameEN,
          stateRegionNameMM = item.stateRegionNameMM,
          districtPCode = item.districtPCode,
          districtNameEN = item.districtNameEN,
          districtNameMM = item.districtNameMM,
          townshipPCode = item.townshipPCode,
          townPCode = item.townPCode,
          latitude = item.latitude,
          longitude = item.longitude,
          latlong = item.latLong,
          source = item.source,
          gadTownStatus = item.gadTownStatus,
          mimuTownMappingStatus = item.mimuTownMappingStatus,
          changeType = item.changeType,
          remark = item.remark
        )
      }
    }
  }

  override suspend fun getServicesByTownshipNameMMAndServiceType(
    townshipNameMM: String,
    serviceType: ServiceType
  ): List<Service> {
    return database.servicesQueries.selectByTownNameMMAndServiceType(
      townshipNameMM = townshipNameMM,
      service = serviceType.type
    ).executeAsList().map(ServicesTableMapper::map)
  }

  override suspend fun deleteAllTownships() {
    database.townshipsQueries.deleteAll()
  }

  override suspend fun getAllTownships(): List<Township> {
    return database.townshipsQueries.selectAll().executeAsList().map(TownshipsTableMapper::map)
  }

  override suspend fun getTownshipById(townshipId: TownshipId): Township {
    return TownshipsTableMapper.map(
      database.townshipsQueries.selectById(townshipId.id.toLong()).executeAsOne()
    )
  }
}