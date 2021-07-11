package io.github.o2formm.domain.sheet.usecase

import io.github.o2formm.domain.CoroutineUseCase
import io.github.o2formm.domain.sheet.repository.ServiceSheetRepository

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
class GetAllDataSheetAndInsertToLocal constructor(private val serviceSheetRepository: ServiceSheetRepository) :
  CoroutineUseCase<Unit, Unit>() {

  override suspend fun provide(input: Unit) {
     serviceSheetRepository.getAllDataSheetAndInsertToLocal()
  }
}