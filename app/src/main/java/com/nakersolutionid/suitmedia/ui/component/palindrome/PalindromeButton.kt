package com.nakersolutionid.suitmedia.ui.component.palindrome

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nakersolutionid.suitmedia.ui.theme.Button
import com.nakersolutionid.suitmedia.ui.theme.SuitmediaTheme

@Composable
fun PalindromeButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    label: String
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Button
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(label, style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview
@Composable
private fun PalindromeButtonPreview() {
    SuitmediaTheme {
        PalindromeButton(onClick = {}, label = "CHECK")
    }
}