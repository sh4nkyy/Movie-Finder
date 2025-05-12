package com.flickfinder.controller;

import java.sql.SQLException;
import java.util.List;

import com.flickfinder.dao.PersonDAO;
import com.flickfinder.model.Movie;
import com.flickfinder.model.Person;

import io.javalin.http.Context;

public class PersonController {

	// to complete the must-have requirements you need to add the following methods:
	// getAllPeople
	// getPersonById
	// you will add further methods for the more advanced tasks; however, ensure your have completed 
	// the must have requirements before you start these. 
	
	/**
	 * The person data access object.
	 */

	private final PersonDAO personDAO;

	/**
	 * Constructs a PersonController object and initialises the personDAO.
	 */
	public PersonController(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	/**
	 * Returns a list of all people in the database.
	 * 
	 * @param ctx the Javalin context
	 */
	public void getAllPeople(Context ctx) {
	    String q = ctx.queryParam("limit");
	    
	    try {
	        if (q == null) {
	            ctx.json(personDAO.getAllPeople());
	        } else {
	            int limit;
	            try {
	                limit = Integer.parseInt(q);
	            } catch (NumberFormatException e) {
	                ctx.status(400);
	                ctx.result("limit must be an integer");
	                return;
	            }
	            ctx.json(personDAO.getAllPeople(limit));
	        }
	    } catch (SQLException e) {
	        ctx.status(500);
	    }
	}

	/**
	 * Returns the person with the specified id.
	 * 
	 * @param ctx the Javalin context
	 */
	public void getPersonById(Context ctx) {

		int id = Integer.parseInt(ctx.pathParam("id"));
		try {
			Person person = personDAO.getPersonById(id);
			if (person == null) {
				ctx.status(404);
				ctx.result("Person not found");
				return;
			}
			ctx.json(personDAO.getPersonById(id));
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();
		}
	}
	
	public void getMoviesStarringPerson(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        try {
            List<Movie> films = personDAO.getMoviesStarringPerson(id);
            ctx.json(films);
        } catch (SQLException e) {
        	ctx.status(500);
        	ctx.result("Database error");
            e.printStackTrace();
        }
    }


}