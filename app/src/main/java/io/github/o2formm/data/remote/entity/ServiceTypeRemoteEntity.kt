package io.github.o2formm.data.remote.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.github.o2formm.data.remote.RemoteConstants

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/

@JsonClass(generateAdapter = true)
data class ServiceTypeRemoteEntity(@Json(name = RemoteConstants.services) val type: String)
