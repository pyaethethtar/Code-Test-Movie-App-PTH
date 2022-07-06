package com.example.movieapp.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.adapters.CastsAdapter
import kotlinx.android.synthetic.main.viewpod_casts.view.*

class CastViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private lateinit var mAdapter : CastsAdapter

    override fun onFinishInflate() {
        super.onFinishInflate()

        setUpRecyclerview()
    }

    private fun setUpRecyclerview(){
        mAdapter = CastsAdapter()
        rvCasts.adapter = mAdapter
        rvCasts.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }
}