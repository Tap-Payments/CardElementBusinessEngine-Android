package company.tap.cardbusinesskit

import com.google.gson.JsonElement
import company.tap.tapnetworkkit.controller.NetworkController
import company.tap.tapnetworkkit.enums.TapMethodType
import company.tap.tapnetworkkit.exception.GoSellError
import company.tap.tapnetworkkit.interfaces.APIRequestCallback
import io.reactivex.subjects.BehaviorSubject
import retrofit2.Response

/**
 *
 * Created by Mario Gamal on 5/11/20
 * Copyright © 2020 Tap Payments. All rights reserved.
 *
 */
class CardRepository : APIRequestCallback {

    val resultObservable = BehaviorSubject.create<String>()

    fun initAppApi() {
        NetworkController.getInstance()
            .processRequest(
                TapMethodType.GET,
                ApiService.INIT,
                null,
                this,
                1
            )
    }

    override fun onSuccess(responseCode: Int, requestCode: Int, response: Response<JsonElement>?) {
        response?.body()?.let {
            resultObservable.onNext(it.toString())
            resultObservable.onComplete()
        }
    }

    override fun onFailure(requestCode: Int, errorDetails: GoSellError?) {
        errorDetails?.let {
            resultObservable.onError(it.throwable)
        }
    }

}