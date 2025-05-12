package com.flickfinder.model;

/**
 * Extends Movie
 */
public class MovieRating extends Movie {
    private double rating;
    private int votes;

    /**
     * Full constructor
     *
     * @param id     the movie ID
     * @param title  the movie title
     * @param year   the release year
     * @param rating the average rating
     * @param votes  the number of votes
     */
    public MovieRating(int id, String title, int year, double rating, int votes) {
        super(id, title, year);
        this.rating = rating;
        this.votes = votes;
    }

    /** @return the average rating */
    public double getRating() {
        return rating;
    }

    /** @param rating the average rating to set */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /** @return the number of votes */
    public int getVotes() {
        return votes;
    }

    /** @param votes the number of votes to set */
    public void setVotes(int votes) {
        this.votes = votes;
    }
}
