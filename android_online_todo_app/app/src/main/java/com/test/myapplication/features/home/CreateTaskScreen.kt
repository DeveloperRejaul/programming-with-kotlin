package com.test.myapplication.features.home


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.test.myapplication.core.api.NetworkResponse
import com.test.myapplication.core.component.Button
import com.test.myapplication.core.component.Container
import com.test.myapplication.core.component.Input
import com.test.myapplication.core.navigation.Routes


@Composable
fun  CreateTaskScreen(
    navController: NavHostController,
    homeViewModal: HomeViewModal,
    params: Routes.Task
) {

    var title by remember { mutableStateOf<String>(params.title ?: "") }
    var body by remember { mutableStateOf<String>(params.body ?: "") }
    val isUpdate = params.isNotEmpty()
    val action = homeViewModal.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        homeViewModal.navEvent.collect { num ->
            if(num == 1) {
                navController.popBackStack()
            }
        }
    }

    Scaffold { innerPadding ->
        Container (
            modifier = Modifier.padding(innerPadding),
            headerLabel = "Create Task",
            onBack = {navController.popBackStack()}
        ) {
            Input(
                value = title,
                onChange = { title = it},
                placeholder = "Type your title",
                label = "Title",
                singleLine = false
            )
            Input(
                value = body,
                onChange = {body = it},
                placeholder = "Type your body",
                label = "Body",
                keyboardAction = ImeAction.Done,
                singleLine = false
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                isLoading = action.value === NetworkResponse.Loading,
                text = if(isUpdate)"Update" else "Create",
                onClick ={
                    if (isUpdate) {
                        homeViewModal.update(HomeModal(
                            id = params.id as Int,
                            userId = params.userId as Int,
                            title = title,
                            body = body
                        ))
                    }
                    else {
                        homeViewModal.create(CreatePostModal(body=body, title=title, userId = 1))
                    }
                }
            )
        }
    }

}

