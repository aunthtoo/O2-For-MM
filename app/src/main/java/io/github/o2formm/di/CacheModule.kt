package io.github.o2formm.di

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import io.github.o2formm.O2ForMMDb
import io.github.o2formm.ServicesTable
import io.github.o2formm.data.cache.adapter.StringListColumnAdapter
import io.github.o2formm.data.cache.source.ServiceSheetCacheSourceImpl
import io.github.o2formm.data.common.repository.sheet.cache.ServiceSheetCacheSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/

val CacheModule = module {
  single { createSqlDriver(androidContext()) }

  single { createO2ForMMDb(get()) }

  single<ServiceSheetCacheSource> { ServiceSheetCacheSourceImpl(get()) }
}

fun createSqlDriver(context: Context): SqlDriver {
  return AndroidSqliteDriver(O2ForMMDb.Schema, context, "O2ForMM.db")
}

fun createO2ForMMDb(sqlDriver: SqlDriver): O2ForMMDb {
  return O2ForMMDb(
    sqlDriver,
    ServicesTableAdapter = ServicesTable.Adapter(phonesAdapter = StringListColumnAdapter())
  )
}
