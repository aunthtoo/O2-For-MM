package io.github.o2formm.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.o2formm.domain.sheet.usecase.GetAllServiceSheetAndInsertToLocal
import io.github.o2formm.helper.asyncviewstate.AsyncViewStateLiveData
import kotlinx.coroutines.launch
import timber.log.Timber

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
class SplashViewModel constructor(private val getAllServiceSheetAndInsertToLocal: GetAllServiceSheetAndInsertToLocal) :
  ViewModel() {

  val dataFromSheetLiveData = AsyncViewStateLiveData<Unit>()

  fun getAllDataFromSheet() {
    viewModelScope.launch {

      dataFromSheetLiveData.postLoading()

      val result = runCatching {

        getAllServiceSheetAndInsertToLocal.execute(Unit)

        dataFromSheetLiveData.postSuccess(Unit)

      }

      result.exceptionOrNull()?.let { e ->
        Timber.e(e)
        dataFromSheetLiveData.postError(e, e.localizedMessage)
      }


    }
  }
}