package com.example.shared

interface BaseView {

    fun displayError(message: String)
    fun displayLoading()
    fun hideLoading()
}