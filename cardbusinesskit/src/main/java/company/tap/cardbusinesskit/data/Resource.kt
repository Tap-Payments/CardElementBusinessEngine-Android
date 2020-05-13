package company.tap.cardbusinesskit.data

/**
 *
 * Created by Mario Gamal on 5/13/20
 * Copyright © 2020 Tap Payments. All rights reserved.
 *
 */

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>() : Resource<T>()
    class Finished<T>() : Resource<T>()
    class Error<T>(message: String) : Resource<T>(message = message)
}