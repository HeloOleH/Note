package com.notes.notes.lists.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.notes.notes.R
import com.notes.notes.databinding.ListFragmentBinding
import com.notes.notes.lists.adapters.RecyclerViewAdapter
import com.notes.notes.lists.viewholder.RecyclerOnClickListener
import com.notes.notes.lists.viewmodel.ListViewModel
import com.notes.notes.lists.viewmodel.UiEvent
import com.notes.notes.utils.BUNDLE_IS_ADD_OR_EDIT_MODE
import com.notes.notes.utils.BUNDLE_LIST_POSITION
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ListFragment : Fragment(), RecyclerOnClickListener {

    private val viewModelList by sharedViewModel<ListViewModel>()

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
                    val bundle = Bundle()
                    bundle.putInt(BUNDLE_LIST_POSITION, customAdapter.itemCount + 1)
                    bundle.putBoolean(BUNDLE_IS_ADD_OR_EDIT_MODE, true)

                    findNavController().navigate(R.id.action_ListFragment_to_EditsFragment, bundle)
                    true
                }
            }
        }

        binding.recyclerView.apply {
            adapter = customAdapter
        }
    }

    override fun onListClick(position: Int) {
        val bundle = Bundle()
        bundle.putInt(BUNDLE_LIST_POSITION, position)
        bundle.putBoolean(BUNDLE_IS_ADD_OR_EDIT_MODE, false)

        findNavController().navigate(R.id.action_ListFragment_to_EditsFragment, bundle)
        viewModelList.processUi(UiEvent.ListSelected(position))
    }

    private fun observeViewModel() = with(viewModelList) {
        allLists.observe(viewLifecycleOwner) { lists ->
            lists?.let {
                customAdapter.submitList(it)
            }
        }
        state.observe(viewLifecycleOwner) {
            when (it) {
                UiEvent.Loading -> binding.loadingView.isVisible = true
                UiEvent.Success -> binding.loadingView.isVisible = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}