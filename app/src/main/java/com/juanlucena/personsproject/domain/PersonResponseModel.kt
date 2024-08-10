package com.juanlucena.personsproject.domain

data class PersonResponseModel (
    val results : List<PersonModel>,
    val info : InfoModel
)

data class PersonModel (

    val gender : String,
    val name : NameModel,
    val location : LocationModel,
    val email : String,
    val login : LoginModel,
    val dob : DobModel,
    val registered : RegisteredModel,
    val phone : String,
    val cell : String,
    val id : IdModel,
    val picture : PictureModel,
    val nat : String
)

data class InfoModel (

    val seed : String,
    val results : Int,
    val page : Int,
    val version : Double
)

data class NameModel (

    val title : String,
    val first : String,
    val last : String
)

data class LocationModel (

    val street : StreetModel,
    val city : String,
    val state : String,
    val country : String,
    val postcode : String,
    val coordinates : CoordinatesModel,
    val timezone : TimezoneModel
)

data class LoginModel (

    val uuid : String,
    val username : String,
    val password : String,
    val salt : String,
    val md5 : String,
    val sha1 : String,
    val sha256 : String
)

data class DobModel (

   val date : String,
   val age : Int
)

data class RegisteredModel (

    val date : String,
    val age : Int
)

data class IdModel (

    val name : String,
    val value : String
)

data class PictureModel (

    val large : String,
    val medium : String,
    val thumbnail : String
)

data class StreetModel (

    val number : Int,
    val name : String
)

data class CoordinatesModel (

    val latitude : Double,
    val longitude : Double
)

data class TimezoneModel (

    val offset : String,
    val description : String
)