package com.juanlucena.personsproject.data.dtomodel

import com.google.gson.annotations.SerializedName
import com.juanlucena.personsproject.domain.CoordinatesModel
import com.juanlucena.personsproject.domain.DobModel
import com.juanlucena.personsproject.domain.IdModel
import com.juanlucena.personsproject.domain.InfoModel
import com.juanlucena.personsproject.domain.LocationModel
import com.juanlucena.personsproject.domain.LoginModel
import com.juanlucena.personsproject.domain.NameModel
import com.juanlucena.personsproject.domain.PersonResponseModel
import com.juanlucena.personsproject.domain.PictureModel
import com.juanlucena.personsproject.domain.RegisteredModel
import com.juanlucena.personsproject.domain.PersonModel
import com.juanlucena.personsproject.domain.StreetModel
import com.juanlucena.personsproject.domain.TimezoneModel

data class PersonDtoModel (
    @SerializedName("results") val results : List<Results>,
    @SerializedName("info") val info : Info
)

data class Results (

    @SerializedName("gender") val gender : String,
    @SerializedName("name") val name : Name,
    @SerializedName("location") val location : Location,
    @SerializedName("email") val email : String,
    @SerializedName("login") val login : Login,
    @SerializedName("dob") val dob : Dob,
    @SerializedName("registered") val registered : Registered,
    @SerializedName("phone") val phone : String,
    @SerializedName("cell") val cell : String,
    @SerializedName("id") val id : Id,
    @SerializedName("picture") val picture : Picture,
    @SerializedName("nat") val nat : String
)

data class Info (

    @SerializedName("seed") val seed : String,
    @SerializedName("results") val results : Int,
    @SerializedName("page") val page : Int,
    @SerializedName("version") val version : Double
)

data class Name (

    @SerializedName("title") val title : String,
    @SerializedName("first") val first : String,
    @SerializedName("last") val last : String
)

data class Location (

    @SerializedName("street") val street : Street,
    @SerializedName("city") val city : String,
    @SerializedName("state") val state : String,
    @SerializedName("country") val country : String,
    @SerializedName("postcode") val postcode : String,
    @SerializedName("coordinates") val coordinates : Coordinates,
    @SerializedName("timezone") val timezone : Timezone
)

data class Login (

    @SerializedName("uuid") val uuid : String,
    @SerializedName("username") val username : String,
    @SerializedName("password") val password : String,
    @SerializedName("salt") val salt : String,
    @SerializedName("md5") val md5 : String,
    @SerializedName("sha1") val sha1 : String,
    @SerializedName("sha256") val sha256 : String
)

data class Dob (

    @SerializedName("date") val date : String,
    @SerializedName("age") val age : Int
)

data class Registered (

    @SerializedName("date") val date : String,
    @SerializedName("age") val age : Int
)

data class Id (

    @SerializedName("name") val name : String,
    @SerializedName("value") val value : String
)

data class Picture (

    @SerializedName("large") val large : String,
    @SerializedName("medium") val medium : String,
    @SerializedName("thumbnail") val thumbnail : String
)

data class Street (

    @SerializedName("number") val number : Int,
    @SerializedName("name") val name : String
)

data class Coordinates (

    @SerializedName("latitude") val latitude : Double,
    @SerializedName("longitude") val longitude : Double
)

data class Timezone (

    @SerializedName("offset") val offset : String,
    @SerializedName("description") val description : String
)

fun PersonDtoModel.toDomain() = PersonResponseModel(
    results = results.map { resultItem -> resultItem.toDomain()},
    info = info.toDomain()
)

fun Results.toDomain() = PersonModel(
    gender = gender,
    name = name.toDomain(),
    location = location.toDomain(),
    email = email,
    login = login.toDomain(),
    dob = dob.toDomain(),
    registered = registered.toDomain(),
    phone = phone,
    cell = cell,
    id = id.toDomain(),
    picture = picture.toDomain(),
    nat = nat,
    isFavorite = false
)

fun Info.toDomain() = InfoModel(
    seed = seed,
    results = results,
    page = page,
    version = version
)

fun Name.toDomain() = NameModel(
    title = title,
    first = first,
    last = last
)

fun Location.toDomain() = LocationModel(
    street = street.toDomain(),
    city = city,
    state = state,
    country = country,
    postcode = postcode,
    coordinates = coordinates.toDomain(),
    timezone = timezone.toDomain()

)

fun Login.toDomain() = LoginModel(
    uuid = uuid,
    username = username,
    password = password,
    salt = salt,
    md5 = md5,
    sha1 = sha1,
    sha256 = sha256
)

fun Dob.toDomain() = DobModel(
    date = date,
    age = age
)

fun Registered.toDomain() = RegisteredModel(
    date = date,
    age = age
)

fun Id.toDomain() = IdModel(
    name = name ?: "",
    value = value ?: ""
)

fun Picture.toDomain() = PictureModel(
    large = large,
    medium = medium,
    thumbnail = thumbnail
)

fun Street.toDomain() = StreetModel(
    number = number,
    name = name
)

fun Coordinates.toDomain() = CoordinatesModel(
    latitude = latitude,
    longitude = longitude
)

fun Timezone.toDomain() = TimezoneModel(
    offset = offset,
    description = description
)

