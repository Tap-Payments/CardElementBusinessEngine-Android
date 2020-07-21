package company.tap.cardbusinesskit.mvi

import company.tap.cardbusinesskit.models.InitResponse
import company.tap.cardbusinesskit.models.Merchant

/**
 *
 * Created by Mario Gamal on 5/13/20
 * Copyright © 2020 Tap Payments. All rights reserved.
 *
 */
data class CardViewState (
    var initResponse: InitResponse? = null
)