package com.example.basket.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basket.adapters.PlayerListAdapter
import com.example.basket.databinding.FragmentPlayersBinding
import com.example.basket.viewModels.PlayersViewModel
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val PER_PAGE = 10
/**
 * A simple [Fragment] subclass.
 * Use the [PlayersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class PlayersFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private lateinit var recycler: RecyclerView
    private val model: PlayersViewModel by viewModels()
    private var _binding : FragmentPlayersBinding? = null
    private val binding get() = _binding!!
    private var adapter : PlayerListAdapter? = null
    private var search: String = ""
    private var page = 1
    private var isLoading = false

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
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPlayersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(model.getPlayers().value == null){
            model.loadPlayers(page, PER_PAGE,search)
        }
        loading()
//        Log.e("TEXT", "ON VIEW CREATED ", )
        recycler = binding.recyclerView
        adapter = PlayerListAdapter()
        adapter?.listener ={ player->
            val action = PlayersFragmentDirections.actionPlayersFragmentToDetailFragment(player.id)
            findNavController().navigate(action)
        }

        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        recycler.addOnScrollListener(object :PlayerScrollListener(recycler.layoutManager as LinearLayoutManager){
            override fun loadMoreItems() {
                page++
                model.loadOtherPlayers(page, PER_PAGE, search)
                loading()
//                Log.e("TEXT", "LOADMOREITEMS $page", )
            }

            override fun isLastPage(): Boolean {
                return model.getMeta().nextPage == 0 || model.getMeta().nextPage ==  null
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

        })


        model.getPlayers().observe(viewLifecycleOwner, { players ->
            notLoading()
//            Log.e("TEXT", "OBSERVE ", )
            adapter!!.addPlayers(players)
        })


        binding.searchBar.doAfterTextChanged {
            if(!isLoading) {
                adapter!!.clearPlayers()
                page = 1
//                Log.e("TEXT", "DO AFTER TEXT CHANGED: ",)
                search = binding.searchBar.text.toString()
                model.loadPlayers(page, PER_PAGE, search)
                loading()
            }
        }
    }


    private fun loading(){
        isLoading = true
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun notLoading(){
        isLoading = false
        binding.progressBar.visibility = View.GONE
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PlayersFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PlayersFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    abstract inner class PlayerScrollListener (private val layoutManager: LinearLayoutManager): RecyclerView.OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val visibleItemCount = recyclerView.childCount
            val totalItemCount = layoutManager.itemCount
            val firstVisibleItemPos = layoutManager.findFirstVisibleItemPosition()

            if(!isLastPage() && !isLoading()){
                if((visibleItemCount + firstVisibleItemPos) >= totalItemCount && firstVisibleItemPos >= 0)
                    loadMoreItems()

            }
        }

        abstract fun loadMoreItems()

        abstract fun isLastPage(): Boolean

        abstract fun isLoading(): Boolean
    }
}