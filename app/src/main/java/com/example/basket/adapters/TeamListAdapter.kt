package com.example.basket.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_domain.models.Team
import com.example.basket.databinding.TeamItemBinding

class TeamListAdapter : RecyclerView.Adapter<TeamListAdapter.TeamViewHolder>(){
    private lateinit var context: Context
    var teamList: List<Team> = listOf()
        set(value){
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val binding = TeamItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        context = parent.context
        return TeamViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bind(teamList[position])
    }

    override fun getItemCount(): Int {
        return teamList.size
    }

    inner class TeamViewHolder (private val binding: TeamItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(team : Team){
            binding.apply {
                textViewAbbreviation.text = team.abbreviation
                textViewName.text = "${team.name} ${team.city}"
                //Glide.with(context).load(beer.imageUrl).into(imageViewBeer)
               /* root.setOnClickListener{
                    listener?.invoke(team)
                }*/
            }
        }
    }

}