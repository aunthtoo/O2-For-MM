package io.github.o2formm.di

import io.github.o2formm.feature.main.MainViewModel
import io.github.o2formm.feature.oxygen.OxygenViewModel
import io.github.o2formm.feature.splash.SplashViewModel
import org.koin.dsl.module

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/

val AppModule = module {
  //splash
  single { SplashViewModel(get()) }

  //main
  single { MainViewModel(get()) }

  //oxygen
  single { OxygenViewModel(get()) }
}