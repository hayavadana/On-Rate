package com.hayavadana.ratingapp;


public class BusinessEntity {


    private int businessId;
    private String businessName;
    private String businessAddress;
    private String averageRating;
    private String longitude;
    private String latitude;
    public String businessLogoUrl;
    private String businessLogoBase64;


    public String getBusinessLogoBase64() {
        return businessLogoBase64;
    }

    public void setBusinessLogoBase64(String businessLogoBase64) {
        this.businessLogoBase64 = businessLogoBase64;
    }




    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setBusinessLogoUrl(String businessLogoUrl) {
        this.businessLogoUrl = businessLogoUrl;
    }



    public int getBusinessId() {
        return businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getBusinessLogoUrl() {
        return businessLogoUrl;
    }



}
