package com.flickfinder.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.flickfinder.model.Movie;
import com.flickfinder.model.Person;
import com.flickfinder.util.Database;
import com.flickfinder.util.Seeder;

/**
 * TODO: Implement this class
 */
class PersonDAOTest {
	/**
	 * The person data access object.
	 */

	private PersonDAO personDAO;

	/**
	 * Seeder
	 */

	Seeder seeder;

	/**
	 * Sets up the database connection and creates the tables.
	 * We are using an in-memory database for testing purposes.
	 * This gets passed to the Database class to get a connection to the database.
	 * As it's a singleton class, the entire application will use the same
	 * connection.
	 */
	@BeforeEach
	void setUp() {
		var url = "jdbc:sqlite::memory:";
		seeder = new Seeder(url);
		Database.getInstance(seeder.getConnection());
		personDAO = new PersonDAO();

	}

	/**
	 * Tests the getAllPersons method.
	 * We expect to get a list of all persons in the database.
	 * We have seeded the database with 5 persons, so we expect to get 5 persons back.
	 * At this point, we avoid checking the actual content of the list.
	 */
	@Test
	void testGetAllPersons() {
		try {
			List<Person> persons = personDAO.getAllPeople();
			assertEquals(5, persons.size());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}

	/**
	 * Tests the getPersonById method.
	 * We expect to get the person with the specified id.
	 */
	@Test
	void testGetPersonById() {
		Person person;
		try {
			person = personDAO.getPersonById(1);
			assertEquals("Tim Robbins", person.getName());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}

	/**
	 * Tests the getPersonById method with an invalid id. Null should be returned.
	 */
	@Test
	void testGetPersonByIdInvalidId() {
		// write an assertThrows for a SQLException

		try {
			Person person = personDAO.getPersonById(1000);
			assertEquals(null, person);
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}
	
	@Test
    void testGetMoviesStarringPerson() {
        try {
            List<Movie> films = personDAO.getMoviesStarringPerson(4);
            assertEquals(2, films.size(), "Al Pacino should star in 2 movies");
            assertEquals(2, films.get(0).getId());
            assertEquals(3, films.get(1).getId());
        } catch (SQLException e) {
            fail("SQLException thrown");
        }
    }

    @Test
    void testGetMoviesStarringPersonInvalidId() {
        try {
            List<Movie> films = personDAO.getMoviesStarringPerson(3);
            assertEquals(0, films.size(), "Person with no starring roles should yield empty list");
        } catch (SQLException e) {
            fail("SQLException thrown");
        }
    }

	@AfterEach
	void tearDown() {
		seeder.closeConnection();
	}

}