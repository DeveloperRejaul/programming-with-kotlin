package com.test.statemanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.test.statemanagement.ViewModal.NameState
import com.test.statemanagement.ui.theme.StateManagementTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel = ViewModelProvider(this)[NameState::class.java]
        setContent {
            StateManagementTheme {
               Home(viewModel)
            }
        }
    }
}

@Composable
fun Home (viewModel: NameState) {
    // Local State Management also props
    //    var name by rememberSaveable {
    //        mutableStateOf("")
    //    }

    val  name by viewModel.name.observeAsState(initial = "")
    val  lName by viewModel.last_name.observeAsState(initial = "")

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Title(name, lName);
            MyInput( name, onValueChange = {viewModel.updateName(it)} )
            MyInput( lName, onValueChange = {viewModel.updateLastname(it)} )
        }
    }
}

@Composable
fun Title (name:String, lName:String) {
    Text("Hello, $name $lName", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
}

@Composable
fun MyInput (name: String, onValueChange:(String)-> Unit) {
    TextField(
       onValueChange = {
           onValueChange(it)
       },
        value = name
    )
}