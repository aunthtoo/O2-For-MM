package io.github.o2formm.domain.sheet.usecase

import io.github.o2formm.domain.CoroutineUseCase
import io.github.o2formm.domain.sheet.model.Township
import io.github.o2formm.domain.sheet.repository.ServiceSheetRepository

/**
Created By Aunt Htoo Aung on 12/07/2021.
 **/
class GetAllTownships constructor(private val serviceSheetRepository: ServiceSheetRepository) :
  CoroutineUseCase<Unit, List<Township>>() {

  override suspend fun provide(input: Unit): List<Township> {
    return serviceSheetRepository.getAllTownshipsFromLocal()
  }
}