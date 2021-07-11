package io.github.o2formm.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.o2formm.helper.asyncviewstate.AsyncViewStateLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
class SplashViewModel : ViewModel() {

  val dataFromSheetLiveData = AsyncViewStateLiveData<String>()

  fun getAllDataFromSheet() {
    viewModelScope.launch {

      dataFromSheetLiveData.postLoading()

      val result = runCatching {

        withContext(Dispatchers.IO) {

        }

      }

      result.exceptionOrNull()?.let { e ->
        Timber.e(e)
        dataFromSheetLiveData.postError(e, e.localizedMessage)
      }


    }
  }
}