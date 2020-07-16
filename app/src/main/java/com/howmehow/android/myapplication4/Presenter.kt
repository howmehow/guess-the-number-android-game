package com.howmehow.android.myapplication4

import android.view.inputmethod.EditorInfo
import android.widget.EditText

class Presenter : Contract.Presenter {

    private lateinit var view: Contract.View
    private var generatedNumber: Int = 0
    private var attempts: Int = 0

    override fun init(view: Contract.View) {
        this.view = view
        generateNumber()
    }

    override fun onButtonPressed(userInput: String) {
        attempts += 1
        view.updateAttemptsCounter(attempts)
        when {
            userInput.toInt() == generatedNumber -> handleSuccessfulInput()
            userInput.toInt() > generatedNumber -> handleSmallerNumberMessage()
            else -> handleBiggerNumberMessage()
        }
    }

    private fun generateNumber() {
        generatedNumber = (0..100).random()
    }

    private fun resetAttemptsNumber(){
        attempts = 0
    }

    private fun handleSuccessfulInput() {
        view.showEndMessage(attempts)
        generateNumber()
        resetAttemptsNumber()
    }

    private fun handleSmallerNumberMessage() {
        view.showUserFeedback(R.string.smaller_number_message)
    }

    private fun handleBiggerNumberMessage() {
        view.showUserFeedback(R.string.bigger_number_message)
    }
}
//LIST
//attempts should reset after user pressing ok on dialog window - when won
//attempts should reset to 0, but after pressing ok