package com.notes.notes.edits.fragment

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
import com.notes.notes.database.ModelListItems
import com.notes.notes.databinding.FragmentEditsBinding
import com.notes.notes.edits.viewmodel.ListViewModelFactory
import com.notes.notes.edits.viewmodel.ViewModelEdits

class EditsFragment : Fragment() {

    private val viewModelEdits: ViewModelEdits by viewModels {
        ListViewModelFactory((activity?.application as App).repository)
    }

    private var _binding: FragmentEditsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        observeViewModel()
    }

    private fun setupUi() {
        binding.toolbar.apply {
            setNavigationOnClickListener {
                activity?.onBackPressed()
            }
            menu.findItem(R.id.action_settings).apply {
                setOnMenuItemClickListener {
                    findNavController().navigate(R.id.action_EditsFragment_to_SettingsFragment)
                    true
                }
            }
            var count: Long = 1
            menu.findItem(R.id.action_create).apply {
                setOnMenuItemClickListener {
                    count++
                    if (binding.edittextTitle.text.isNotEmpty()) {
                        val m = ModelListItems(
                            count,
                            binding.edittextTitle.text.toString(),
                            binding.edittextBody.text.toString()
                        )
                        viewModelEdits.insert(m)

                        findNavController().navigate(R.id.action_EditsFragment_to_ListFragment)
                    }
                    true
                }
            }
        }
    }

    private fun observeViewModel() {
        viewModelEdits.allLists.observe(viewLifecycleOwner) { lists ->
            lists.let {
                Log.d("note", "${it.forEach { act -> print("$act") }} ${it.firstOrNull()?.title} ")
                it.forEach { act ->
                    Log.d("note", "$it  $act" )
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}