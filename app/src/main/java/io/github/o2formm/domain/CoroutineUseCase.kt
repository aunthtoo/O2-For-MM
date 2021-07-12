package io.github.o2formm.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class CoroutineUseCase<Input, Output> {

  suspend fun execute(input: Input): Output {
    return withContext(Dispatchers.IO) {
      provide(input)
    }
  }

  protected abstract suspend fun provide(input: Input): Output
}