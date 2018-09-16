package com.jshvarts.notespaging.presentation

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.closeSoftKeyboard() {
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}