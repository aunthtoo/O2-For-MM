package io.github.o2formm.data.remote.source

import io.github.o2formm.data.common.repository.sheet.remote.ServiceSheetRemoteSource
import io.github.o2formm.data.remote.api.SheetService
import io.github.o2formm.data.remote.entity.ServiceRemoteEntity
import io.github.o2formm.data.remote.entity.ServiceTypeRemoteEntity
import io.github.o2formm.data.remote.entity.TownshipRemoteEntity

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
class ServiceSheetRemoteSourceImpl constructor(private val sheetService: SheetService) :
  ServiceSheetRemoteSource {

  override suspend fun getAllServices(): List<ServiceRemoteEntity> {
    return sheetService.getServices()
  }

  override suspend fun getAllServicesType(): List<ServiceTypeRemoteEntity> {
    return sheetService.getServicesType()
  }

  override suspend fun getAllTownships(): List<TownshipRemoteEntity> {
    return sheetService.getTownships()
  }
}