package com.juanlucena.personsproject.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persons_table")
data class PersonEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val gender: String,
    val name: NameEntity,
    val location: LocationEntity,
    val email: String,
    val login: LoginEntity,
    val dob: DobEntity,
    val registered: RegisteredEntity,
    val phone: String,
    val cell: String,
    val picture: PictureEntity,
    val nat: String,
    val identificationEntity: IdentificationEntity
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