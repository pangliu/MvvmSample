package com.example.mvvm_test.base

import android.service.autofill.OnClickAction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes


inline fun View.onClick(crossinline clickAction: () -> Unit) = this.setOnClickListener{
    clickAction.invoke()
}

fun ViewGroup.inflate(@LayoutRes layout: Int, attachToBoolean: Boolean = false): View =
    LayoutInflater.from(this.context).inflate(layout, this, attachToBoolean)

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}