package com.example.agencedrive.models

class Agences {
    var firstName:String
    var lastName : String
    var phone : String
    var email : String
    var password:String

    constructor(firstName: String, lastName: String,  phone: String, email: String,password: String){

        this.firstName = firstName
        this.lastName = lastName
        this.phone = phone
        this.email = email
        this.password = password

    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Agences

        if (email != other.email) return false

        return true
    }

}