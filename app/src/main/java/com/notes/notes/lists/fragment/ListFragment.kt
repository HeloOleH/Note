package com.notes.notes.lists.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.notes.notes.App
import com.notes.notes.R
import com.notes.notes.databinding.ListFragmentBinding
import com.notes.notes.lists.adapters.RecyclerViewAdapter
import com.notes.notes.lists.viewholder.RecyclerOnClickListener
import com.notes.notes.lists.viewmodel.ListViewModel
import com.notes.notes.lists.viewmodel.ListViewModelFactory
import com.notes.notes.lists.viewmodel.UiEvent

class ListFragment : Fragment(), RecyclerOnClickListener {

    private val viewModelList: ListViewModel by viewModels {
        ListViewModelFactory((activity?.application as App).repository)
    }

    private var _binding: ListFragmentBinding? = null
    private val binding get() = _binding!!

    private val customAdapter = RecyclerViewAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        observeViewModel()
    }

    private fun setupUi() {
        binding.toolbar.apply {
            menu.findItem(R.id.action_settings).apply {
                setOnMenuItemClickListener {
                    findNavController().navigate(R.id.action_ListFragment_to_SettingsFragment)
                    true
                }
            }
            menu.findItem(R.id.action_add).apply {
                setOnMenuItemClickListener {
                    findNavController().navigate(R.id.action_ListFragment_to_EditsFragment)
                    true
                }
            }
        }

        binding.recyclerView.apply {
            adapter = customAdapter
        }
    }

    override fun onListClick(position: Int) {
        Log.d("ListFragment", "click recyclerview: $position")
        findNavController().navigate(R.id.action_ListFragment_to_EditsFragment)

        viewModelList.processUi(UiEvent.ListSelected(position))
    }

    private fun observeViewModel() = with(viewModelList) {
        allLists.observe(viewLifecycleOwner) { lists ->

            lists?.let {
                Log.d("ListFragment note", "${it.firstOrNull()?.body} ${it.firstOrNull()?.title} ")
                customAdapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}