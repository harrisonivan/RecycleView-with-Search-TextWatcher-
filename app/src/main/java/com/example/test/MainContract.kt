package com.example.test

import com.example.test.model.ResponsRespons

interface MainContract {
    interface view {
        fun setAdapter (adapter: MainAdapter)
        fun setDataState(isNull: Boolean)
        fun showNoData()
        fun hideNoData()
        fun showLoading()
        fun hideLoading()

    }

    interface presenter {
        fun getData(textQ: String)
        fun setAdapter(itemCustomers: List<ResponsRespons?>?,
                       filterBy: String?)

    }

    interface repository {
        fun onDestroy()


    }
}