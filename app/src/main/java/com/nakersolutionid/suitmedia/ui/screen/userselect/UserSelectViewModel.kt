package com.nakersolutionid.suitmedia.ui.screen.userselect

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.nakersolutionid.suitmedia.data.UserRepository

class UserSelectViewModel(private val userRepository: UserRepository) : ViewModel() {
    val pagingUsers = userRepository.getUsers().cachedIn(viewModelScope)
}