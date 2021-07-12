package io.github.o2formm.domain.sheet.usecase

import io.github.o2formm.domain.CoroutineUseCase
import io.github.o2formm.domain.sheet.model.Service
import io.github.o2formm.domain.sheet.repository.ServiceSheetRepository

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
class GetServicesByType constructor(private val serviceSheetRepository: ServiceSheetRepository) :
  CoroutineUseCase<String, List<Service>>() {

  override suspend fun provide(input: String): List<Service> {
    return serviceSheetRepository.getServicesByType(type = input)
  }
}