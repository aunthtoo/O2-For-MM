package io.github.o2formm.feature.oxygenplant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.o2formm.domain.sheet.model.ServiceTypeConstants
import io.github.o2formm.domain.sheet.usecase.GetServicesByType
import io.github.o2formm.feature.oxygen.OxygenViewItem
import io.github.o2formm.helper.asyncviewstate.AsyncViewStateLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class OxygenPlantViewModel constructor(private val getServicesByType: GetServicesByType) :
    ViewModel() {
    val oxygenServiceLiveData = AsyncViewStateLiveData<List<OxygenViewItem>>()

    private val baseOxygenServices = mutableListOf<OxygenViewItem>()

    fun getAllOxygenServices() {
        viewModelScope.launch {
            oxygenServiceLiveData.postLoading()

            val result = runCatching {

                val oxygenService =
                    getServicesByType.execute(ServiceTypeConstants.OXYGEN_PLANT).map { item ->
                        OxygenViewItem(
                            serviceId = item.id,
                            nameEn = item.name,
                            nameMm = item.nameMM ?: (item.name ?: "-"),
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

                withContext(Dispatchers.IO) {

                    val filteredOxygenServices = baseOxygenServices.filter { item ->
                        (item.nameEn ?: "").contains(keyword, ignoreCase = true) || (item.nameMm
                            ?: "").contains(keyword, ignoreCase = true) || (item.addressEn
                            ?: "").contains(keyword, ignoreCase = true) || (item.addressMm
                            ?: "").contains(keyword) || (item.townshipEn
                            ?: "").contains(keyword, ignoreCase = true) || (item.townshipMm ?: "").contains(
                            keyword,
                            ignoreCase = true
                        ) || (item.stateRegionEn ?: "").contains(
                            keyword,
                            ignoreCase = true
                        ) || (item.stateRegionMm ?: "").contains(keyword, ignoreCase = true)
                    }

                    withContext(Dispatchers.Main) {
                        oxygenServiceLiveData.postSuccess(filteredOxygenServices)
                    }
                }

            }

        }
    }
}