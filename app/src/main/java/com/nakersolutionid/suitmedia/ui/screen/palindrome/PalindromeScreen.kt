package com.nakersolutionid.suitmedia.ui.screen.palindrome

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nakersolutionid.suitmedia.R
import com.nakersolutionid.suitmedia.extensions.meshGradient
import com.nakersolutionid.suitmedia.ui.component.palindrome.PalindromeButton
import com.nakersolutionid.suitmedia.ui.component.palindrome.PalindromeInput
import com.nakersolutionid.suitmedia.ui.component.palindrome.ProfilePicture
import com.nakersolutionid.suitmedia.ui.theme.SuitmediaTheme
import kotlinx.coroutines.launch

@Composable
fun PalindromeScreen(
    viewModel: PalindromeViewModel = viewModel(),
    onNextScreen: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val scrollState = rememberScrollState()
    val meshColors = remember {
        listOf(
            listOf(
                Pair(Offset(0.0f, 0.0f), Color(0xFF4B90AD)),
                Pair(Offset(0.5f, 0.0f), Color(0xFF57A8A7)),
                Pair(Offset(1.0f, 0.0f), Color(0xFF8CBBA8))
            ),
            listOf(
                Pair(Offset(0.0f, 0.75f), Color(0xFF8E94AD)),
                Pair(Offset(0.5f, 0.7f), Color(0xFF8B9DAA)),
                Pair(Offset(1.0f, 0.5f), Color(0xFF6F9EA2))
            ),
            listOf(
                Pair(Offset(0.0f, 1.0f), Color(0xFF29618C)),
                Pair(Offset(0.5f, 1.0f), Color(0xFF29618C)),
                Pair(Offset(1.0f, 1.0f), Color(0xFF29618C))
            )
        )
    }
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.isPalindrome) {
        when (uiState.isPalindrome) {
            true -> {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = "Is Palindrome",
                        actionLabel = "OK",
                        duration = SnackbarDuration.Short
                    )
                }
                viewModel.clearPalindromeState()
            }
            false -> {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = "Not Palindrome",
                        actionLabel = "OK",
                        duration = SnackbarDuration.Short
                    )
                }
                viewModel.clearPalindromeState()
            }
            else -> null
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.imePadding()
            )
        },
    ) { paddingValues ->
        val customModifier = if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
            Modifier
                .fillMaxSize()
                .paint(
                    painter = painterResource(id = R.drawable.background),
                    contentScale = ContentScale.FillBounds,
                    alignment = Alignment.Center
                )
                .padding(paddingValues)
                .imePadding()
                .verticalScroll(scrollState)
        } else {
            Modifier
                .fillMaxSize()
                .meshGradient(
                    points = meshColors,
                    resolutionX = 4,
                    resolutionY = 1
                )
                .padding(paddingValues)
                .imePadding()
                .verticalScroll(scrollState)
        }


        Column(
            modifier = customModifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfilePicture(
                modifier = Modifier
                    .size(140.dp)
                    .background(
                        color = Color.White.copy(alpha = 0.01f),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            )

            Spacer(Modifier.height(64.dp))

            PalindromeInput(
                value = uiState.name,
                label = stringResource(R.string.tf_name),
                onValueChange = { viewModel.onNameChange(it) },
                imeAction = ImeAction.Next,
                focusManager = focusManager
            )

            Spacer(Modifier.height(24.dp))

            PalindromeInput(
                value = uiState.palindrome,
                label = stringResource(R.string.tf_palindrome),
                onValueChange = { viewModel.onPalindromeChange(it) },
                imeAction = ImeAction.Done,
                focusManager = focusManager
            )

            Spacer(Modifier.height(42.dp))

            PalindromeButton(
                label = "CHECK",
                onClick = { viewModel.isPalindrome() }
            )

            Spacer(Modifier.height(8.dp))

            PalindromeButton(
                label = "NEXT",
                onClick = { onNextScreen(uiState.name) }
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PalindromeScreenPreview() {
    SuitmediaTheme {
        PalindromeScreen(onNextScreen = {})
    }
}