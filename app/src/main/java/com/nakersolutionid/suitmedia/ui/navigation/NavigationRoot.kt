package com.nakersolutionid.suitmedia.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.nakersolutionid.suitmedia.ui.screen.palindrome.PalindromeScreen
import com.nakersolutionid.suitmedia.ui.screen.userselect.UserSelectScreen
import com.nakersolutionid.suitmedia.ui.screen.welcome.WelcomeScreen

@Composable
fun NavigationRoot(modifier: Modifier = Modifier) {
    val backStack = rememberNavBackStack(Palindrome)

    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSceneSetupNavEntryDecorator(),
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<Palindrome> { PalindromeScreen(onNextScreen = { backStack.add(Welcome(it)) }) }
            entry<Welcome> { key ->
                WelcomeScreen(
                    username = key.name,
                    selectedUser = key.selectedUser,
                    onNavigationBack = { backStack.removeLastOrNull() },
                    onUserSelect = { backStack.add(UserSelect) }
                )
            }
            entry<UserSelect> {
                UserSelectScreen(
                    onNavigationBack = { backStack.removeLastOrNull() },
                    onUserClick = { selectedUser ->
                        // Find the Welcome screen in the back stack
                        val welcomeIndex = backStack.indexOfLast { it is Welcome }
                        if (welcomeIndex != -1) {
                            val welcomeEntry = backStack[welcomeIndex] as Welcome
                            // Remove everything after Welcome (including UserSelect)
                            while (backStack.size > welcomeIndex + 1) {
                                backStack.removeLastOrNull()
                            }
                            // Replace Welcome with updated version
                            backStack[welcomeIndex] = welcomeEntry.copy(selectedUser = "${selectedUser.firstName} ${selectedUser.lastName}")
                        }
                    }
                )
            }
        }
    )
}