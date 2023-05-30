package com.example.agencedrive.models

class Publications {

    var agenceName: String
    var message: String
    var datePub: String

    constructor(agenceName : String,message:String,datePub:String){
        this.agenceName=agenceName
        this.message=message
        this.datePub=datePub
    }
}