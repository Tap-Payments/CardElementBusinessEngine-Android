package company.tap.cardbusiness

import android.app.Application
import company.tap.tapnetworkkit.connection.NetworkApp

/**
 *
 * Created by Mario Gamal on 5/13/20
 * Copyright © 2020 Tap Payments. All rights reserved.
 *
 */
class CardApp: Application() {
    override fun onCreate() {
        super.onCreate()
        NetworkApp.initNetwork(
            this,
            "sk_test_kovrMB0mupFJXfNZWx6Etg5y",
            "company.tap.goSellSDKExample",
            "https://api.tap.company/v2/"
        )
    }
}