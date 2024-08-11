package com.juanlucena.personsproject.data.repository

import com.juanlucena.personsproject.data.database.dao.PersonsDao
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
import com.juanlucena.personsproject.data.dtomodel.Coordinates
import com.juanlucena.personsproject.data.dtomodel.Dob
import com.juanlucena.personsproject.data.dtomodel.Id
import com.juanlucena.personsproject.data.dtomodel.Info
import com.juanlucena.personsproject.data.dtomodel.Location
import com.juanlucena.personsproject.data.dtomodel.Login
import com.juanlucena.personsproject.data.dtomodel.Name
import com.juanlucena.personsproject.data.dtomodel.PersonDtoModel
import com.juanlucena.personsproject.data.dtomodel.Picture
import com.juanlucena.personsproject.data.dtomodel.Registered
import com.juanlucena.personsproject.data.dtomodel.Results
import com.juanlucena.personsproject.data.dtomodel.Street
import com.juanlucena.personsproject.data.dtomodel.Timezone
import com.juanlucena.personsproject.data.network.PersonDataSource
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
import com.juanlucena.personsproject.domain.toEntity
import io.mockk.Runs
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class PersonRepositoryImplTest{

    private val personDataSource: PersonDataSource = mockk()
    private val personsDao: PersonsDao = mockk()
    private lateinit var repository: PersonRepositoryImpl

    @Before
    fun setUp() {
        repository = PersonRepositoryImpl(personDataSource, personsDao)
    }

    @After
    fun tearDown() {
        clearMocks(personDataSource, personsDao)
    }

    @Test
    fun `getPersons should return data from API`() = runBlocking {
        // Arrange
        val personDtoModel = PersonDtoModel(
            results = listOf(
                Results(
                    gender = "female",
                    name = Name("Ms", "Jane", "Doe"),
                    location = Location(
                        street = Street(123, "Main Street"),
                        city = "New York",
                        state = "NY",
                        country = "USA",
                        postcode = "10001",
                        coordinates = Coordinates(40.7128, -74.0060),
                        timezone = Timezone("-5:00", "Eastern Time (US & Canada)")
                    ),
                    email = "jane.doe@example.com",
                    login = Login(
                        uuid = "some-uuid-value",
                        username = "janedoe",
                        password = "supersecurepassword",
                        salt = "randomsalt",
                        md5 = "md5hash",
                        sha1 = "sha1hash",
                        sha256 = "sha256hash"
                    ),
                    dob = Dob("1990-01-01", 34),
                    registered = Registered("2015-05-15", 9),
                    phone = "555-1234",
                    cell = "555-5678",
                    id = Id("SSN", "123-45-6789"),
                    picture = Picture(
                        large = "https://example.com/images/large.jpg",
                        medium = "https://example.com/images/medium.jpg",
                        thumbnail = "https://example.com/images/thumbnail.jpg"
                    ),
                    nat = "US"
                )
            ),
            info = Info("exampleSeed", 1, 1, 1.0)
        )

        coEvery { personDataSource.getPersonsFromApi() } returns personDtoModel

        // Act
        val result = repository.getPersons().first()

        // Assert
        assertEquals(personDtoModel, result)
        coVerify { personDataSource.getPersonsFromApi() }
    }

    @Test
    fun `addPersonToFavorites should save person in the database`() = runBlocking {
        // Arrange
        val personModel = PersonModel(
            gender = "female",
            name = NameModel("Ms", "Jane", "Doe"),
            location = LocationModel(
                street = StreetModel(123, "Main Street"),
                city = "New York",
                state = "NY",
                country = "USA",
                postcode = "10001",
                coordinates = CoordinatesModel(40.7128, -74.0060),
                timezone = TimezoneModel("-5:00", "Eastern Time (US & Canada)")
            ),
            email = "jane.doe@example.com",
            login = LoginModel(
                uuid = "some-uuid-value",
                username = "janedoe",
                password = "supersecurepassword",
                salt = "randomsalt",
                md5 = "md5hash",
                sha1 = "sha1hash",
                sha256 = "sha256hash"
            ),
            dob = DobModel("1990-01-01", 34),
            registered = RegisteredModel("2015-05-15", 9),
            phone = "555-1234",
            cell = "555-5678",
            id = IdModel("SSN", "123-45-6789"),
            picture = PictureModel(
                large = "https://example.com/images/large.jpg",
                medium = "https://example.com/images/medium.jpg",
                thumbnail = "https://example.com/images/thumbnail.jpg"
            ),
            nat = "US",
            isFavorite = true
        )

        coEvery { personsDao.savePerson(any()) } just Runs

        // Act
        repository.addPersonToFavorites(personModel)

        // Assert
        coVerify { personsDao.savePerson(personModel.toEntity()) }
    }

    @Test
    fun `getSavedPersons should return data from the database`() = runBlocking {
        // Arrange
        val personEntities = listOf(
            PersonEntity(
                gender = "female",
                name = NameEntity("Ms", "Jane", "Doe"),
                location = LocationEntity(
                    street = StreetEntity(123, "Main Street"),
                    city = "New York",
                    state = "NY",
                    country = "USA",
                    postcode = "10001",
                    coordinates = CoordinatesEntity(40.7128, -74.0060),
                    timezone = TimezoneEntity("-5:00", "Eastern Time (US & Canada)")
                ),
                email = "jane.doe@example.com",
                login = LoginEntity(
                    uuid = "some-uuid-value",
                    username = "janedoe",
                    password = "supersecurepassword",
                    salt = "randomsalt",
                    md5 = "md5hash",
                    sha1 = "sha1hash",
                    sha256 = "sha256hash"
                ),
                dob = DobEntity("1990-01-01", 34),
                registered = RegisteredEntity("2015-05-15", 9),
                phone = "555-1234",
                cell = "555-5678",
                picture = PictureEntity(
                    large = "https://example.com/images/large.jpg",
                    medium = "https://example.com/images/medium.jpg",
                    thumbnail = "https://example.com/images/thumbnail.jpg"
                ),
                nat = "US",
                identificationEntity = IdentificationEntity("SSN", "123-45-6789"),
                isFavorite = true
            )
        )

        coEvery { personsDao.getAllPersons() } returns personEntities

        // Act
        val result = repository.getSavedPersons().first()

        // Assert
        assertEquals(personEntities, result)
        coVerify { personsDao.getAllPersons() }
    }

    @Test
    fun `isPersonFavorite should return correct boolean`() = runBlocking {
        // Arrange
        val email = "jane.doe@example.com"
        coEvery { personsDao.isPersonFavorite(email) } returns flowOf(true)

        // Act
        val result = repository.isPersonFavorite(email).first()

        // Assert
        assertTrue(result)
        coVerify { personsDao.isPersonFavorite(email) }
    }

    @Test
    fun `deletePersonFromFavorites should remove person from the database`() = runBlocking {
        // Arrange
        val personModel = PersonModel(
            gender = "female",
            name = NameModel("Ms", "Jane", "Doe"),
            location = LocationModel(
                street = StreetModel(123, "Main Street"),
                city = "New York",
                state = "NY",
                country = "USA",
                postcode = "10001",
                coordinates = CoordinatesModel(40.7128, -74.0060),
                timezone = TimezoneModel("-5:00", "Eastern Time (US & Canada)")
            ),
            email = "jane.doe@example.com",
            login = LoginModel(
                uuid = "some-uuid-value",
                username = "janedoe",
                password = "supersecurepassword",
                salt = "randomsalt",
                md5 = "md5hash",
                sha1 = "sha1hash",
                sha256 = "sha256hash"
            ),
            dob = DobModel("1990-01-01", 34),
            registered = RegisteredModel("2015-05-15", 9),
            phone = "555-1234",
            cell = "555-5678",
            id = IdModel("SSN", "123-45-6789"),
            picture = PictureModel(
                large = "https://example.com/images/large.jpg",
                medium = "https://example.com/images/medium.jpg",
                thumbnail = "https://example.com/images/thumbnail.jpg"
            ),
            nat = "US",
            isFavorite = true
        )

        coEvery { personsDao.deletePersonFromFavorites(any()) } just Runs

        // Act
        repository.deletePersonFromFavorites(personModel)

        // Assert
        coVerify { personsDao.deletePersonFromFavorites(personModel.toEntity()) }
    }
}