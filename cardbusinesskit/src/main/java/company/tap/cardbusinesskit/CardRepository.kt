package company.tap.cardbusinesskit

import io.reactivex.subjects.BehaviorSubject

/**
 *
 * Created by Mario Gamal on 5/11/20
 * Copyright © 2020 Tap Payments. All rights reserved.
 *
 */
class CardRepository : FakeApiResponse {

    val resultSubject = BehaviorSubject.create<String>()

    fun makeFakeApiRequest(success: Boolean) {
        if (success)
            onSuccess()
        else
            onError()
    }

    override fun onSuccess() {
        resultSubject.onNext("Success")
        resultSubject.onComplete()
    }

    override fun onError() {
        resultSubject.onError(Throwable("Error"))
    }
}