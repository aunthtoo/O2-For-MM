package io.github.o2formm.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.o2formm.domain.sheet.model.ServiceTypeConstants
import io.github.o2formm.domain.sheet.usecase.GetAllServicesType
import io.github.o2formm.helper.asyncviewstate.AsyncViewStateLiveData
import kotlinx.coroutines.launch
import timber.log.Timber

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
class MainViewModel(private val getAllServicesType: GetAllServicesType) : ViewModel() {

  val allServicesLiveData = AsyncViewStateLiveData<List<ServiceTypeViewItem>>()

  fun getAllServices() {
    viewModelScope.launch {

      allServicesLiveData.postLoading()

      val result = runCatching {

        val servicesType = getAllServicesType.execute(Unit).map { item ->

          val type = if (item.type.trim().equals(
              ServiceTypeConstants.CALL_CENTER,
              ignoreCase = true
            )
          ) "Call Center" else item.type
          ServiceTypeViewItem(type = type)
        }

        allServicesLiveData.postSuccess(servicesType)
      }

      result.exceptionOrNull()?.let { e ->
        Timber.e(e)
        allServicesLiveData.postError(e, e.localizedMessage)
      }
    }
  }

}