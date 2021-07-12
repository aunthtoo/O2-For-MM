package io.github.o2formm.domain.sheet.usecase

import io.github.o2formm.domain.CoroutineUseCase
import io.github.o2formm.domain.sheet.model.Township
import io.github.o2formm.domain.sheet.model.TownshipId
import io.github.o2formm.domain.sheet.repository.ServiceSheetRepository

/**
Created By Aunt Htoo Aung on 12/07/2021.
 **/
class GetTownshipById constructor(private val serviceSheetRepository: ServiceSheetRepository) :
  CoroutineUseCase<TownshipId, Township>() {

  override suspend fun provide(input: TownshipId): Township {
    return serviceSheetRepository.getTownshipById(townshipId = input)
  }
}