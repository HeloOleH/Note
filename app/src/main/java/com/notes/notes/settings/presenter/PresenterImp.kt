package com.notes.notes.settings.presenter

import android.content.Context
import com.notes.notes.settings.data.DataSettings

class PresenterImp(mView: View, context: Context) : Presenter {
    private var mView: View = mView
    private val mRepository: Repository = DataSettings(context)

    override fun switchTheme(isChecked: Boolean) {
        mRepository.saveTheme(isChecked)
    }

    override fun switchLanguage(isChecked: Boolean) {
        mRepository.saveLanguage(isChecked)
    }

    override fun onDestroy() {

    }
}