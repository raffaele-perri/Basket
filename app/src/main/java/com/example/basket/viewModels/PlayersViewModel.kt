package com.example.basket.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_domain.interfaces.IBasketRepository
import com.example.app_domain.models.APIPlayerData
import com.example.app_domain.models.Meta
import com.example.app_domain.models.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PlayersViewModel @Inject constructor(private val basketRepository: IBasketRepository) :
    ViewModel() {

    private val players: MutableLiveData<MutableList<Player>> = MutableLiveData<MutableList<Player>>()
    private val meta: MutableLiveData<Meta> = MutableLiveData()
    fun getPlayers(): LiveData<MutableList<Player>> {
        return players
    }

    fun getMeta(): Meta{
        return meta.value!!
    }

    fun loadPlayers(){
        viewModelScope.launch {
            players.postValue(basketRepository.getPlayers(1,3) as MutableList<Player>?)
        }
    }

    fun loadPlayers(page:Int, perPage:Int,search:String){
        viewModelScope.launch {
            val resp = basketRepository.getPlayers(page,perPage,search)
            players.postValue(resp.data as MutableList<Player>?)
            meta.postValue(resp.meta)
        }
    }

    fun loadOtherPlayers(page:Int, perPage:Int,search:String){
        viewModelScope.launch {
            val resp = basketRepository.getPlayers(page,perPage,search)
            val newPlayers = resp.data
            val oldPlayers = players.value
            oldPlayers?.addAll(newPlayers)
            players.postValue(oldPlayers)
            meta.postValue(resp.meta)
        }
    }

    fun filterPlayers(query: String): List<Player>? {
        return players.value?.filter {
            it.lastName.toUpperCase().contains(query.toUpperCase())
                    || it.firstName.toUpperCase().contains(query.toUpperCase())
        }
    }

}
