package com.example.movieapp.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.adapters.CastsAdapter
import com.example.shared.data.vos.PersonVO
import kotlinx.android.synthetic.main.viewpod_casts.view.*

class CastViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private lateinit var mAdapter : CastsAdapter

    override fun onFinishInflate() {
        super.onFinishInflate()


    }

    fun setUpRecyclerview(){
        mAdapter = CastsAdapter()
        rvCasts.adapter = mAdapter
        rvCasts.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    fun displayCasts(casts: List<PersonVO>){
        mAdapter.setNewData(casts)
    }
}