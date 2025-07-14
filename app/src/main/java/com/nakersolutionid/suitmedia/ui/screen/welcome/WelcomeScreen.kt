package com.nakersolutionid.suitmedia.ui.screen.welcome

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nakersolutionid.suitmedia.ui.component.common.MyButton
import com.nakersolutionid.suitmedia.ui.component.common.MyTopAppBar
import com.nakersolutionid.suitmedia.ui.theme.SuitmediaTheme

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    username: String,
    selectedUser: String? = null,
    onNavigationBack: () -> Unit,
    onUserSelect: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column {
                MyTopAppBar(title = "Second Screen", onNavigationBack = onNavigationBack)
                HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
            }
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, top = 24.dp, end = 24.dp),
                text = "Welcome",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp),
                text = username,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(weight = 1f, fill = true),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = selectedUser ?: "Selected User Name",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            MyButton(
                label = "Choose a User",
                onClick = onUserSelect
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun WelcomeScreenPreview() {
    SuitmediaTheme {
        WelcomeScreen(username = "John Doe", onNavigationBack = {}, onUserSelect = {})
    }
}