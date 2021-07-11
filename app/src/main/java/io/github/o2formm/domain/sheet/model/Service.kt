package io.github.o2formm.domain.sheet.model

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
data class Service(
  val id: ServiceId,
  val service: String?,
  val name: String?,
  val nameMM: String?,
  val address: String?,
  val addressMM: String?,
  val phones: List<String>,
  val townshipMM: String?,
  val township: String?,
  val stateRegionMM: String?,
  val stateRegion: String?,
  val latLong: String?,
  val remark: String?,
  val url: String?,
  val serviceIfOther: String?
)

data class ServiceId(val id: Int)
