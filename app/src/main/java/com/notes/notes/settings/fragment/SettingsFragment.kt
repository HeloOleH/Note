package com.notes.notes.settings.fragment
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.notes.notes.databinding.SettingFragmentBinding
import com.notes.notes.settings.presenter.Presenter
import com.notes.notes.settings.presenter.PresenterImp
import java.util.*

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

        presenter = PresenterImp(this, context?.applicationContext)
        return binding.root
    }

    override fun showToast(message: String) {
        Toast.makeText(activity, "save new changed", Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
    }

    private fun setupUi() {
        binding.toolbar.apply {
            setNavigationOnClickListener {
                activity?.onBackPressed()
            }
        }

        binding.switchTheme.apply {
            setOnCheckedChangeListener { buttonView, isChecked ->
                presenter.switchTheme(isChecked)
            }
        }

        binding.switchLanguage.apply {
            setOnCheckedChangeListener { buttonView, isChecked ->
                presenter.switchLanguage(buttonView.isChecked)

                if (buttonView.isChecked) {
                    val res: Resources = context.resources
                    val dm: DisplayMetrics = res.displayMetrics
                    val conf: Configuration = res.configuration
                    conf.setLocale(Locale("en".lowercase()))
                    res.updateConfiguration(conf, dm)

                    context.resources.configuration.setLocale(Locale("en".lowercase()))
                } else {
                    val res: Resources = context.resources
                    val dm: DisplayMetrics = res.displayMetrics
                    val conf: Configuration = res.configuration
                    conf.setLocale(Locale("ua".lowercase()))
                    res.updateConfiguration(conf, dm)
                    activity?.recreate()
                }
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