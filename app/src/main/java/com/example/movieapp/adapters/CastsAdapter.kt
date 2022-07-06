package com.example.movieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.movieapp.R
import com.example.movieapp.viewholders.CastViewHolder
import com.example.shared.BaseAdapter
import com.example.shared.data.vos.PersonVO

class CastsAdapter : BaseAdapter<CastViewHolder, PersonVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_cast, parent, false)
        return CastViewHolder(view)
    }
}