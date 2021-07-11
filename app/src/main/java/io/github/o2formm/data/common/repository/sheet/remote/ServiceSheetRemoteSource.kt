package io.github.o2formm.data.common.repository.sheet.remote

import io.github.o2formm.data.remote.entity.ServiceRemoteEntity
import io.github.o2formm.data.remote.entity.ServiceTypeRemoteEntity

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
interface ServiceSheetRemoteSource {

  suspend fun getAllServices(): List<ServiceRemoteEntity>

  suspend fun getAllServicesType(): List<ServiceTypeRemoteEntity>
}