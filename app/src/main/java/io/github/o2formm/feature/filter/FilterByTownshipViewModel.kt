package io.github.o2formm.feature.filter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
        }

        townshipsLiveData.postSuccess(townships)

      }

      result.exceptionOrNull()?.let { e ->
        Timber.e(e)
        townshipsLiveData.postError(e, e.localizedMessage)
      }
    }
  }
}