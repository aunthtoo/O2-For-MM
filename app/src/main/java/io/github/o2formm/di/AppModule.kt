package io.github.o2formm.di

import io.github.o2formm.feature.filter.FilterByTownshipViewModel
import io.github.o2formm.feature.main.MainViewModel
import io.github.o2formm.feature.main.SharedViewModel
import io.github.o2formm.feature.oxygen.OxygenViewModel
import io.github.o2formm.feature.oxygen.detail.OxygenDetailViewModel
import io.github.o2formm.feature.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/

val AppModule = module {
  //splash
  viewModel { SplashViewModel(get()) }

  //main
  viewModel { MainViewModel(get()) }

  //oxygen
  viewModel { OxygenViewModel(get(), get(), get()) }

  //oxygen service detail
  viewModel { OxygenDetailViewModel(get()) }

  //filter by township
  viewModel { FilterByTownshipViewModel(get()) }

  //shared viewmodel
  viewModel { SharedViewModel() }
}