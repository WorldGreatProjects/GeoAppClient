package com.example.geoapp.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.geoapp.utils.Hash;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Entity (tableName = "user")
public class User {

    @PrimaryKey
    @NonNull
    private String id ;
    private String userName = "";
    private String email = "";
    private String password = "";
    private String name = "";
    private String description = "";


    public User(){
        id = UUID.randomUUID().toString() + "-" + ( System.currentTimeMillis() / 1000L );
    }

    public String getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String user_name) {
        this.userName = user_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        try{
            this.password = Hash.getSha256( password );
        }catch ( NoSuchAlgorithmException e){
            this.password = password;
        }
    }

    public boolean isEmpty(){
        return (userName.equals("") || password.equals("") || email.equals(""));
    }

    public JSONObject toJson()  {
        JSONObject json = new JSONObject();
        try{
            json.put("id", this.id);
            json.put("password", this.password);
            json.put("username", this.userName);
            json.put("email", this.email);
            json.put("name", this.name);
            json.put("desc", this.description);
        }catch (JSONException e){
            return new JSONObject();
        }
        return json;
    }

    public static User userFromJSONString( String  json_str) throws JSONException {
        JSONObject json = new JSONObject(json_str);
        return userFromJSON( json );
    }

    public static User userFromJSON( JSONObject  json) throws JSONException {
        User user = new User();
        user.setId(json.getString("id"));
        user.setHashedPassword(json.getString("password"));
        user.setUserName(json.getString("username"));
        user.setEmail(json.getString("email"));
        user.setName(json.getString("name"));
        user.setDescription(json.getString("desc"));
        return user;
    }

    private void setHashedPassword( String hashed_pass){
        this.password = hashed_pass;
    }


    @NonNull
    @Override
    public String toString() {
        return this.toJson().toString();
    }
}
