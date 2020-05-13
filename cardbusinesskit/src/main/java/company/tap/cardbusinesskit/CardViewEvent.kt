package company.tap.cardbusinesskit

import company.tap.commonmodels.TapCard

/**
 *
 * Created by Mario Gamal on 5/13/20
 * Copyright © 2020 Tap Payments. All rights reserved.
 *
 */
sealed class CardViewEvent {
    object InitEvent : CardViewEvent()
    data class SaveCardEvent(val card: TapCard) : CardViewEvent()
    data class TokenizeCardEvent(val card: TapCard) : CardViewEvent()
}