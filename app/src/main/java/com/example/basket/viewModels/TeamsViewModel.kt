package com.example.basket.viewModels

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_domain.interfaces.IBasketRepository
import com.example.app_domain.models.Team
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(private val basketRepository: IBasketRepository) :ViewModel() {

    private val teams: MutableLiveData<List<Team>> = MutableLiveData<List<Team>>()

    fun getTeams(): LiveData<List<Team>>{
        return teams
    }

    fun loadTeams(){
        viewModelScope.launch {
            teams.postValue(basketRepository.getTeams())
        }
    }
}
