package com.test.myapplication.core.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.test.myapplication.core.theme.Typography

@Composable
fun Button (
    text:String,
    onClick: () -> Unit,
    isLoading: Boolean?=null
) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().height(60.dp),
        contentPadding = PaddingValues(vertical = 10.dp),
        // shape = RectangleShape
        shape = RoundedCornerShape(5.dp)
    ) {
        if(isLoading == true) CircularProgressIndicator(
            modifier = Modifier.width(20.dp).height(20.dp),
            color = MaterialTheme.colorScheme.surface,
            trackColor = MaterialTheme.colorScheme.primary,
        ) else  Text(text, style = Typography.titleMedium)

    }
}