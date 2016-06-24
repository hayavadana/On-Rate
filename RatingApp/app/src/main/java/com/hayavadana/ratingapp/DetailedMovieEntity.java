package com.hayavadana.ratingapp;

import java.util.ArrayList;

public class DetailedMovieEntity extends MovieEntity{

    private String movieDescription;
    private ArrayList<ReviewComment> movieReviewComments;


    public ArrayList<ReviewComment> getMovieReviewComments() {
        return movieReviewComments;
    }

    public void setMovieReviewComments(ArrayList<ReviewComment> movieReviewComments) {
        this.movieReviewComments = movieReviewComments;
    }



    public String getMovieDescription() {
        return movieDescription;
    }



    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }
}
