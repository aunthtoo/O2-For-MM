package io.github.o2formm.data.remote.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.github.o2formm.data.remote.RemoteConstants

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
@JsonClass(generateAdapter = true)
data class ServiceRemoteEntity(
  @Json(name = RemoteConstants.service)
  val service: String?,
  @Json(name = RemoteConstants.name)
  val name: String?,
  @Json(name = RemoteConstants.nameMM)
  val nameMM: String?,
  @Json(name = RemoteConstants.address)
  val address: String?,
  @Json(name = RemoteConstants.addressMM)
  val addressMM: String?,
  @Json(name = RemoteConstants.phone1)
  val phone1: String?,
  @Json(name = RemoteConstants.phone2)
  val phone2: String?,
  @Json(name = RemoteConstants.phone3)
  val phone3: String?,
  @Json(name = RemoteConstants.phone4)
  val phone4: String?,
  @Json(name = RemoteConstants.phone5)
  val phone5: String?,
  @Json(name = RemoteConstants.townshipMM)
  val townshipMM: String?,
  @Json(name = RemoteConstants.township)
  val township: String?,
  @Json(name = RemoteConstants.stateRegionMM)
  val stateRegionMM: String?,
  @Json(name = RemoteConstants.stateRegion)
  val stateRegion: String?,
  @Json(name = RemoteConstants.latLong)
  val latLong: String?,
  @Json(name = RemoteConstants.remark)
  val remark: String?,
  @Json(name = RemoteConstants.url)
  val url: String?,
  @Json(name = RemoteConstants.serviceIfOthers)
  val serviceIfOther: String?
)
