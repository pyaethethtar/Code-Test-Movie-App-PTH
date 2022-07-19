package com.example.movieapp.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.example.shared.BaseViewHolder
import com.example.shared.data.vos.PersonVO
import com.example.shared.utils.IMAGE_URL
import kotlinx.android.synthetic.main.viewholder_cast.view.*

class CastViewHolder(itemView: View) : BaseViewHolder<PersonVO>(itemView) {

    override fun bindData(data: PersonVO) {
        Glide.with(itemView.context).load(IMAGE_URL+data.profilePath).into(itemView.ivCast)
        itemView.tvCastName.text = data.name
    }
}