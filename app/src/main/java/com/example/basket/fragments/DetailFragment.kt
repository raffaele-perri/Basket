package com.example.basket.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_domain.models.Player
import com.example.basket.R
import com.example.basket.adapters.PlayerListAdapter
import com.example.basket.databinding.FragmentDetailBinding
import com.example.basket.databinding.FragmentPlayersBinding
import com.example.basket.viewModels.DetailViewModel
import com.example.basket.viewModels.PlayersViewModel
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class DetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val model: DetailViewModel by viewModels()
    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var playerDetail: Player
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.getPlayer().observe(viewLifecycleOwner, { player ->
            binding.progressBar.visibility = View.GONE
            loadSpecs(player)
            Log.d("TEAM", "team -> :  ${player.lastName}")
        })

        model.loadPlayer(args.playerId)
    }

    private fun loadSpecs(player: Player){
        binding.textViewDetailFirstName.text = player.firstName
        binding.textViewDetailHeight.text = player.height_feet.toString()
        binding.textViewDetailID.text = player.id.toString()
        binding.textViewDetailLastName.text = player.lastName
        binding.textViewDetailPosition.text = player.position
        binding.textViewDetailTeam.text = "${player.team.name} ${player.team.city}"

        binding.textViewDetailWeight.text = player.weightPounds.toString()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}