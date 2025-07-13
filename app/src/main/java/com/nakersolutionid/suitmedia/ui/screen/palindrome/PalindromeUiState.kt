package com.nakersolutionid.suitmedia.ui.screen.palindrome

import androidx.compose.runtime.Immutable

@Immutable
data class PalindromeUiState(
    val name: String = "",
    val palindrome: String = "",
    val isPalindrome: Boolean? = null
)
