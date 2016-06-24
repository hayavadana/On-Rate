package com.hayavadana.ratingapp;

import java.util.ArrayList;



public class DetailedBusinessEntity extends BusinessEntity{

    private String businessDescription;
    private ArrayList<ReviewComment> businessReviewComments;


    public ArrayList<ReviewComment> getBusinessReviewComments() {
        return businessReviewComments;
    }

    public void setBusinessReviewComments(ArrayList<ReviewComment> businessReviewComments) {
        this.businessReviewComments = businessReviewComments;
    }



    public String getBusinessDescription() {
        return businessDescription;
    }



    public void setBusinessDescription(String businessDescription) {
        this.businessDescription = businessDescription;
    }

}
