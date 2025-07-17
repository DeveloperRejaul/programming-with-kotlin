package com.test.myapplication.core.utils

import android.content.Context
import android.widget.Toast

fun showToast (context: Context, message:String) {
    // test
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}