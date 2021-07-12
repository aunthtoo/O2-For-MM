package io.github.o2formm.domain.sheet.usecase

import io.github.o2formm.domain.CoroutineUseCase
import io.github.o2formm.domain.sheet.model.ServiceType
import io.github.o2formm.domain.sheet.repository.ServiceSheetRepository

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
class GetAllServicesType constructor(private val serviceSheetRepository: ServiceSheetRepository) :
  CoroutineUseCase<Unit, List<ServiceType>>() {

  override suspend fun provide(input: Unit): List<ServiceType> {
    return serviceSheetRepository.getAllServiceTypeFromLocal()
  }
}