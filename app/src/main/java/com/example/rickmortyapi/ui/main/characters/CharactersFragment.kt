package com.example.rickmortyapi.ui.main.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.rickmortyapi.MainActivity
import com.example.rickmortyapi.R
import com.example.rickmortyapi.databinding.FragmentCharactersPagedListBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CharactersFragment : Fragment() {

    companion object {
        fun newInstance() = CharactersFragment()
    }

    private var _binding: FragmentCharactersPagedListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CharactersViewModel by viewModels()

//    private val pagedAdapter = CharactersPagedListAdapter()
private val pagedAdapter = CharactersPagedListAdapter { item -> viewModel.onItemClick(item) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val actionBar = (activity as MainActivity?)!!.supportActionBar
        actionBar?.setHomeButtonEnabled(false)
        actionBar?.setDisplayHomeAsUpEnabled(false)

        _binding = FragmentCharactersPagedListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerviewCharacter.adapter = pagedAdapter

        viewModel.pagedCharacters.onEach {
            pagedAdapter.submitData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.characterDetailsFlow.onEach {
            parentFragmentManager.beginTransaction()
                .addToBackStack(this.toString())
                .replace(R.id.container, CharacterDetailsFragment.newInstance(it))
                .commit()
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}