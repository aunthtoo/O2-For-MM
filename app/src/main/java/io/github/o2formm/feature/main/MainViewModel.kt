package io.github.o2formm.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.o2formm.domain.sheet.usecase.GetAllServices
import io.github.o2formm.helper.asyncviewstate.AsyncViewStateLiveData
import kotlinx.coroutines.launch
import timber.log.Timber

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
class MainViewModel(private val getAllServices: GetAllServices) : ViewModel() {

  val allServicesLiveData = AsyncViewStateLiveData<String>()

  fun getAllServices() {
    viewModelScope.launch {

      allServicesLiveData.postLoading()

      val result = runCatching {

        val services = getAllServices.execute(Unit)

        allServicesLiveData.postSuccess(services.size.toString())
      }

      result.exceptionOrNull()?.let { e ->
        Timber.e(e)
        allServicesLiveData.postError(e, e.localizedMessage)
      }
    }
  }

}