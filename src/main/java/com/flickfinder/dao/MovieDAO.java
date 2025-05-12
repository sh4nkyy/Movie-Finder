package com.flickfinder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flickfinder.model.Movie;
import com.flickfinder.model.MovieRating;
import com.flickfinder.model.Person;
import com.flickfinder.util.Database;

/**
 * The Data Access Object for the Movie table.
 * 
 * This class is responsible for getting data from the Movies table in the
 * database.
 * 
 */
// S2 is to be done in this class
public class MovieDAO {

	/**
	 * The connection to the database.
	 */
	private final Connection connection;

	/**
	 * Constructs a SQLiteMovieDAO object and gets the database connection.
	 * 
	 */
	public MovieDAO() {
		Database database = Database.getInstance();
		connection = database.getConnection();
	}

	/**
	 * Returns a list of all movies in the database.
	 * 
	 * @return a list of all movies in the database
	 * @throws SQLException if a database error occurs
	 */


	public List<Movie> getAllMovies(int limit) throws SQLException {
	    String sql = "SELECT * FROM movies LIMIT ?";
	    try (PreparedStatement ps = connection.prepareStatement(sql)) {
	        ps.setInt(1, limit);
	        try (ResultSet rs = ps.executeQuery()) {
	            List<Movie> movies = new ArrayList<>();
	            while (rs.next()) {
	                movies.add(new Movie(
	                    rs.getInt("id"),
	                    rs.getString("title"),
	                    rs.getInt("year")
	                ));
	            }
	            return movies;
	        }
	    }
	}
	
	public List<Movie> getAllMovies() throws SQLException {
	    return getAllMovies(50);
	}

	/**
	 * Returns the movie with the specified id.
	 * 
	 * @param id the id of the movie
	 * @return the movie with the specified id
	 * @throws SQLException if a database error occurs
	 */
	public Movie getMovieById(int id) throws SQLException {

		String statement = "select * from movies where id = ?";
		PreparedStatement ps = connection.prepareStatement(statement);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {

			return new Movie(rs.getInt("id"), rs.getString("title"), rs.getInt("year"));
		}
		
		// return null if the id does not return a movie.

		return null;

	}

	public List<Person> getPeopleByMovieId(int movieID) throws SQLException {
		String sql =
		        "SELECT p.id, p.name, p.birth " +
		        "FROM people p " +
		        "  JOIN stars s ON p.id = s.person_id " +
		        "WHERE s.movie_id = ?";
		    try (PreparedStatement ps = connection.prepareStatement(sql)) {
		        ps.setInt(1, movieID);
		        try (ResultSet rs = ps.executeQuery()) {
		            List<Person> stars = new ArrayList<>();
		            while (rs.next()) {
		                stars.add(new Person(
		                    rs.getInt("id"),
		                    rs.getString("name"),
		                    rs.getInt("birth")
		                ));
		            }
		            return stars;
		        }
		    }
	}
	
	/**
	 * Returns top-rated movies for a given year, filtered by minVotes and limited to count.
	 */
	public List<MovieRating> getRatingsByYear(int year, int limit, int minVotes) throws SQLException {
	    String sql =
	        "SELECT m.id, m.title, m.year, r.rating, r.votes " +
	        "FROM movies m " +
	        "  JOIN ratings r ON m.id = r.movie_id " +
	        "WHERE m.year = ? AND r.votes > ? " +
	        "ORDER BY r.rating DESC " +
	        "LIMIT ?";
	    try (PreparedStatement ps = connection.prepareStatement(sql)) {
	        ps.setInt(1, year);
	        ps.setInt(2, minVotes);
	        ps.setInt(3, limit);
	        try (ResultSet rs = ps.executeQuery()) {
	            List<MovieRating> list = new ArrayList<>();
	            while (rs.next()) {
	                list.add(new MovieRating(
	                    rs.getInt("id"),
	                    rs.getString("title"),
	                    rs.getInt("year"),
	                    rs.getDouble("rating"),
	                    rs.getInt("votes")
	                ));
	            }
	            return list;
	        }
	    }
	}

}
