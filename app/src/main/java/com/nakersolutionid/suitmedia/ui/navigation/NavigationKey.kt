package com.nakersolutionid.suitmedia.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable data object Palindrome : NavKey
@Serializable data class Welcome(val name: String) : NavKey
@Serializable data object UserSelect : NavKey