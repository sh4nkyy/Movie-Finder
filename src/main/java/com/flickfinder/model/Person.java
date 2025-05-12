package com.flickfinder.model;

/**
 * A person in the movie database.
 * 
 * @TODO: Implement this class
 */
public class Person {

	// - Add your code here: use the MovieDAO.java as an example
	// - Check the ERD and database schema in the docs folder
	// (./docs/database_schema.md) to ensure each column in the People table
	// has an attribute in the model. (DELETE THIS COMMENT WHEN DONE)
	
	private int id;
	private String name;
	private int birth;
	
	/**
	 * Constructs a Movie object with the specified id, name, and birth.
	 *
	 * @param id    the unique identifier of the person
	 * @param name the name of the person
	 * @param birth  the birth birth of the person
	 */
	public Person(int id, String name, int birth) {
		this.id = id;
		this.name = name;
		this.birth= birth;
	}
	/**
	 * Returns the unique identifier of the movie.
	 *
	 * @return the id of the movie
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the unique identifier of the movie.
	 *
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the name of the movie.
	 *
	 * @return the name of the movie
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the movie.
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the release birth of the movie.
	 *
	 * @return the release birth of the movie
	 */
	public int getBirth() {
		return birth;
	}

	/**
	 * Sets the release birth of the movie.
	 *
	 * @param birth the release birth to set
	 */
	public void setBirth(int birth) {
		this.birth = birth;
	}

	/**
	 * Returns a string representation of the Movie object.
	 * This is primarily used for debugging purposes.
	 *
	 * @return a string representation of the Movie object
	 */
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", birth=" + birth + "]";
	}
	
}
