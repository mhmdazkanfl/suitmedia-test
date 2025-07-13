package com.nakersolutionid.suitmedia.ui.component.palindrome

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nakersolutionid.suitmedia.R
import com.nakersolutionid.suitmedia.ui.theme.SuitmediaTheme

@Composable
fun PalindromeInput(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    imeAction: ImeAction,
    focusManager: FocusManager
) {
    val keyAction = when (imeAction) {
        ImeAction.Done -> KeyboardActions(
            onDone = { focusManager.clearFocus() }
        )
        ImeAction.Next -> KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        )
        else -> KeyboardActions.Default
    }

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = label,
                color = Color.Black.copy(alpha = 0.25f),
                style = MaterialTheme.typography.bodyLarge
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        shape = RoundedCornerShape(16.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = imeAction
        ),
        keyboardActions = keyAction
    )
}

@Preview
@Composable
private fun PalindromeInputPreview() {
    SuitmediaTheme {
        val focusManager = LocalFocusManager.current
        var input by rememberSaveable { mutableStateOf("") }
        PalindromeInput(
            modifier = Modifier,
            value = input,
            label = stringResource(R.string.tf_name),
            onValueChange = { input = it },
            imeAction = ImeAction.Next,
            focusManager = focusManager
        )
    }
}