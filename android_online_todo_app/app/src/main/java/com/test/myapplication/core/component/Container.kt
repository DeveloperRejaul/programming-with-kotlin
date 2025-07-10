package com.test.myapplication.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.myapplication.core.theme.Typography

@Composable
fun Container(
    modifier: Modifier = Modifier,
    headerLabel: String = "",
    headerShow: Boolean = true,
    onBack:() -> Unit = { },
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable () -> Unit,
) {
    Column(modifier = Modifier.background(MaterialTheme.colorScheme.surface).padding(horizontal = 10.dp).fillMaxSize()) {
        if(headerShow){
            Row (modifier = Modifier.fillMaxWidth()) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "backIcons", modifier = Modifier.clickable(onClick = onBack))
                Spacer(modifier = Modifier.width(10.dp))
                Text(headerLabel, style = Typography.titleLarge.copy(fontSize = 18.sp, fontWeight = FontWeight.Bold))
            }
        }
        Column(
            modifier = modifier.fillMaxSize().weight(1f).imePadding(),
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment
        ) {
            content()
        }
    }
}

