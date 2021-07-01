package com.example.basket.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_domain.models.Player
import com.example.basket.databinding.PlayerItemBinding

class PlayerListAdapter : RecyclerView.Adapter<PlayerListAdapter.PlayerViewHolder>(){
    private lateinit var context: Context
    var listener: ((player : Player) -> Unit)? = null
    var playerList: MutableList<Player> = mutableListOf()
        set(value){
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val binding = PlayerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        context = parent.context
        return PlayerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(playerList[position])
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    fun addPlayers(newPlayers: List<Player>){
        val dim = playerList.size
        if (newPlayers.size > playerList.size)
            playerList.addAll(newPlayers.subList(dim,newPlayers.size-1))
        else
            playerList.addAll(newPlayers)
        notifyItemInserted(playerList.size - dim)
        //notifyDataSetChanged()
    }

    fun clearPlayers(){
        playerList.clear()
        notifyDataSetChanged()
    }

    inner class PlayerViewHolder(private val binding: PlayerItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(player : Player){
            binding.apply {
                textViewLastName.text = player.lastName
                textViewFirstName.text = player.firstName
                //Glide.with(context).load(beer.imageUrl).into(imageViewBeer)
                root.setOnClickListener{
                    listener?.invoke(player)
                }
            }
        }
    }
}
