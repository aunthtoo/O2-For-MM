package io.github.o2formm.feature.oxygen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.o2formm.domain.sheet.model.ServiceId
import io.github.o2formm.domain.sheet.usecase.GetServiceById
import io.github.o2formm.helper.asyncviewstate.AsyncViewStateLiveData
import kotlinx.coroutines.launch
import timber.log.Timber

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
class OxygenDetailViewModel constructor(private val getServiceById: GetServiceById) :
  ViewModel() {

  val oxygenServiceLiveData = AsyncViewStateLiveData<OxygenDetailViewItem>()

  fun getOxygenService(id: ServiceId) {
    viewModelScope.launch {
      oxygenServiceLiveData.postLoading()

      val result = runCatching {
        val oxygenService = getServiceById.execute(id)

        oxygenServiceLiveData.postSuccess(
          OxygenDetailViewItem(
            name = oxygenService.nameMM ?: "-",
            fullAddress = oxygenService.addressMM ?: "-",
            township = oxygenService.townshipMM ?: "",
            stateRegion = oxygenService.stateRegionMM ?: "-",
            phones = oxygenService.phones.filter { it.isNotEmpty() },
            source = oxygenService.url ?: "-"
          )
        )
      }

      result.exceptionOrNull()?.let { e ->
        Timber.e(e)
        oxygenServiceLiveData.postError(e, e.localizedMessage)
      }
    }
  }
}