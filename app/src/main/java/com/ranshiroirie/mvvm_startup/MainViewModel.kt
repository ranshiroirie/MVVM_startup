package com.ranshiroirie.mvvm_startup

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ranshiroirie.mvvm_startup.model.MainModel

class MainViewModel(
    handle: SavedStateHandle
) : ViewModel() {

    var liveDataText1: MutableLiveData<String> = handle.getLiveData("liveDataText1")
    var liveDataText2: MutableLiveData<String> = handle.getLiveData("liveDataText2")

    init {
        initTextData()
    }

    private fun initTextData(){
        val initializeData = RealmRepository.getWord()
        liveDataText1.value = initializeData.text1
        liveDataText2.value = initializeData.text2
    }

    fun saveTextData(){
        val setMainModel = MainModel(liveDataText1.value.toString(), liveDataText2.value.toString())
        RealmRepository.updateWord(setMainModel)
    }

    fun resetTextData(applicationContext: Context){
        RealmRepository.resetRealm(applicationContext)
        initTextData()
    }

}