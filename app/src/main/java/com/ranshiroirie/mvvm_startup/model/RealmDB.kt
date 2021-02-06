package com.ranshiroirie.mvvm_startup.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RealmDB : RealmObject(){
    @PrimaryKey
    open var numText: Int = 0
    open var text1 : String = "初期値"
    open var text2 : String = "default"
}