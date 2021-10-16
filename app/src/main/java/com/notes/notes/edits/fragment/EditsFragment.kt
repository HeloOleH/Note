package com.notes.notes.edits.fragment

import android.os.Bundle
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
import com.notes.notes.utils.BUNDLE_IS_ADD_OR_EDIT_MODE
import com.notes.notes.utils.BUNDLE_LIST_POSITION
import com.notes.notes.utils.EMPTY

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

            menu.findItem(R.id.action_create).apply {
                setOnMenuItemClickListener {
                    if (binding.edittextTitle.text.isNotEmpty()) {
                        val m = ModelListItems(
                            getPositionFromArguments(),
                            binding.edittextTitle.text.toString(),
                            binding.edittextBody.text.toString()
                        )

                        if (getIsAddOrEditFromArguments()) {
                            viewModelEdits.insert(m) // for add new list item
                        } else {
                            viewModelEdits.update(m) // for Edits old list item
                        }
                        findNavController().navigate(R.id.action_EditsFragment_to_ListFragment)
                    }
                    true
                }
            }
        }
    }

    private fun observeViewModel() {
        viewModelEdits.getModelById(getPositionFromArguments())
            .observe(viewLifecycleOwner) { listItem ->
                listItem.let {
                    if (getIsAddOrEditFromArguments()) {
                        binding.edittextTitle.setText(EMPTY)
                        binding.edittextBody.setText(EMPTY)
                    } else {
                        binding.edittextTitle.setText(it.title)
                        binding.edittextBody.setText(it.body)
                    }
                }
            }
    }

    private fun getPositionFromArguments() =
        arguments?.getInt(BUNDLE_LIST_POSITION, 1)?.toLong() ?: 1

    private fun getIsAddOrEditFromArguments() =
        arguments?.getBoolean(BUNDLE_IS_ADD_OR_EDIT_MODE, false) ?: false


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}