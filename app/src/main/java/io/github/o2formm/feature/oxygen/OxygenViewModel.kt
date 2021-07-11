package io.github.o2formm.feature.oxygen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.o2formm.domain.sheet.model.ServiceTypeConstants
import io.github.o2formm.domain.sheet.usecase.GetServicesByType
import io.github.o2formm.helper.asyncviewstate.AsyncViewStateLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
class OxygenViewModel constructor(private val getServicesByType: GetServicesByType) : ViewModel() {


  val oxygenServiceLiveData = AsyncViewStateLiveData<List<OxygenViewItem>>()

  private val baseOxygenServices = mutableListOf<OxygenViewItem>()

  fun getAllOxygenServices() {
    viewModelScope.launch {
      oxygenServiceLiveData.postLoading()

      val result = runCatching {

        val oxygenService = getServicesByType.execute(ServiceTypeConstants.OXYGEN).map { item ->
          OxygenViewItem(
            nameEn = item.name,
            nameMm = item.nameMM ?: "-",
            addressEn = item.address ?: "-",
            addressMm = item.addressMM ?: "-",
            townshipEn = item.township,
            townshipMm = item.townshipMM ?: "-",
            stateRegionEn = item.stateRegion,
            stateRegionMm = item.stateRegionMM ?: "-",
            location = item.latLong ?: "-",
            remark = item.remark ?: "-",
            link = item.url ?: "-"
          )
        }

        baseOxygenServices.clear()
        baseOxygenServices.addAll(oxygenService)

        oxygenServiceLiveData.postSuccess(baseOxygenServices)

      }

      result.exceptionOrNull()?.let { e ->
        Timber.e(e)
        oxygenServiceLiveData.postError(e, e.localizedMessage)
      }

    }

  }

  fun search(keyword: String) {
    viewModelScope.launch {


      if (keyword.isEmpty()) {
        oxygenServiceLiveData.postSuccess(baseOxygenServices)

        Timber.e("empty")
      } else {

        val filteredOxygenServices = baseOxygenServices.filter { item ->
            (item.nameEn ?: "").contains(keyword) || (item.nameMm
              ?: "").contains(keyword) || (item.addressEn
              ?: "").contains(keyword) || (item.addressMm ?: "").contains(keyword)
          }

        oxygenServiceLiveData.postSuccess(filteredOxygenServices)

      }

    }
  }
}