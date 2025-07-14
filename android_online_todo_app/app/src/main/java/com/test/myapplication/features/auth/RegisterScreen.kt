package com.test.myapplication.features.auth

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.test.myapplication.core.api.NetworkResponse
import com.test.myapplication.core.component.Button
import com.test.myapplication.core.component.Container
import com.test.myapplication.core.component.Input
import com.test.myapplication.core.constance.InputVariant
import com.test.myapplication.core.theme.Typography
import com.test.myapplication.core.utils.showToast

@Composable
fun RegisterScreen(navController: NavHostController, viewModal: AuthViewModal = viewModel()) {
    val context = LocalContext.current
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val response = viewModal.registerResult.observeAsState()

    LaunchedEffect(response.value) {
        when(response.value) {
            is NetworkResponse.Success -> {
                navController.popBackStack()
            }
            else -> Unit
        }
    }

    Scaffold { innerPadding ->
        Container(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(innerPadding),
            headerLabel = "Register",
            onBack = {navController.popBackStack()}
        ){
            Spacer(modifier = Modifier.height(height = 100.dp))
            Text("Welcome TO TODO App", style = Typography.titleLarge.copy(fontSize = 30.sp, fontWeight = FontWeight.Bold))
            Spacer(modifier = Modifier.height(height = 50.dp))
            Input(
                value = userName,
                placeholder = "Type your user name",
                label = "User name",
                onChange = {userName = it}
            )
            Spacer(modifier = Modifier.height(height = 10.dp))
            Input(
                value = email,
                placeholder = "Type your user email",
                label = "Email",
                onChange = {email = it}
            )
            Spacer(modifier = Modifier.height(height = 10.dp))
            Input(
                value = password,
                placeholder = "Type Password",
                label = "Password",
                onChange = {password = it},
                variant =  InputVariant.PASSWORD
            )
            Spacer(modifier = Modifier.height(height = 10.dp))
            Input(
                value = confirmPassword,
                placeholder = "Type password again",
                label = "Confirm password",
                onChange = {confirmPassword = it},
                variant =  InputVariant.PASSWORD,
                keyboardAction = ImeAction.Done ,
            )
            Spacer(modifier = Modifier.height(height = 10.dp))
            Button(
                text = "Register",
                onClick = {
                    if(email.isEmpty() || userName.isEmpty()) {
                        showToast(context, "Name or email is empty")
                        return@Button
                    }
                    viewModal.register(RegisterModal(age = 20, firstName = userName , lastName = email))
                },
                isLoading = response.value === NetworkResponse.Loading
            )
        }
    }


}

