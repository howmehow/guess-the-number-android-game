package com.howmehow.android.myapplication4

import android.content.DialogInterface
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), Contract.View {

    private val presenter: Contract.Presenter by lazy {
        val presenter = Presenter()
        presenter.init(this)
        presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setViews()
    }

    private fun setViews() {
        main_activity_send_button.setOnClickListener {
            presenter.onButtonPressed(main_activity_insert_number_edittext.text.toString())
        }
        findViewById<EditText>(R.id.main_activity_insert_number_edittext).setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {
                    presenter.onButtonPressed(main_activity_insert_number_edittext.text.toString())
                    true
                }
                else -> false
            }
        }
    }

    override fun updateAttemptsCounter(attempts: Int) {
        main_activity_how_many_attempts_textview.text =
            getString(R.string.main_activity_number_of_attempts, attempts)
    }

    override fun cleanTheView() {
        updateAttemptsCounter(0)
    }

    override fun showUserFeedback(@StringRes feedback: Int) {
        Toast.makeText(this, feedback, Toast.LENGTH_LONG).show()
    }

    override fun showEndMessage(attempts: Int) {
        AlertDialog.Builder(this)
            .setTitle(R.string.title_for_end_message)
            .setMessage(getString(R.string.message_for_end_message, attempts))
            .setPositiveButton(android.R.string.ok, DialogInterface.OnClickListener {
                    dialog, which ->  cleanTheView()})
            .setIcon(android.R.drawable.ic_dialog_info)
            .show()
    }


}