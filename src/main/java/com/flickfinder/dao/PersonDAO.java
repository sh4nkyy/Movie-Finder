package com.flickfinder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flickfinder.model.Movie;
import com.flickfinder.model.Person;
import com.flickfinder.util.Database;

/**
 * TODO: Implement this class
 * 
 */
//S1 is to be done in this class
public class PersonDAO {

	// for the must have requirements, you will need to implement the following
	// methods:
	// - getAllPeople()
	// - getPersonById(int id)
	// you will add further methods for the more advanced tasks; however, ensure your have completed 
	// the must have requirements before you start these.  

	/**
	 * The connection to the database.
	 */
	private final Connection connection;

	/**
	 * Constructs a SQLitePersonDAO object and gets the database connection.
	 * 
	 */
	public PersonDAO() {
		Database database = Database.getInstance();
		connection = database.getConnection();
	}

	/**
	 * Returns a list of all people in the database.
	 * 
	 * @return a list of all people in the database
	 * @throws SQLException if a database error occurs
	 */

	public List<Person> getAllPeople(int limit) throws SQLException {
		String sql = "SELECT * FROM people LIMIT ?";
	    try (PreparedStatement ps = connection.prepareStatement(sql)) {
	        ps.setInt(1, limit);
	        try (ResultSet rs = ps.executeQuery()) {
	            List<Person> people = new ArrayList<>();
	            while (rs.next()) {
	                people.add(new Person(
	                    rs.getInt("id"),
	                    rs.getString("name"),
	                    rs.getInt("birth")
	                ));
	            }
	            return people;
	        }
	    }
	}
	
	public List<Person> getAllPeople() throws SQLException {
	    return getAllPeople(50);
	}

	/**
	 * Returns the person with the specified id.
	 * 
	 * @param id the id of the person
	 * @return the person with the specified id
	 * @throws SQLException if a database error occurs
	 */
	public Person getPersonById(int id) throws SQLException {

		String statement = "select * from people where id = ?";
		PreparedStatement ps = connection.prepareStatement(statement);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {

			return new Person(rs.getInt("id"), rs.getString("name"), rs.getInt("birth"));
		}
		
		// return null if the id does not return a person.

		return null;

	}
	
	public List<Movie> getMoviesStarringPerson(int personId) throws SQLException {
        String sql =
            "SELECT m.id, m.title, m.year " +
            "FROM movies m " +
            "JOIN stars s ON m.id = s.movie_id " +
            "WHERE s.person_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, personId);
            try (ResultSet rs = ps.executeQuery()) {
                List<Movie> films = new ArrayList<>();
                while (rs.next()) {
                    Movie m = new Movie(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("year")
                    );
                    films.add(m);
                }
                return films;
            }
        }
    }
}
