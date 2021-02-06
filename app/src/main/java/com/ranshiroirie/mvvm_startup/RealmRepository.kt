package com.ranshiroirie.mvvm_startup

import android.content.Context
import com.ranshiroirie.mvvm_startup.model.MainModel
import com.ranshiroirie.mvvm_startup.model.RealmDB
import io.realm.Realm
import io.realm.RealmConfiguration

object RealmRepository  {
    private lateinit var realm : Realm
    private const val defaultNumText : Int = 0

    fun initRealm(applicationContext: Context){
        Realm.init(applicationContext)
        //Realmのデータベース構造が変わった時の対処
        val config = RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build()
        Realm.setDefaultConfiguration(config)
    }

    fun resetRealm(applicationContext: Context){
        val config = RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build()
        Realm.deleteRealm(config)
        initRealm(applicationContext)
    }

    fun getWord() : MainModel {
        startRealm()
        var selectedDB = realm.where(RealmDB::class.java).equalTo(RealmDB::numText.name, defaultNumText).findFirst()
        if(selectedDB == null){
            addWord()
            selectedDB = realm.where(RealmDB::class.java).equalTo(RealmDB::numText.name, defaultNumText).findFirst()!!
        }
        val getMainModel = MainModel(selectedDB.text1, selectedDB.text2)
        closeRealm()
        return getMainModel
    }

    fun updateWord(setMainModel: MainModel) {
        startRealm()
        val selectedDB = realm.where(RealmDB::class.java).equalTo(RealmDB::numText.name, defaultNumText).findFirst()!!
        realm.beginTransaction()
        selectedDB.text1 = setMainModel.text1
        selectedDB.text2 = setMainModel.text2
        realm.commitTransaction()
        closeRealm()
    }

    private fun addWord(){
        realm.beginTransaction()
        realm.createObject(RealmDB::class.java, defaultNumText)
        realm.commitTransaction()
    }

    private fun startRealm(){
        realm = Realm.getDefaultInstance()
    }
    private fun closeRealm(){
        realm.close()
    }
}