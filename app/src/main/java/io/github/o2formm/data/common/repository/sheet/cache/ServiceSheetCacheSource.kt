package io.github.o2formm.data.common.repository.sheet.cache

import io.github.o2formm.data.remote.entity.ServiceRemoteEntity
import io.github.o2formm.domain.sheet.model.Service

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
interface ServiceSheetCacheSource {

  suspend fun insertOrReplaceService(list: List<ServiceRemoteEntity>)

  suspend fun getAllServices(): List<Service>

  suspend fun deleteAllServices()
}