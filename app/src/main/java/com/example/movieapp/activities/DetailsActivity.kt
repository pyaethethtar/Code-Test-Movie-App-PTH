package com.example.movieapp.activities

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.viewpods.CastViewPod
import com.example.movieapp.viewpods.MovieDetailsViewPod
import com.example.shared.BaseActivity
import com.example.shared.data.vos.MovieVO
import com.example.movieapp.mvp.presenters.DetailsPresenter
import com.example.movieapp.mvp.presenters.DetailsPresenterImpl
import com.example.movieapp.mvp.views.DetailsView
import com.example.shared.utils.IMAGE_URL
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : BaseActivity(), DetailsView {

    private var mMovieId : Int = 0
    private lateinit var mPresenter : DetailsPresenter
    private lateinit var mDetailsViewPod : MovieDetailsViewPod
    private lateinit var mCastsViewPod : CastViewPod

    companion object{
        const val EXTRA_ID = "EXTRA_ID"
        fun newIntent(context: Context, id: Int) : Intent{
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(EXTRA_ID, id)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        mMovieId = intent.getIntExtra(EXTRA_ID, 0)
        showWindowTransluentStatusBar()
        setUpPresenter()
        setUpViewPods()
        setUpListeners()
        mPresenter.onDetailsUiReady(this, mMovieId)
    }

    private fun showWindowTransluentStatusBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProvider(this).get(DetailsPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpViewPods(){
        mDetailsViewPod = vpMovieDetails as MovieDetailsViewPod
        mDetailsViewPod.setPresenter(mPresenter)

        mCastsViewPod = vpCasts as CastViewPod
    }

    private fun setUpListeners(){
        btnBack.setOnClickListener {
            mPresenter.onTapBack()
        }
    }

    override fun displayMovieDetails(details: MovieVO) {
        Glide.with(this).load(IMAGE_URL+details.posterPath).into(ivMoviePhoto)
        mDetailsViewPod.displayMovieDetails(details)
    }

    override fun goBackToMain() {
        this.finish()
    }
}