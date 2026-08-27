package com.sigeschool.domain.util

import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true },
    crossinline onFetchFailed: (Throwable) -> Unit = { }
): Flow<Resource<ResultType>> = flow {
    val data = query().first()

    if (shouldFetch(data)) {
        emit(Resource.Loading(data))

        try {
            saveFetchResult(fetch())
            emitAll(query().map { Resource.Success(it) })
        } catch (throwable: Throwable) {
            onFetchFailed(throwable)
            emitAll(query().map { Resource.Error(throwable.message ?: "Unknown error", it) })
        }
    } else {
        emitAll(query().map { Resource.Success(it) })
    }
}
