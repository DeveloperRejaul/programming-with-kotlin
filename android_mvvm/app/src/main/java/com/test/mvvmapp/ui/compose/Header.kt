package com.test.mvvmapp.ui.compose

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.test.mvvmapp.ui.theme.Typography


@Composable
fun Header (text: String) {
    Text(text, style = Typography.bodyLarge)
}