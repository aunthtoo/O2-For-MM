package io.github.o2formm.data.remote.api

import com.github.theapache64.retrosheet.core.Read
import io.github.o2formm.data.remote.RemoteConstants
import io.github.o2formm.data.remote.entity.ServiceRemoteEntity
import io.github.o2formm.data.remote.entity.ServiceTypeRemoteEntity
import io.github.o2formm.data.remote.entity.TownshipRemoteEntity
import retrofit2.http.GET

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
interface SheetService {

  @Read("SELECT *")
  @GET(RemoteConstants.servicesSheet)
  suspend fun getServices(): List<ServiceRemoteEntity>

  @Read("SELECT *")
  @GET(RemoteConstants.serviceTypeSheet)
  suspend fun getServicesType(): List<ServiceTypeRemoteEntity>

  @Read("SELECT *")
  @GET(RemoteConstants.townshipSheet)
  suspend fun getTownships(): List<TownshipRemoteEntity>

}