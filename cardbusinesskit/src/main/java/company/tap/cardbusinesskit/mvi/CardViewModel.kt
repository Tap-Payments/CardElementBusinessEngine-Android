package company.tap.cardbusinesskit.mvi

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import company.tap.cardbusinesskit.mvi.CardViewEvent.*
import company.tap.cardbusinesskit.data.CardRepository
import company.tap.cardbusinesskit.data.Resource
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
    val liveData = MutableLiveData<Resource<CardViewState>>()
    val context:Context?=null

    init {
        compositeDisposable.add(repository.resultObservable
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { liveData.value = Resource.Loading() }
            .doOnTerminate { liveData.value = Resource.Finished() }
            .subscribe(
                { data -> liveData.value = Resource.Success(data) },
                { error -> liveData.value = error.message?.let { Resource.Error(it) } }
            ))
    }

    private fun getInitData() {
        repository.getInitData()
    }

    fun processEvent(event: CardViewEvent,context: Context) {
        when (event) {
            InitEvent -> getInitData()
            MockEvent -> getMockData(context)

        }
    }

    private fun getMockData(context: Context) {
        repository.getJsonDataFromAsset(context,"dummyapiresponse.json")
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}