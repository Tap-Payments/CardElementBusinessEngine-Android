package company.tap.cardbusinesskit.data

import com.google.gson.Gson
import com.google.gson.JsonElement
import company.tap.cardbusinesskit.models.InitResponse
import company.tap.cardbusinesskit.mvi.CardViewState
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

    val resultObservable = BehaviorSubject.create<CardViewState>()

    fun getInitData() {
        NetworkController.getInstance()
            .processRequest(TapMethodType.GET, ApiService.INIT, null, this,
                INIT_CODE
            )
    }

    override fun onSuccess(responseCode: Int, requestCode: Int, response: Response<JsonElement>?) {
        if (requestCode == INIT_CODE) {
            response?.body()?.let {
                val initResponse = Gson().fromJson(it, InitResponse::class.java)
                val viewState = CardViewState(initResponse = initResponse)
                resultObservable.onNext(viewState)
                resultObservable.onComplete()
            }
        }
    }

    override fun onFailure(requestCode: Int, errorDetails: GoSellError?) {
        errorDetails?.let {
            if (it.throwable != null)
                resultObservable.onError(it.throwable)
            else
                resultObservable.onError(Throwable(it.errorMessage))
        }
    }

    companion object {
        private const val INIT_CODE = 1
    }

}