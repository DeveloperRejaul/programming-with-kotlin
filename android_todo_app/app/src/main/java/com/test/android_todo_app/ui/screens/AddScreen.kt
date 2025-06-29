package com.test.android_todo_app.ui.screens



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.test.android_todo_app.Todo
import com.test.android_todo_app.TodoViewModal
import com.test.android_todo_app.ui.theme.Typography
import java.util.UUID
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.test.android_todo_app.navigation.NavRoutes

@SuppressLint("ContextCastToActivity")
@Composable
fun AddScreen(navController: NavHostController, todoViewModal: TodoViewModal, todo: NavRoutes.Add) {

    // with out passing parameter we can use context like this way
    val viewModal: TodoViewModal = viewModel(LocalContext.current as ComponentActivity)

    // local state for input
    var title by rememberSaveable { mutableStateOf(todo.title ?: "") }
    var description by rememberSaveable { mutableStateOf(todo.description ?: "") }

    // check is params data available and is update actions
    var isUpdate = !todo.title.isNullOrBlank() && !todo.description.isNullOrBlank() && (todo.id != null && todo.id != 0)


    Scaffold(){ innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = title ,
                onValueChange = { title = it} ,
                label = {Text("Enter Tour Title")},
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = description ,
                onValueChange = { description = it} ,
                label = {Text("Enter Tour description")},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )

            Button(onClick = {
                if(isUpdate) {
                    if(todo.id != null) {
                        todoViewModal.update(Todo(id = todo.id, title = title, description = description))
                    }
                }else {
                    // context get from parameter
                    todoViewModal.addTodo(Todo(id = UUID.randomUUID().hashCode(), title = title, description = description))

                    // context get with out parameter
                    // viewModal.addTodo(Todo(id = UUID.randomUUID().hashCode(), title = title, description = description))
                }
                navController.popBackStack()
            }) {
                Text( if(isUpdate) "Update" else "Create", style = Typography.titleMedium)
            }
        }
    }
}