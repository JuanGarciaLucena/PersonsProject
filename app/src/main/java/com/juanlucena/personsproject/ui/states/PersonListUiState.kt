package com.juanlucena.personsproject.ui.states

import com.juanlucena.personsproject.domain.PersonModel
import com.juanlucena.personsproject.domain.PersonResponseModel

interface PersonListUiState {

    object Loading: PersonListUiState
    data class Success(val data: PersonResponseModel) : PersonListUiState
    data class SuccessDb(val data: List<PersonModel>) : PersonListUiState
    data class Error(val throwable: Throwable? = null): PersonListUiState
}