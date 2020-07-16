package com.howmehow.android.myapplication4

import androidx.annotation.StringRes

interface Contract {
    interface View {
        fun updateAttemptsCounter(attempts: Int)
        fun cleanTheView()
        fun showUserFeedback(@StringRes feedback: Int)
        fun showEndMessage(attempts: Int)
    }

    interface Presenter {
        fun init(view: View)
        fun onButtonPressed(userInput: String)
    }
}
