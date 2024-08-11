package com.juanlucena.personsproject.domain

import com.juanlucena.personsproject.data.database.entity.CoordinatesEntity
import com.juanlucena.personsproject.data.database.entity.DobEntity
import com.juanlucena.personsproject.data.database.entity.IdentificationEntity
import com.juanlucena.personsproject.data.database.entity.LocationEntity
import com.juanlucena.personsproject.data.database.entity.LoginEntity
import com.juanlucena.personsproject.data.database.entity.NameEntity
import com.juanlucena.personsproject.data.database.entity.PersonEntity
import com.juanlucena.personsproject.data.database.entity.PictureEntity
import com.juanlucena.personsproject.data.database.entity.RegisteredEntity
import com.juanlucena.personsproject.data.database.entity.StreetEntity
import com.juanlucena.personsproject.data.database.entity.TimezoneEntity

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
    val nat : String,
    var isFavorite : Boolean
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

fun PersonModel.toEntity() = PersonEntity(
    gender = gender,
    name = name.toEntity(),
    location = location.toEntity(),
    email = email,
    login = login.toEntity(),
    dob = dob.toEntity(),
    registered = registered.toEntity(),
    phone = phone,
    cell = cell,
    picture = picture.toEntity(),
    nat = nat,
    identificationEntity = id.toEntity(),
    isFavorite = isFavorite
)

fun NameModel.toEntity() = NameEntity(
    title = title,
    first = first,
    last = last
)

fun LocationModel.toEntity() = LocationEntity(
    street = street.toEntity(),
    city = city,
    state = state,
    country = country,
    postcode = postcode,
    coordinates = coordinates.toEntity(),
    timezone = timezone.toEntity()
)

fun StreetModel.toEntity() = StreetEntity(
    number = number,
    name = name
)

fun CoordinatesModel.toEntity() = CoordinatesEntity(
    latitude = latitude,
    longitude = longitude
)

fun TimezoneModel.toEntity() = TimezoneEntity(
    offset = offset,
    description = description
)

fun LoginModel.toEntity() = LoginEntity(
    uuid = uuid,
    username = username,
    password = password,
    salt = salt,
    md5 = md5,
    sha1 = sha1,
    sha256 = sha256
)

fun DobModel.toEntity() = DobEntity(
    date = date,
    age = age
)

fun RegisteredModel.toEntity() = RegisteredEntity(
    date = date,
    age = age
)

fun PictureModel.toEntity() = PictureEntity(
    large = large,
    medium = medium,
    thumbnail = thumbnail
)

fun IdModel.toEntity() = IdentificationEntity(
    name = name,
    value = value
)

