package com.juanlucena.personsproject.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanlucena.personsproject.data.database.entity.toModel
import com.juanlucena.personsproject.data.dtomodel.toDomain
import com.juanlucena.personsproject.data.repository.PersonRepositoryImpl
import com.juanlucena.personsproject.domain.InfoModel
import com.juanlucena.personsproject.domain.PersonModel
import com.juanlucena.personsproject.domain.PersonResponseModel
import com.juanlucena.personsproject.ui.states.PersonListUiState
import com.juanlucena.personsproject.utils.Result
import com.juanlucena.personsproject.utils.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(private val personRepositoryImpl: PersonRepositoryImpl) :
    ViewModel() {

    private val _personListFlow = MutableStateFlow<PersonListUiState>(
        PersonListUiState.Success(
            PersonResponseModel(results = emptyList(), info = InfoModel("", 0, 0, 0.0))
        )
    )
    val personListFlow: StateFlow<PersonListUiState> = _personListFlow.asStateFlow()

    private val _selectedPerson = MutableStateFlow<PersonModel?>(null)
    val selectedPerson: StateFlow<PersonModel?> = _selectedPerson.asStateFlow()

    private val _personSavedInDbListFlow = MutableStateFlow<PersonListUiState>(
        PersonListUiState.SuccessDb(emptyList())
    )
    val savedPersonsInDb: StateFlow<PersonListUiState> = _personSavedInDbListFlow

    init {
        getPersons()
    }

    fun selectPerson(personModel: PersonModel) {
        viewModelScope.launch {
            _selectedPerson.value = personModel
        }
    }

    private fun getPersons() {
        viewModelScope.launch {
            personRepositoryImpl.getPersons().asResult().collect { result ->
                _personListFlow.update {
                    when (result) {
                        is Result.Loading -> {
                            Log.i("STATE", "LOADING")
                            PersonListUiState.Loading
                        }

                        is Result.Success -> {
                            Log.i("STATE", "SUCCESS")
                            PersonListUiState.Success(result.data.toDomain())
                        }

                        is Result.Error -> {
                            Log.i("STATE", result.exception!!.message!!)
                            PersonListUiState.Error(result.exception)
                        }
                    }
                }
            }
        }
    }

    fun addPersonToFavorites(personModel: PersonModel) {
        viewModelScope.launch {
            personRepositoryImpl.addPersonToFavorites(personModel)
        }
    }

    fun getSavedPersons() {
        viewModelScope.launch {

            _personSavedInDbListFlow.value = PersonListUiState.Loading

            personRepositoryImpl.getSavedPersons()
                .map { entityList ->
                    entityList.map { entityItem -> entityItem.toModel() }
                }
                .catch { throwable ->
                    _personSavedInDbListFlow.value = PersonListUiState.Error(throwable)
                }
                .collect { personModelList ->
                    _personSavedInDbListFlow.value = PersonListUiState.SuccessDb(personModelList)
                }
        }
    }

    fun deletePersonFromFavorites(person: PersonModel) {
        viewModelScope.launch {
            personRepositoryImpl.deletePersonFromFavorites(person)
        }
    }
}