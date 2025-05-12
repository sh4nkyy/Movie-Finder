package com.flickfinder.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.flickfinder.dao.PersonDAO;
import com.flickfinder.model.Movie;

import io.javalin.http.Context;

/**
 * TODO: Implement this class
 */
class PersonControllerTest {
	/**
	 *
	 * The context object, later we will mock it.
	 */
	private Context ctx;

	/**
	 * The person data access object.
	 */
	private PersonDAO personDAO;

	/**
	 * The person controller.
	 */

	private PersonController personController;

	@BeforeEach
	void setUp() {
		// We create a mock of the PersonDAO class.
		personDAO = mock(PersonDAO.class);
		// We create a mock of the Context class.
		ctx = mock(Context.class);

		// We create an instance of the PersonController class and pass the mock object
		personController = new PersonController(personDAO);
	}

	/**
	 * Tests the getAllPersons method.
	 * We expect to get a list of all persons in the database.
	 */

	@Test
	void testGetAllPersons() {
		personController.getAllPeople(ctx);
		try {
			verify(personDAO).getAllPeople();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test that the controller returns a 500 status code when a database error
	 * occurs
	 * 
	 * @throws SQLException
	 */
	@Test
	void testThrows500ExceptionWhenGetAllDatabaseError() throws SQLException {
		when(personDAO.getAllPeople()).thenThrow(new SQLException());
		personController.getAllPeople(ctx);
		verify(ctx).status(500);
	}

	/**
	 * Tests the getPersonById method.
	 * We expect to get the person with the specified id.
	 */

	@Test
	void testGetPersonById() {
		when(ctx.pathParam("id")).thenReturn("1");
		personController.getPersonById(ctx);
		try {
			verify(personDAO).getPersonById(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test a 500 status code is returned when a database error occurs.
	 * 
	 * @throws SQLException
	 */

	@Test
	void testThrows500ExceptionWhenGetByIdDatabaseError() throws SQLException {
		when(ctx.pathParam("id")).thenReturn("1");
		when(personDAO.getPersonById(1)).thenThrow(new SQLException());
		personController.getPersonById(ctx);
		verify(ctx).status(500);
	}

	/**
	 * Test that the controller returns a 404 status code when a person is not found
	 * or
	 * database error.
	 * 
	 * @throws SQLException
	 */

	@Test
	void testThrows404ExceptionWhenNoPersonFound() throws SQLException {
		when(ctx.pathParam("id")).thenReturn("1");
		when(personDAO.getPersonById(1)).thenReturn(null);
		personController.getPersonById(ctx);
		verify(ctx).status(404);
	}
	
	@Test
	void testMoviesStarringPerson() throws SQLException {
		when(ctx.pathParam("id")).thenReturn("2");
        List<Movie> mockFilms = List.of(new Movie(10, "Title A", 2001), new Movie(20, "Title B", 2005));
        when(personDAO.getMoviesStarringPerson(2)).thenReturn(mockFilms);
        personController.getMoviesStarringPerson(ctx);
        verify(personDAO).getMoviesStarringPerson(2);
        verify(ctx).json(mockFilms);
	}
	
	void testThrows500ExceptionWhenGetMoviesStarringPersonDatabaseError() throws SQLException {
        when(ctx.pathParam("id")).thenReturn("3");
        when(personDAO.getMoviesStarringPerson(3)).thenThrow(new SQLException());
        personController.getMoviesStarringPerson(ctx);
        verify(ctx).status(500);
        verify(ctx).result("Database error");
    }
}