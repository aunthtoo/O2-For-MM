package io.github.o2formm.feature.filter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.o2formm.domain.sheet.model.TownshipId
import io.github.o2formm.domain.sheet.usecase.GetAllTownships
import io.github.o2formm.helper.asyncviewstate.AsyncViewStateLiveData
import kotlinx.coroutines.launch
import timber.log.Timber

/**
Created By Aunt Htoo Aung on 12/07/2021.
 **/
class FilterByTownshipViewModel constructor(private val getAllTownships: GetAllTownships) :
  ViewModel() {

  var selectedTownshipNameMM: String? = null

  val townshipsLiveData = AsyncViewStateLiveData<List<TownshipViewItem>>()

  private val baseTownshipsList = mutableListOf<TownshipViewItem>()
  private val workingTownshipsList = mutableListOf<TownshipViewItem>()

  fun getAllTownship() {
    viewModelScope.launch {
      townshipsLiveData.postLoading()

      val result = runCatching {
        val townships = getAllTownships.execute(Unit).map { item ->
          TownshipViewItem(
            id = item.id,
            townshipNameMM = item.townNameMM,
            townshipNameEN = item.townNameEN
          )
        }.sortedBy { it.townshipNameMM }

        baseTownshipsList.clear()

        val allViewItem =
          TownshipViewItem(
            id = TownshipId(-1),
            townshipNameMM = "All",
            townshipNameEN = "All",
            isSelect = true
          )
        baseTownshipsList.add(0, allViewItem)

        baseTownshipsList.addAll(townships)
        workingTownshipsList.addAll(baseTownshipsList)
        townshipsLiveData.postSuccess(workingTownshipsList)
      }

      result.exceptionOrNull()?.let { e ->
        Timber.e(e)
        townshipsLiveData.postError(e, e.localizedMessage)
      }
    }
  }

  fun searchTownship(keyword: String) {

    viewModelScope.launch {
      workingTownshipsList.clear()

      if (keyword.isEmpty()) {
        workingTownshipsList.addAll(baseTownshipsList)

        townshipsLiveData.postSuccess(workingTownshipsList)
      } else {

        val filteredTownship = baseTownshipsList.filter { item ->
          item.townshipNameMM.contains(keyword, ignoreCase = true) || item.townshipNameEN.contains(
            keyword,
            ignoreCase = true
          )
        }
        workingTownshipsList.addAll(filteredTownship)
        townshipsLiveData.postSuccess(workingTownshipsList)
      }
    }
  }

  fun selectTownship(position: Int, item: TownshipViewItem) {
    viewModelScope.launch {

      Timber.e("select")
      workingTownshipsList.find { it.id.id == item.id.id }?.isSelect = true
      workingTownshipsList.forEach { each ->
        if (each.id.id != item.id.id)
          each.isSelect = false
      }

      baseTownshipsList.find { it.id.id == item.id.id }?.isSelect = true
      baseTownshipsList.forEach { each ->
        if (each.id.id != item.id.id)
          each.isSelect = false
      }
      townshipsLiveData.postSuccess(workingTownshipsList)
    }
  }
}