package com.example.geoapp.models;


import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Mark {

    private final String ownerId;
    private final long creationDate;
    private double latitude;
    private double longitude;
    private String description = "";

    public Mark(String owner_id, double latitude, double longitude,String description) {
        this.ownerId = owner_id;
        this.creationDate = ( System.currentTimeMillis() / 1000L );
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
    }

    private Mark(String ownerId, long creationDate){
        this.ownerId = ownerId;
        this.creationDate = creationDate;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public JSONObject toJson()  {
        JSONObject json = new JSONObject();
        try{
            json.put("owner_id", this.ownerId);
            json.put("date", this.creationDate);
            json.put("lat", this.latitude);
            json.put("lon", this.longitude);
            json.put("desc", this.description);
        }catch (JSONException e){
            return new JSONObject();
        }
        return json;
    }

    @NonNull
    @Override
    public String toString() {
        return this.toJson().toString();
    }

    public static Mark markFromJSONString ( String json_str) throws JSONException {
        JSONObject json = new JSONObject(json_str);
        return markFromJSON( json );
    }

    public static Mark markFromJSON( JSONObject json) throws JSONException {
        Mark mark = new Mark( json.getString("owner_id"),
                json.getLong  ("date"));
        mark.setLatitude( json.getDouble("lat"));
        mark.setLongitude( json.getDouble("lon"));
        mark.setDescription( json.getString("description"));
        return mark;
    }

    public static List<Mark> getMarksFromJSONArray (JSONArray marksArray) throws JSONException {
        List<Mark> marks = new ArrayList<>();
        for (int i = 0 ; i < marksArray.length(); ++i){
            marks.add(markFromJSON((JSONObject) marksArray.get(i)));
        }
        return marks;
    }


}
