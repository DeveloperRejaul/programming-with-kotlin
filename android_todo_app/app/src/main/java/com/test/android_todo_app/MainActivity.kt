package com.test.android_todo_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.test.android_todo_app.navigation.Navigator
import com.test.android_todo_app.ui.theme.Android_todo_appTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // setup provider
        val  todoViewModal = ViewModelProvider(this)[TodoViewModal::class.java]
        setContent {
            Android_todo_appTheme {
                Navigator(todoViewModal)
            }
        }
    }
}
