package com.example.shared

import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog

abstract class BaseActivity : AppCompatActivity(), BaseView {

    private var mAlertDialog : SweetAlertDialog ?= null
    private var mErrorDialog : SweetAlertDialog?= null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun displayError(message: String) {
        hideLoading()

        mErrorDialog = SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
        mErrorDialog?.let {dialog->
            dialog.titleText = "Error!"
            dialog.contentText = message
            dialog.confirmText = "OK"
            dialog.setConfirmClickListener {
                it.dismiss()
            }
            dialog.show()
        }
    }

    override fun displayLoading() {
        mAlertDialog = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
        mAlertDialog?.let {dialog->
            dialog.titleText = "Loading"
            dialog.setCancelable(false)
            dialog.progressHelper.barColor = Color.BLUE
            dialog.show()
        }
    }

    override fun hideLoading() {
        mAlertDialog?.dismiss()
    }

    override fun onStop() {
        super.onStop()

        mAlertDialog?.dismiss()
        mErrorDialog?.dismiss()
    }
}