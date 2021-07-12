package io.github.o2formm.data.remote.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.github.o2formm.data.remote.RemoteConstants

/**
Created By Aunt Htoo Aung on 12/07/2021.
 **/
@JsonClass(generateAdapter = true)
data class TownshipRemoteEntity(
  @Json(name = RemoteConstants.townNameMM) val townnameMM: String?,
  @Json(name = RemoteConstants.townNameEN) val townnameEN: String?,
  @Json(name = RemoteConstants.nameEN) val nameEN: String?,
  @Json(name = RemoteConstants.stateRegionNameMM) val stateRegionNameMM: String?,
  @Json(name = RemoteConstants.stateRegionNameEN) val stateRegionNameEN: String?,
  @Json(name = RemoteConstants.districtPCode) val districtPCode: String?,
  @Json(name = RemoteConstants.districtNameEng) val districtNameEN: String?,
  @Json(name = RemoteConstants.districtNameMM) val districtNameMM: String?,
  @Json(name = RemoteConstants.townshipPCode) val townshipPCode: String?,
  @Json(name = RemoteConstants.townPCode) val townPCode: String?,
  @Json(name = RemoteConstants.latitude) val latitude: String?,
  @Json(name = RemoteConstants.longitude) val longitude: String?,
  @Json(name = RemoteConstants.latLong) val latLong: String?,
  @Json(name = RemoteConstants.source) val source: String?,
  @Json(name = RemoteConstants.gadTownStatus) val gadTownStatus: String?,
  @Json(name = RemoteConstants.mimuTownMappingStatus) val mimuTownMappingStatus: String?,
  @Json(name = RemoteConstants.changeType) val changeType: String?,
  @Json(name = RemoteConstants.Remark) val remark: String?
)
