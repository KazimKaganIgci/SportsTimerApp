package com.kazim.timerapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
  var hareketadi:String="",
  var hareketsaniye:Int=0,
  var harekettekrar:Int=0,
  var hareketmola:Int=0
){
    @PrimaryKey(autoGenerate = true) var id:Int=0
}