package com.flickfinder.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 * TODO: Implement this class
 * 
 */

class PersonTest {
	
	/**
	 * The person object to be tested.
	 */
	private Person person;

	/**
	 * Set up the person object before each test.
	 *
	 */
	@BeforeEach
	public void setUp() {
		person = new Person(1, "Tim Robbins", 1958-10-16);
	}

	/**
	 * Test the person object is created with the correct values.
	 */
	@Test
	public void testPersonCreated() {
		assertEquals(1, person.getId());
		assertEquals("Tim Robbins", person.getName());
		assertEquals(1958-10-16, person.getBirth());
	}

	/**
	 * Test the person object is created with the correct values.
	 */
	@Test
	public void testPersonSetters() {
		person.setId(2);
		person.setName("Morgan Freeman");
		person.setBirth(1937-06-01);
		assertEquals(2, person.getId());
		assertEquals("Morgan Freeman", person.getName());
		assertEquals(1937-06-01, person.getBirth());
	}


}