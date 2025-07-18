package com.test.myapplication.core.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.myapplication.core.theme.Typography

@Composable
fun Header (onBack: () -> Unit, headerLabel: String, showBackIcon: Boolean = true) {
    Row (modifier = Modifier.fillMaxWidth().height(30.dp)) {
        if(showBackIcon) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "backIcons", modifier = Modifier.clickable(onClick = onBack))
            Spacer(modifier = Modifier.width(10.dp))
        }
        Text(headerLabel, style = Typography.titleLarge.copy(fontSize = 18.sp, fontWeight = FontWeight.Bold))
    }
}