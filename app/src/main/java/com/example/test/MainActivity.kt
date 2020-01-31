package com.example.test

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : BaseActivity(), MainContract.view, View.OnClickListener {

    private var repository: MainRepository? = MainRepository(this)
    private var MainPresenter: MainPresenter =
        MainPresenter(this, repository)

    var textTemp = ""
    var isNull = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        coroutineScope.launch(Dispatchers.Main) {
            if (isInternetAvailable()) {
                MainPresenter.getData("")
            } else {
                setDataState(true)
                showNoData()
            }
        }
        init()
    }

    private fun init(){
        rv_main.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        tv_main_search.setOnClickListener(this)

        tv_main_search.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                Log.e("s","$s")
//                MainPresenter.getData(s.toString())
                textTemp = s.toString()


            }
        })

        iv_search.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.iv_search -> search()

        }
    }

    override fun showNoData() {
        rv_main.adapter?.notifyDataSetChanged()
        rl_main_no_data_screen.visibility = View.VISIBLE
    }

    override fun hideNoData() {
        rl_main_no_data_screen.visibility = View.GONE
    }

    override fun setDataState(isNull: Boolean) {
        this.isNull = isNull
    }

    override fun setAdapter(adapter: MainAdapter) {
        rv_main.adapter = adapter
//        adapter.setItemClickListener(object :MainAdapter.OnItemClickListener {
//            override fun onItemClick(
//                parent: AdapterView<*>?,
//                view: View?,
//                position: Int,
//                id: Long
//            ) {
//
//            }
//
//            override fun onClick(view: View, itemCustomer: Response) {
//
//            }
//        } )
        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        ll_loading_screen.visibility= View.VISIBLE
    }

    override fun hideLoading() {
        ll_loading_screen.visibility= View.GONE
    }


    private fun search(){
            MainPresenter.getData(textTemp)
    }

}
