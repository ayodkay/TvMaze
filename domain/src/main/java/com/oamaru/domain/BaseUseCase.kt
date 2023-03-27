package com.oamaru.domain

import com.jakewharton.rxrelay2.BehaviorRelay
import com.oamaru.data.status.Status
import com.oamaru.data.status.toErrorStatus
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

interface BaseUseCase<in Req : BaseUseCase.Request, Resp : BaseUseCase.Response> {
    abstract class Request
    abstract class Response

    val disposables: CompositeDisposable

    fun execute(request: Req): BehaviorRelay<Status<Resp>>

    fun dispose()

    fun <T> Observable<Status<T>>.convertToUseCaseResponse(converter: (T) -> Resp): BehaviorRelay<Status<Resp>> =
        BehaviorRelay.createDefault(Status.Loading<Resp>() as Status<Resp>).also { statusRelay ->
            subscribeOn(Schedulers.io()).subscribe({ status ->
                when (status) {
                    is Status.Loading -> Unit
                    is Status.OnSuccess -> statusRelay.accept(Status.OnSuccess(converter(status.value)))
                    is Status.OnError -> statusRelay.accept(Status.OnError(status.error))
                }
            }, { statusRelay.accept(it.toErrorStatus()) })
                .addTo(disposables)
        }
}
