package io.github.o2formm.feature.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.o2formm.domain.sheet.model.TownshipId

/**
Created By Aunt Htoo Aung on 12/07/2021.
 **/
class SharedViewModel : ViewModel() {

  val selectTownshipLiveData = MutableLiveData<TownshipId>()
}