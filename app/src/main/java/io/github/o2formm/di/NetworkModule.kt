package io.github.o2formm.di

import com.github.theapache64.retrosheet.RetrosheetInterceptor
import com.squareup.moshi.Moshi
import io.github.o2formm.BuildConfig
import io.github.o2formm.data.remote.RemoteConstants
import io.github.o2formm.data.remote.api.SheetService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/

val NetworkModule = module {

  single { createRetrosheetInterceptor() }

  single { createOkHttpClient(get()) }

  single { createMoshi() }

  single { createRetrofit(get(), get()) }

}

fun createRetrosheetInterceptor(): RetrosheetInterceptor {
  val builder = RetrosheetInterceptor.Builder()

  if (BuildConfig.DEBUG)
    builder.setLogging(true)
  else
    builder.setLogging(false)

  builder.addSheet(
    RemoteConstants.services, // sheet name
    RemoteConstants.service,
    RemoteConstants.name,
    RemoteConstants.nameMM,
    RemoteConstants.address,
    RemoteConstants.addressMM,
    RemoteConstants.phone1,
    RemoteConstants.phone2,
    RemoteConstants.phone3,
    RemoteConstants.phone4,
    RemoteConstants.phone5,
    RemoteConstants.townshipMM,
    RemoteConstants.township,
    RemoteConstants.stateRegionMM,
    RemoteConstants.stateRegion,
    RemoteConstants.latLong,
    RemoteConstants.remark,
    RemoteConstants.url,
    RemoteConstants.serviceIfOthers
  )

  return builder.build()
}

fun createOkHttpClient(retrosheetInterceptor: RetrosheetInterceptor): OkHttpClient {
  return OkHttpClient.Builder().addInterceptor(retrosheetInterceptor).build()
}

fun createMoshi(): Moshi {
  return Moshi.Builder().build()
}

fun createRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): SheetService {

  val retrofit = Retrofit.Builder().baseUrl(RemoteConstants.SHEET_BASE_URL).client(okHttpClient)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

  return retrofit.create(SheetService::class.java)
}