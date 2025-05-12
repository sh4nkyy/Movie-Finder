package com.flickfinder.controller;

import java.sql.SQLException;
import java.util.List;

import com.flickfinder.dao.MovieDAO;
import com.flickfinder.model.Movie;
import com.flickfinder.model.Person;

import io.javalin.http.Context;

/**
 * The controller for the movie endpoints.
 * 
 * The controller acts as an intermediary between the HTTP routes and the DAO.
 * 
 * As you can see each method in the controller class is responsible for
 * handling a specific HTTP request.
 * 
 * Methods a Javalin Context object as a parameter and uses it to send a
 * response back to the client.
 * We also handle business logic in the controller, such as validating input and
 * handling errors.
 *
 * Notice that the methods don't return anything. Instead, they use the Javalin
 * Context object to send a response back to the client.
 */

public class MovieController {

	/**
	 * The movie data access object.
	 */

	private final MovieDAO movieDAO;

	/**
	 * Constructs a MovieController object and initialises the movieDAO.
	 */
	public MovieController(MovieDAO movieDAO) {
		this.movieDAO = movieDAO;
	}

	/**
	 * Returns a list of all movies in the database.
	 * 
	 * @param ctx the Javalin context
	 */
	public void getAllMovies(Context ctx) {
	    String q = ctx.queryParam("limit");

	    try {
	        if (q == null) {
	            ctx.json(movieDAO.getAllMovies());
	        } else {
	            int limit;
	            try {
	                limit = Integer.parseInt(q);
	            } catch (NumberFormatException e) {
	                ctx.status(400).result("limit must be an integer");
	                return;
	            }
	            ctx.json(movieDAO.getAllMovies(limit));
	        }
	    } catch (SQLException e) {
	        ctx.status(500);
	    }
	}


	/**
	 * Returns the movie with the specified id.
	 * 
	 * @param ctx the Javalin context
	 */
	public void getMovieById(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		try {
			Movie movie = movieDAO.getMovieById(id);
			if (movie == null) {
				ctx.status(404);
				ctx.result("Movie not found");
				return;
			}
			ctx.json(movieDAO.getMovieById(id));
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();
		}
	}
	
	public void getPeopleByMovieId(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        try {
            List<Person> stars = movieDAO.getPeopleByMovieId(id);
            ctx.json(stars);
        } catch (SQLException e) {
            ctx.status(500);
            ctx.result("Database error");
            e.printStackTrace();
        }
    }

	public void getRatingsByYear(Context ctx) {
	    int year = Integer.parseInt(ctx.pathParam("year"));
	    int limit = 50;
	    int minVotes = 1000;
	    String l = ctx.queryParam("limit"), v = ctx.queryParam("votes");
	    try {
	        if (l != null) limit = Integer.parseInt(l);
	        if (v != null) minVotes = Integer.parseInt(v);
	    } catch (NumberFormatException ex) {
	        ctx.status(400);
	        ctx.result("limit and votes must be integers");
	        return;
	    }
	    try {
	        ctx.json(movieDAO.getRatingsByYear(year, limit, minVotes));
	    } catch (SQLException e) {
	        ctx.status(500);
	        ctx.result("Database error");
	    }
	}

}