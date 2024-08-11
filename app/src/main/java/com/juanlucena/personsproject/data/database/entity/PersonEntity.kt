package com.juanlucena.personsproject.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.juanlucena.personsproject.domain.CoordinatesModel
import com.juanlucena.personsproject.domain.DobModel
import com.juanlucena.personsproject.domain.IdModel
import com.juanlucena.personsproject.domain.LocationModel
import com.juanlucena.personsproject.domain.LoginModel
import com.juanlucena.personsproject.domain.NameModel
import com.juanlucena.personsproject.domain.PersonModel
import com.juanlucena.personsproject.domain.PictureModel
import com.juanlucena.personsproject.domain.RegisteredModel
import com.juanlucena.personsproject.domain.StreetModel
import com.juanlucena.personsproject.domain.TimezoneModel

@Entity(tableName = "persons_table")
data class PersonEntity(
    @PrimaryKey val email: String,
    val gender: String,
    val name: NameEntity,
    val location: LocationEntity,
    val login: LoginEntity,
    val dob: DobEntity,
    val registered: RegisteredEntity,
    val phone: String,
    val cell: String,
    val picture: PictureEntity,
    val nat: String,
    val identificationEntity: IdentificationEntity,
    val isFavorite: Boolean
)

data class NameEntity(
    val title: String,
    val first: String,
    val last: String
)

data class StreetEntity(
    val number: Int,
    val name: String
)

data class CoordinatesEntity(
    val latitude: Double,
    val longitude: Double
)

data class TimezoneEntity(
    val offset: String,
    val description: String
)

data class LocationEntity(
    val street: StreetEntity,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val coordinates: CoordinatesEntity,
    val timezone: TimezoneEntity
)

data class LoginEntity(
    val uuid: String,
    val username: String,
    val password: String,
    val salt: String,
    val md5: String,
    val sha1: String,
    val sha256: String
)

data class DobEntity(
    val date: String,
    val age: Int
)

data class RegisteredEntity(
    val date: String,
    val age: Int
)

data class PictureEntity(
    val large: String,
    val medium: String,
    val thumbnail: String
)

data class IdentificationEntity(
    val name: String,
    val value: String
)

fun PersonEntity.toModel(): PersonModel = PersonModel(
    email = email,
    gender = gender,
    name = name.toModel(),
    location = location.toModel(),
    login = login.toModel(),
    dob = dob.toModel(),
    registered = registered.toModel(),
    phone = phone,
    cell = cell,
    picture = picture.toModel(),
    nat = nat,
    id = identificationEntity.toModel(),
    isFavorite = isFavorite
)

fun NameEntity.toModel(): NameModel = NameModel(
    title = title,
    first = first,
    last = last
)

fun StreetEntity.toModel(): StreetModel = StreetModel(
    number = number,
    name = name
)

fun LocationEntity.toModel(): LocationModel = LocationModel(
    street = street.toModel(),
    city = city,
    state = state,
    country = country,
    postcode = postcode,
    coordinates = coordinates.toModel(),
    timezone = timezone.toModel()
)

fun CoordinatesEntity.toModel(): CoordinatesModel = CoordinatesModel(
        latitude = latitude,
        longitude = longitude
    )

fun TimezoneEntity.toModel(): TimezoneModel = TimezoneModel(
        offset = offset,
        description = description
    )

fun LoginEntity.toModel(): LoginModel = LoginModel(
    uuid = uuid,
    username = username,
    password = password,
    salt = salt,
    md5 = md5,
    sha1 = sha1,
    sha256 = sha256
)

fun DobEntity.toModel(): DobModel = DobModel(
    date = date,
    age = age
)

fun RegisteredEntity.toModel(): RegisteredModel = RegisteredModel(
    date = date,
    age = age
)

fun PictureEntity.toModel(): PictureModel = PictureModel(
    large = large,
    medium = medium,
    thumbnail = thumbnail
)

fun IdentificationEntity.toModel(): IdModel = IdModel(
    name = name,
    value = value
)