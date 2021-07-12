package io.github.o2formm.feature.oxygen

import io.github.o2formm.domain.sheet.model.ServiceId

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
data class OxygenViewItem(
  val serviceId: ServiceId,
  val nameEn: String?,
  val nameMm: String,
  val addressEn: String?,
  val addressMm: String,
  val townshipEn: String?,
  val townshipMm: String,
  val stateRegionEn: String?,
  val stateRegionMm: String,
  val location: String,
  val remark: String,
  val link: String
)
