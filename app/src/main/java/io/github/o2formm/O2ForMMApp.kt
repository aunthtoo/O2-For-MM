package io.github.o2formm

import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import io.github.o2formm.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
class O2ForMMApp : MultiDexApplication() {

  override fun onCreate() {
    super.onCreate()

    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }

    //koin
    startKoin {
      androidContext(this@O2ForMMApp)
      modules(listOf(AppModule, DomainModule, DataModule, CacheModule, NetworkModule))
    }
  }
}