package com.notes.notes.settings.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.notes.notes.R
import com.notes.notes.databinding.SettingFragmentBinding
import com.notes.notes.settings.presenter.Presenter
import com.notes.notes.settings.presenter.PresenterImp
import com.notes.notes.utils.PREFERENCES_KEY_IS_CHECKED_LANGUAGE
import com.notes.notes.utils.PREFERENCES_KEY_IS_CHECKED_THEME
import com.notes.notes.utils.readPreferences
import com.notes.notes.utils.writePreferences

class SettingsFragment : Fragment(), com.notes.notes.settings.presenter.View {

    private lateinit var presenter: Presenter

    private var _binding: SettingFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SettingFragmentBinding.inflate(inflater, container, false)

        presenter = PresenterImp(this, requireContext().applicationContext)
        return binding.root
    }

    override fun showToast(message: Int) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
    }

    private fun setupUi() {
        binding.toolbar.apply {
            setNavigationOnClickListener {
                showToast(R.string.all_settings_saved)
                activity?.onBackPressed()
            }
        }

        binding.switchTheme.apply {
            isChecked = readPreferences(this.context, PREFERENCES_KEY_IS_CHECKED_THEME)

            setOnCheckedChangeListener { buttonView, isChecked ->
                presenter.switchTheme(isChecked)
                writePreferences(this.context, PREFERENCES_KEY_IS_CHECKED_THEME, isChecked)
            }
        }

        binding.switchLanguage.apply {
            isChecked = readPreferences(this.context, PREFERENCES_KEY_IS_CHECKED_LANGUAGE)

            setOnCheckedChangeListener { buttonView, isChecked ->
                presenter.switchLanguage(buttonView.isChecked)
                writePreferences(this.context, PREFERENCES_KEY_IS_CHECKED_LANGUAGE, isChecked)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}