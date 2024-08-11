package com.juanlucena.personsproject.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.juanlucena.personsproject.data.database.entity.DobEntity
import com.juanlucena.personsproject.data.database.entity.IdentificationEntity
import com.juanlucena.personsproject.data.database.entity.LocationEntity
import com.juanlucena.personsproject.data.database.entity.LoginEntity
import com.juanlucena.personsproject.data.database.entity.NameEntity
import com.juanlucena.personsproject.data.database.entity.PictureEntity
import com.juanlucena.personsproject.data.database.entity.RegisteredEntity

class PersonConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromNameEntity(nameEntity: NameEntity): String {
        return gson.toJson(nameEntity)
    }

    @TypeConverter
    fun toNameEntity(json: String): NameEntity {
        return gson.fromJson(json, NameEntity::class.java)
    }

    @TypeConverter
    fun fromLocationEntity(locationEntity: LocationEntity): String {
        return gson.toJson(locationEntity)
    }

    @TypeConverter
    fun toLocationEntity(json: String): LocationEntity {
        return gson.fromJson(json, LocationEntity::class.java)
    }

    @TypeConverter
    fun fromLoginEntity(loginEntity: LoginEntity): String {
        return gson.toJson(loginEntity)
    }

    @TypeConverter
    fun toLoginEntity(json: String): LoginEntity {
        return gson.fromJson(json, LoginEntity::class.java)
    }

    @TypeConverter
    fun fromDobEntity(dobEntity: DobEntity): String {
        return gson.toJson(dobEntity)
    }

    @TypeConverter
    fun toDobEntity(json: String): DobEntity {
        return gson.fromJson(json, DobEntity::class.java)
    }

    @TypeConverter
    fun fromRegisteredEntity(registeredEntity: RegisteredEntity): String {
        return gson.toJson(registeredEntity)
    }

    @TypeConverter
    fun toRegisteredEntity(json: String): RegisteredEntity {
        return gson.fromJson(json, RegisteredEntity::class.java)
    }

    @TypeConverter
    fun fromPictureEntity(pictureEntity: PictureEntity): String {
        return gson.toJson(pictureEntity)
    }

    @TypeConverter
    fun toPictureEntity(json: String): PictureEntity {
        return gson.fromJson(json, PictureEntity::class.java)
    }

    @TypeConverter
    fun fromIdentificationEntity(identificationEntity: IdentificationEntity): String {
        return gson.toJson(identificationEntity)
    }

    @TypeConverter
    fun toIdentificationEntity(json: String): IdentificationEntity {
        return gson.fromJson(json, IdentificationEntity::class.java)
    }
}