package com.example.basket.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_domain.models.Player
import com.example.basket.databinding.PlayerItemBinding
import com.example.basket.databinding.TeamItemBinding

class PlayerListAdapter : RecyclerView.Adapter<PlayerListAdapter.PlayerViewHolder>(){
    private lateinit var context: Context
    // var listener: ((team : Team) -> Unit)? = null
    var playerList: List<Player> = listOf()
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

    inner class PlayerViewHolder(private val binding: PlayerItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(player : Player){
            binding.apply {
                textViewLastName.text = player.lastName
                textViewFirstName.text = player.firstName
                //Glide.with(context).load(beer.imageUrl).into(imageViewBeer)
                /* root.setOnClickListener{
                     listener?.invoke(team)
                 }*/
            }
        }
    }
}
