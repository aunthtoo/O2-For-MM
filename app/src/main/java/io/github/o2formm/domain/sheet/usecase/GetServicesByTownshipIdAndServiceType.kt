package io.github.o2formm.domain.sheet.usecase

import io.github.o2formm.domain.CoroutineUseCase
import io.github.o2formm.domain.sheet.model.Service
import io.github.o2formm.domain.sheet.model.ServiceType
import io.github.o2formm.domain.sheet.model.TownshipId
import io.github.o2formm.domain.sheet.repository.ServiceSheetRepository

/**
Created By Aunt Htoo Aung on 12/07/2021.
 **/
class GetServicesByTownshipIdAndServiceType constructor(private val serviceSheetRepository: ServiceSheetRepository) :
  CoroutineUseCase<GetServicesByTownshipIdAndServiceType.Params, List<Service>>() {

  override suspend fun provide(input: Params): List<Service> {
    val townshipNameMM =
      serviceSheetRepository.getTownshipById(townshipId = input.townshipId).townshipNameMM

    return serviceSheetRepository.getServicesByTownshipNameMMAndServiceType(
      serviceType = input.serviceType,
      townshipNameMM = townshipNameMM
    )
  }

  data class Params(val townshipId: TownshipId, val serviceType: ServiceType)
}