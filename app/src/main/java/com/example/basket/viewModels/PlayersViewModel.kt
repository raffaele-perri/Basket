package com.example.basket.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_domain.interfaces.IBasketRepository
import com.example.app_domain.models.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PlayersViewModel @Inject constructor(private val basketRepository: IBasketRepository) :
    ViewModel() {

    private val players: MutableLiveData<List<Player>> = MutableLiveData<List<Player>>()

    fun getPlayers(): LiveData<List<Player>> {
        return players
    }

    fun loadPlayers(){
        viewModelScope.launch {
            players.postValue(basketRepository.getPlayers())
        }
    }
}
