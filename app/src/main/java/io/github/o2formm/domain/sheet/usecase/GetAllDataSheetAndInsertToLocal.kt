package io.github.o2formm.domain.sheet.usecase

import io.github.o2formm.domain.CoroutineUseCase
import io.github.o2formm.domain.sheet.repository.ServiceSheetRepository
import io.github.o2formm.exception.NoInternetConnection
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
class GetAllDataSheetAndInsertToLocal constructor(private val serviceSheetRepository: ServiceSheetRepository) :
  CoroutineUseCase<Unit, Unit>() {

  override suspend fun provide(input: Unit) {
    try {
      serviceSheetRepository.getAllDataSheetAndInsertToLocal()
    } catch (t: UnknownHostException) {
      throw NoInternetConnection()
    } catch (t: SocketTimeoutException) {
      throw NoInternetConnection()
    } catch (t: ConnectException) {
      throw NoInternetConnection()
    } catch (t: Throwable) {
      throw t
    }
  }
}