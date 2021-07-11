package io.github.o2formm.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<I, O> {

  fun execute(params: I): Flow<O> {
    return provide(params)
      .flowOn(Dispatchers.IO)
  }

  protected abstract fun provide(
    params: I
  ): Flow<O>

}