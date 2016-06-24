package com.hayavadana.ratingapp;


public class ReviewComment {

    private String commentString;
    private String rating;

    public ReviewComment(String commentString, String rating) {
        this.commentString = commentString;
        this.rating = rating;
    }


    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }




    public String getCommentString() {
        return commentString;
    }

    public void setCommentString(String commentString) {
        this.commentString = commentString;
    }


}
