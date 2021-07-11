package io.github.o2formm.data.cache.mapper

import io.github.o2formm.ServicesTypeTable
import io.github.o2formm.domain.sheet.model.ServiceType

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
object ServicesTypeTableMapper {

  fun map(item: ServicesTypeTable): ServiceType {
    return ServiceType(type = item.type ?: "")
  }
}