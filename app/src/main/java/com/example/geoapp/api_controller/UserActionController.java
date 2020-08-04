package com.example.geoapp.api_controller;

import android.net.Uri;

import com.example.geoapp.Urls;
import com.example.geoapp.exceptions.RegistrationException;
import com.example.geoapp.exceptions.UserActionException;
import com.example.geoapp.models.Mark;
import com.example.geoapp.models.User;
import com.example.geoapp.net.Requests;
import com.example.geoapp.utils.JSONCreater;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UserActionController {
    private User user;

    public UserActionController(User user){
        this.user = user;
    }

    public User getUser(){
        return this.user;
    }

    public void setUser( User user){
        this.user = user;

    }

    public Completable changeUserInfo( User user ) {
        return Completable.fromAction(()->{
            JSONObject response = Requests.put(Urls.API_USER + user.getId() + "/", user.toJson());
            if ( !response.getString("status").equals("success")){
                throw new RegistrationException(" Error while change  " + response.getString("status"));
            }
        }).subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread());
    }


    public Single<List<Mark>> getUserMarks(User user ) {
        return Single.fromCallable(()->{
            JSONObject response = Requests.get(Urls.API_USER_MARKS + user.getId() + "/");
            return Mark.getMarksFromJSONArray(response.getJSONArray("marks"));
        }).subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable createMark(User user, Mark mark ) {
        return Completable.fromAction(()->{
            JSONObject response = Requests.post(Urls.API_USER_MARKS + user.getId() + "/", mark.toJson() );
            if ( !response.getString("status").equals("success")){
                throw new RegistrationException(" Error while mark creation  " + response.getString("status")+ response.getString("message"));
            }
        }).subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable updateMark(User user, Mark mark ) {
        return Completable.fromAction(()->{
            Requests.put(Urls.API_USER_MARKS + user.getId() + "/", mark.toJson() );
        }).subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable deleteMark(User user, Mark mark ) {
        return Completable.fromAction(()->{
            Requests.delete(Urls.deleteMarkUrl(user.getId(), mark ));
        }).subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable follow(User user, String username ) {
        return Completable.fromAction(()->{
            JSONObject requestBody = JSONCreater.followPackage( username );
            Requests.post(Urls.API_USER_ACTION + user.getId(), requestBody);
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable unfollow(User user, String username ) {
        return Completable.fromAction(()->{
            JSONObject requestBody = JSONCreater.unFollowPackage( username );
            Requests.post(Urls.API_USER_ACTION + user.getId(), requestBody);
        }).subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread());
    }

}
