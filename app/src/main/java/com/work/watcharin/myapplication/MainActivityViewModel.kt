package com.work.watcharin.myapplication

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.google.firebase.firestore.*
import com.work.watcharin.model.Party
import durdinapps.rxfirebase2.RxFirestore
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel : ViewModel(), DisposableTracker {

    override val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val db = FirebaseFirestore.getInstance()
    private val partyRef = db.collection("parties")

    private val partyObservable = RxFirestore.observeQueryRef(partyRef).toObservable()

    private var partyList: MutableLiveData<List<Party>> = MutableLiveData()

    fun getPartyList(): LiveData<List<Party>> {
        loadPartyList()
        return this.partyList
    }

    private fun loadPartyList() {
        partyObservable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                val parties = it.map { party -> party.toObject(Party::class.java) }
                this.partyList.postValue(parties)
            }
            .track()
    }

    override fun onCleared() {
        super.onCleared()
        cleanUpDisposable();
    }
}