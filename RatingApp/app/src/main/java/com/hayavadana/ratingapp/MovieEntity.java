package com.hayavadana.ratingapp;


public class MovieEntity {

    private int movieId;
    private String movieName;
    private String actorName;
    private String actressName;
    private String averageRating;
    public String movieLogoUrl;
    private String movieLogoBase64;



    public void setMovieLogoBase64(String movieLogoBase64) {
        this.movieLogoBase64 = movieLogoBase64;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public void setActressName(String actressName) {
        this.actressName = actressName;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }


    public void setMovieLogoUrl(String movieLogoUrl) {
        this.movieLogoUrl = movieLogoUrl;
    }


    public String getMovieLogoBase64() {
        return movieLogoBase64;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getActorName() {
        return actorName;
    }

    public String getActressName() {
        return actressName;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public String getMovieLogoUrl() {
        return movieLogoUrl;
    }


}
