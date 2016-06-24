package com.hayavadana.ratingapp;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class QueryCache {

    private static JSONArray stateInfoCache;
    private static JSONArray cityInfoCache;
    private static ArrayList<StateEntity> stateEntityArr;
    private static ArrayList<CityEntity> cityEntityArr;
    private static ArrayList<AreaEntity> areaEntityArr;
    private static ArrayList<BusinessCategoryEntity> businessCategoryEntityArr;
    private static ArrayList<BusinessEntity> businessEntityArrayList;
    private static ArrayList<MovieEntity> movieEntityArrayList;
    private static ArrayList<MovieCategoryEntity> movieCategoryEntityArrayList;


    public static ArrayList<StateEntity> getStateEntityArrayList(){
        return stateEntityArr;
    }
    public static ArrayList<CityEntity> getCityEntityArrayList(){
        return cityEntityArr;
    }

    public static ArrayList<AreaEntity> getAreaEntityArrayList(){
        return areaEntityArr;
    }
    public static ArrayList<BusinessCategoryEntity> getBusinessCategoryEntityArrayList(){
        return businessCategoryEntityArr;
    }

    public static ArrayList<BusinessEntity> getBusinessEntityArrayList(){
        return businessEntityArrayList;
    }

    public static ArrayList<MovieEntity> getMovieEntityArrayList(){
        return movieEntityArrayList;
    }
    public static ArrayList<MovieCategoryEntity> getMovieCategoryEntityArrayList(){
        return movieCategoryEntityArrayList;
    }


    public static void setStateInfo(JSONArray stateInfo){
        stateEntityArr = new ArrayList<StateEntity>();
        JSONObject jsonChildNode = null;

        if (stateInfo != null) {
            for (int i=0;i<stateInfo.length();i++){
                StateEntity aStateEntityObj = new StateEntity();

                try {
                    jsonChildNode = stateInfo.getJSONObject(i);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

                String stateCode = jsonChildNode.optString("stateCode").toString();
                aStateEntityObj.stateCode =  stateCode;

                String stateDesc = jsonChildNode.optString("stateDesc").toString();
                aStateEntityObj.stateDesc =  stateDesc;


                stateEntityArr.add(aStateEntityObj);
            }
        }
    }
    public static void setCityInfo(JSONArray cityInfo){
        cityEntityArr = new ArrayList<CityEntity>();
        JSONObject jsonChildNode = null;

        if (cityInfo != null) {
            for (int i=0;i<cityInfo.length();i++){
                CityEntity aCityEntityObj = new CityEntity();

                try {
                    jsonChildNode = cityInfo.getJSONObject(i);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

                int cityCode = (int) Integer.parseInt(jsonChildNode.optString("cityCode"));
                aCityEntityObj.cityCode =  cityCode;

                String cityDesc = jsonChildNode.optString("cityDesc").toString();
                aCityEntityObj.cityDesc =  cityDesc;


                cityEntityArr.add(aCityEntityObj);
            }
        }
    }
    public static void setAreasInfo(JSONArray areaInfo){
        areaEntityArr = new ArrayList<AreaEntity>();
        JSONObject jsonChildNode = null;

        if (areaInfo != null) {
            for (int i=0;i<areaInfo.length();i++){
                AreaEntity anAreaEntityObj = new AreaEntity();

                try {
                    jsonChildNode = areaInfo.getJSONObject(i);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

                int areaCode = (int) Integer.parseInt(jsonChildNode.optString("areaCode"));
                anAreaEntityObj.areaCode =  areaCode;

                String areaDesc = jsonChildNode.optString("areaDesc").toString();
                anAreaEntityObj.areaDesc =  areaDesc;


                areaEntityArr.add(anAreaEntityObj);
            }
        }
    }

    public static void setBusinessCategoryInfo(JSONArray businessCategoryInfo) {
        businessCategoryEntityArr = new ArrayList<BusinessCategoryEntity>();
        JSONObject jsonChildNode = null;

        if (businessCategoryInfo != null) {
            for (int i = 0; i < businessCategoryInfo.length(); i++) {
                BusinessCategoryEntity aBusinessCategoryEntity = new BusinessCategoryEntity();

                try {
                    jsonChildNode = businessCategoryInfo.getJSONObject(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String categoryCode = jsonChildNode.optString("categoryCode").toString();
                aBusinessCategoryEntity.categoryCode = categoryCode;

                String categoryDesc = jsonChildNode.optString("categoryDesc").toString();
                aBusinessCategoryEntity.categoryDesc = categoryDesc;


                businessCategoryEntityArr.add(aBusinessCategoryEntity);
            }
        }
    }

    public static void setBusinessEntityList(JSONArray businessEntityListJSONArr){
        businessEntityArrayList = new ArrayList<BusinessEntity>();
        JSONObject jsonChildNode = null;

        if (businessEntityListJSONArr != null) {
            for (int i=0;i<businessEntityListJSONArr.length();i++){
                BusinessEntity aBusinessEntity = new BusinessEntity();

                try {
                    jsonChildNode = businessEntityListJSONArr.getJSONObject(i);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

                int businessId = (int) Integer.parseInt(jsonChildNode.optString("businessId"));
                aBusinessEntity.setBusinessId(businessId);

                String businessName = jsonChildNode.optString("businessName").toString();
                aBusinessEntity.setBusinessName(businessName);

                String businessAddress = jsonChildNode.optString("businessAddress").toString();
                aBusinessEntity.setBusinessAddress(businessAddress);

                String businessLogoUrl = jsonChildNode.optString("businessLogoUrl").toString();
                aBusinessEntity.setBusinessLogoUrl(businessLogoUrl);

                String averageRating = jsonChildNode.optString("averageRating").toString();
                aBusinessEntity.setAverageRating(averageRating);

                String longitude = jsonChildNode.optString("longitude").toString();
                aBusinessEntity.setLongitude(longitude);

                String latitude = jsonChildNode.optString("latitude").toString();
                aBusinessEntity.setLatitude(latitude);

                String logoBase64 = jsonChildNode.optString("businessLogo").toString();
                aBusinessEntity.setBusinessLogoBase64(logoBase64);

                businessEntityArrayList.add(aBusinessEntity);
            }
        }

    }
    public static void setMovieCategoryInfo(JSONArray movieCategoryInfo) {
        movieCategoryEntityArrayList = new ArrayList<MovieCategoryEntity>();
        JSONObject jsonChildNode = null;

        if (movieCategoryInfo != null) {
            for (int i = 0; i < movieCategoryInfo.length(); i++) {
                MovieCategoryEntity aMovieCategoryEntity = new MovieCategoryEntity();

                try {
                    jsonChildNode = movieCategoryInfo.getJSONObject(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String lCategoryCode = jsonChildNode.optString("categoryCode").toString();
                aMovieCategoryEntity.langcategorycode = lCategoryCode;

                String lCategory = jsonChildNode.optString("langcategory").toString();
                aMovieCategoryEntity.langcategory = lCategory;


                movieCategoryEntityArrayList.add(aMovieCategoryEntity);
            }
        }
    }
    public static void setMovieEntityList(JSONArray movieEntityListJSONArr){
        movieEntityArrayList = new ArrayList<MovieEntity>();
        JSONObject jsonChildNode = null;

        if (movieEntityListJSONArr != null) {
            for (int i=0;i<movieEntityListJSONArr.length();i++){
                MovieEntity aMovieEntity = new MovieEntity();

                try {
                    jsonChildNode = movieEntityListJSONArr.getJSONObject(i);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

                int movieId = (int) Integer.parseInt(jsonChildNode.optString("movie_id"));
                aMovieEntity.setMovieId(movieId);

                String movieName = jsonChildNode.optString("movieName").toString();
                aMovieEntity.setMovieName(movieName);

                String movieActorName = jsonChildNode.optString("actor").toString();
                aMovieEntity.setActorName(movieActorName);

               /* String movieActressName = jsonChildNode.optString("movieActressName").toString();
                aMovieEntity.setActressName(movieActressName);

                String movieLogoUrl = jsonChildNode.optString("movieLogoUrl").toString();
                aMovieEntity.setMovieLogoUrl(movieLogoUrl);
*/
                String averageRating = Double.toString(jsonChildNode.optDouble("avgRating"));
                aMovieEntity.setAverageRating(averageRating);

                String logoBase64 = jsonChildNode.optString("poster").toString();
                aMovieEntity.setMovieLogoBase64(logoBase64);

                movieEntityArrayList.add(aMovieEntity);
            }
        }

    }

}
