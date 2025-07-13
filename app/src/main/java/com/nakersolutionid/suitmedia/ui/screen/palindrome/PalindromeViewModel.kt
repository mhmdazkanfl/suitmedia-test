package com.nakersolutionid.suitmedia.ui.screen.palindrome

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PalindromeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PalindromeUiState())
    val uiState: StateFlow<PalindromeUiState> = _uiState.asStateFlow()

    fun onNameChange(name: String) {
        _uiState.update { it.copy(name = name) }
    }

    fun onPalindromeChange(palindrome: String) {
        _uiState.update { it.copy(palindrome = palindrome) }
    }

    fun clearPalindromeState() {
        _uiState.update { it.copy(isPalindrome = null) }
    }

    fun isPalindrome() {
        val currentState = _uiState.value
        // Remove all non-aplhanumeric including space, punctuation, symbol, and convert to lowercase
        val cleanedText = currentState.palindrome.lowercase().replace("[^a-z0-9]".toRegex(), "")

        // An empty string or a single-character string is considered a palindrome.
        if (cleanedText.length <= 1) {
            return _uiState.update { it.copy(isPalindrome = true) }
        }

        val reversedText = cleanedText.reversed()
        return _uiState.update { it.copy(isPalindrome = cleanedText == reversedText) }
    }
}