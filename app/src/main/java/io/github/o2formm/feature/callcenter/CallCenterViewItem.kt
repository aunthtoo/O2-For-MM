package io.github.o2formm.feature.callcenter

import io.github.o2formm.domain.sheet.model.ServiceId

/**
Created By Aunt Htoo Aung on 12/07/2021.
 **/
data class CallCenterViewItem(
  val serviceId: ServiceId,
  val name: String,
  val phones: List<String>,
  val remark: String
)