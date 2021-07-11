package io.github.o2formm.data.remote.api

import com.github.theapache64.retrosheet.core.Read
import io.github.o2formm.data.remote.entity.ServiceRemoteEntity
import retrofit2.http.GET

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
interface SheetService {

  @Read("SELECT *")
  @GET("Services")
  suspend fun getServices(): List<ServiceRemoteEntity>

}