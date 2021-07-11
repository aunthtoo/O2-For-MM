package io.github.o2formm.data.cache.mapper

import io.github.o2formm.ServicesTable
import io.github.o2formm.domain.sheet.model.Service

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
object ServicesTableMapper {

  fun map(item: ServicesTable): Service {
    return Service(
      service = item.service,
      name = item.name,
      nameMM = item.nameMM,
      address = item.address,
      addressMM = item.addressMM,
      phones = item.phones ?: emptyList(),
      townshipMM = item.townshipMM,
      township = item.township,
      stateRegionMM = item.stateRegionMM,
      stateRegion = item.stateRegion,
      latLong = item.latLong,
      remark = item.remark,
      url = item.url,
      serviceIfOther = item.serviceIfOthers
    )
  }
}