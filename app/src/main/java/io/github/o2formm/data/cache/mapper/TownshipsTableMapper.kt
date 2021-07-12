package io.github.o2formm.data.cache.mapper

import io.github.o2formm.TownshipTable
import io.github.o2formm.domain.sheet.model.Township
import io.github.o2formm.domain.sheet.model.TownshipId

/**
Created By Aunt Htoo Aung on 12/07/2021.
 **/
object TownshipsTableMapper {

  fun map(item: TownshipTable): Township {
    return Township(
      id = TownshipId(id = item.id.toInt()),
      townNameMM = item.townNameMM ?: "-",
      townNameEN = item.townNameEN ?: "-",
      latitude = item.latitude ?: "0",
      longitude = item.longitude ?: "0"
    )
  }
}