package com.example.test

import android.util.Log
import com.example.test.model.ResponsRespons
import com.example.test.model.Response
import com.example.test.retrofit.ApiClientRx
import com.example.test.utilities.Utils
import com.google.gson.JsonObject
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import okhttp3.ResponseBody
import java.util.*
import kotlin.coroutines.CoroutineContext

class MainPresenter(var view : MainContract.view?,var repository: MainContract.repository?):
    MainContract.presenter,CoroutineScope {

    lateinit var call: Single<Response>
    lateinit var adapterMain: MainAdapter

    private var job = Job()
    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    // set coroutine
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    override fun getData(textQ:String) {
//        view!!.showLoading()

        val sort = "$textQ type:user in:login"
        val jsonObject = JsonObject()
                call = ApiClientRx().apiService.getItem(sort)
                val disposable =
                    call.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            val temp = it.items

                            view!!.setDataState(false)

                            setAdapter(temp,"")

                            Log.e("isi","${it.items}")
//                            view!!.hideLoading()
                        }, { error ->
                            Log.e("result", "$error")
//                            view!!.hideLoading()
                        })
                compositeDisposable.add(disposable)
            }



    override fun setAdapter(item: List<ResponsRespons?>?, filterBy: String?) {
        val resultFilter = ArrayList<ResponsRespons>()
        // filtering
        for (it in item!!) {
            if (it?.login.toString().toLowerCase(Locale.ENGLISH).contains(filterBy ?: return)
            ){
                resultFilter.add(it!!)
            }
        }
        // set result
        if (resultFilter.isNullOrEmpty()) {
            adapterMain = MainAdapter(resultFilter)
            view!!.setAdapter(adapterMain)
            view!!.showNoData()
        } else {
            val arrayList = Utils.convertToArraylist(resultFilter)
            adapterMain =
                MainAdapter(arrayList as ArrayList<ResponsRespons>)
            view!!.setAdapter(adapterMain)
            view!!.hideNoData()
        }
    }
}