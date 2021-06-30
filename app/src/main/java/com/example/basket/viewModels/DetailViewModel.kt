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
class DetailViewModel @Inject constructor(private val basketRepository: IBasketRepository) :
    ViewModel() {

    private val player: MutableLiveData<Player> = MutableLiveData<Player>()

    fun getPlayer(): LiveData<Player> {
        return player
    }

    fun loadPlayer(id:Int){
        viewModelScope.launch {
            player.postValue(basketRepository.getPlayerById(id))
        }
    }
}
