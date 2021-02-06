package com.ranshiroirie.mvvm_startup.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.ranshiroirie.mvvm_startup.MainViewModel
import com.ranshiroirie.mvvm_startup.R

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels(
        factoryProducer = {defaultViewModelProviderFactory}
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, MainFragment())
                .commit()
        }
    }

    fun onClickButtonNext(view: View) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, SecondFragment()).addToBackStack(null)
            .commit()
    }

    fun onClickButtonBack(view: View) {
        supportFragmentManager.popBackStack()
    }

    fun showDialogConfirmSave(view: View){
        val dialogBuilder = AlertDialog.Builder(ContextThemeWrapper(this@MainActivity,
            R.style.Theme_AppCompat_DayNight_Dialog
        ))
            .setMessage("データを保存してもよろしいですか？")
            .setPositiveButton("OK") { _, _ ->
                onClickButtonSave()
            }
            .setNegativeButton("キャンセル"){ _, _ -> }
        dialogBuilder.show()
    }

    fun showDialogConfirmReset(view: View){
        val dialogBuilder = AlertDialog.Builder(ContextThemeWrapper(this@MainActivity,
            R.style.Theme_AppCompat_DayNight_Dialog
        ))
            .setMessage("データを初期化してもよろしいですか？")
            .setPositiveButton("OK") { _, _ ->
                onClickButtonReset()
            }
            .setNegativeButton("キャンセル") { _, _ -> }
        dialogBuilder.show()
    }

    private fun onClickButtonSave(){
        viewModel.saveTextData()
        Toast.makeText(this,"データを保存しました", Toast.LENGTH_LONG).show()
        supportFragmentManager.popBackStack()
    }

    private fun onClickButtonReset(){
        viewModel.resetTextData(applicationContext)
        Toast.makeText(this,"データを初期化しました", Toast.LENGTH_LONG).show()
    }
}