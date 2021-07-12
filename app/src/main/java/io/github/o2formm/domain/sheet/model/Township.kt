package io.github.o2formm.domain.sheet.model

/**
Created By Aunt Htoo Aung on 12/07/2021.
 **/
data class Township(
  val id: TownshipId,
  val townshipNameMM: String,
  val townshipNameEN: String,
  val latitude: String,
  val longitude: String
)

data class TownshipId(val id: Int)