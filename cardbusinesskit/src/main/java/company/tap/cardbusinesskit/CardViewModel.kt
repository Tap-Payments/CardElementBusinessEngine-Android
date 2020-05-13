package company.tap.cardbusinesskit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import company.tap.cardbusinesskit.CardViewEvent.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

/**
 *
 * Created by Mario Gamal on 5/11/20
 * Copyright © 2020 Tap Payments. All rights reserved.
 *
 */
class CardViewModel : ViewModel() {

    private val repository = CardRepository()
    private val compositeDisposable = CompositeDisposable()
    val liveData = MutableLiveData<String>()

    init {
        compositeDisposable.add(repository.resultObservable
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { liveData.value = "Loading" }
            .doOnTerminate { liveData.value = "Finished" }
            .subscribe(
                { data -> liveData.value = data },
                { error -> liveData.value = error.message }
            ))
    }

    private fun getInitData() = repository.getInitData()

    fun processEvent(event: CardViewEvent) {
        when (event) {
            InitEvent -> getInitData()
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}