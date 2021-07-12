package io.github.o2formm.feature.filter

import io.github.o2formm.domain.sheet.model.TownshipId

/**
Created By Aunt Htoo Aung on 12/07/2021.
 **/
data class TownshipViewItem(
  val id: TownshipId,
  val townshipNameMM: String,
  val townshipNameEN: String
)