package io.github.o2formm.feature.oxygen.detail

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
data class OxygenDetailViewItem(
  val name: String,
  val fullAddress: String,
  val township: String,
  val stateRegion: String,
  val phones: List<String>,
  val source: String,
  val remark:String
)
