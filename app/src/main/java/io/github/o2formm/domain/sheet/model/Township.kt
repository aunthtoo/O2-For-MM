package io.github.o2formm.domain.sheet.model

/**
Created By Aunt Htoo Aung on 12/07/2021.
 **/
data class Township(
  val id: TownshipId,
  val townNameMM: String,
  val townNameEN: String,
  val latitude: String,
  val longitude: String
)

data class TownshipId(val id: Int)