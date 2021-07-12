package io.github.o2formm.feature.callcenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.o2formm.domain.sheet.model.ServiceTypeConstants
import io.github.o2formm.domain.sheet.usecase.GetServicesByType
import io.github.o2formm.helper.asyncviewstate.AsyncViewStateLiveData
import kotlinx.coroutines.launch
import timber.log.Timber

/**
Created By Aunt Htoo Aung on 12/07/2021.
 **/
class CallCenterViewModel constructor(private val getServicesByType: GetServicesByType) :
  ViewModel() {

  val callCenterLiveData = AsyncViewStateLiveData<List<CallCenterViewItem>>()

  fun getCallCenter() {
    viewModelScope.launch {

      callCenterLiveData.postLoading()

      val result = runCatching {

        val callCenter = getServicesByType.execute(ServiceTypeConstants.CALL_CENTER).map { item ->
          CallCenterViewItem(
            serviceId = item.id,
            name = item.nameMM ?: item.name ?: "-",
            phones = item.phones.filter { it.isNullOrEmpty().not() },
            remark = item.remark ?: "-"
          )
        }

        callCenterLiveData.postSuccess(callCenter)
      }

      result.exceptionOrNull()?.let { e ->
        Timber.e(e)
        callCenterLiveData.postError(e, e.localizedMessage)
      }
    }
  }

}