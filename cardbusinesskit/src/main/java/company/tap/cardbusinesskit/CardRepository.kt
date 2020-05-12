package company.tap.cardbusinesskit

import android.content.Context
import company.tap.tapnetworkkit.connection.AppInfo
import company.tap.tapnetworkkit.controller.NetworkController
import company.tap.tapnetworkkit.enums.TapMethodType
import company.tap.tapnetworkkit.exception_handling.GoSellError
import company.tap.tapnetworkkit.interfaces.APIRequestCallback
import company.tap.tapnetworkkit.interfaces.BaseResponse
import io.reactivex.subjects.BehaviorSubject
import retrofit2.Response

/**
 *
 * Created by Mario Gamal on 5/11/20
 * Copyright © 2020 Tap Payments. All rights reserved.
 *
 */
class CardRepository : APIRequestCallback<InitResponse> {

    val resultSubject = BehaviorSubject.create<InitResponse>()

    fun initAppApi(context: Context) {
        AppInfo.setAuthToken(context, "sk_test_kovrMB0mupFJXfNZWx6Etg5y", "company.tap.goSellSDKExample")
        NetworkController.getInstance().setBaseUrl(ApiService.BASE_URL)
        NetworkController.getInstance().processRequest(TapMethodType.GET, ApiService.INIT, null, this, context)
    }

    override fun onSuccess(responseCode: Int, serializedResponse: Response<InitResponse>?) {
        serializedResponse?.body()?.let {
            resultSubject.onNext(it)
            resultSubject.onComplete()
        }
    }

    override fun onFailure(errorDetails: GoSellError?) {
        errorDetails?.let {
            resultSubject.onError(it.throwable)
        }
    }

}