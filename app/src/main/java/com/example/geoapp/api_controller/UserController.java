package com.example.geoapp.api_controller;

import com.example.geoapp.Urls;
import com.example.geoapp.exceptions.RegistrationException;
import com.example.geoapp.exceptions.ResetPassException;
import com.example.geoapp.models.User;
import com.example.geoapp.net.Requests;
import com.example.geoapp.utils.FormValidator;
import com.example.geoapp.utils.JSONCreater;

import org.json.JSONObject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UserController {

    private final FormValidator formValidator = new FormValidator();

    public Completable resetPassword(String emailOrUserName){
        return Completable.fromAction(()->{
            JSONObject response = Requests.get(Urls.API_USER_RESET_PASS + emailOrUserName + "/");
            if ( !response.getString("status").equals("success")){
                throw new ResetPassException("No such username or email " +
                        response.getString("message"));
            }
        })
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable registration(User user ) {
        return Completable.fromAction(()->{
            JSONObject response = Requests.post( Urls.API_USER + user.getId() + "/", user.toJson());
            if ( !response.getString("status").equals("success")){
                throw new RegistrationException(" Error while registration " +
                        response.getString("message"));
            }})
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<User> login(String username , String password ) {
        return Single.fromCallable(()->{
            JSONObject loginPackage = JSONCreater.login(username ,password);
            JSONObject response = Requests.post( Urls.API_USER_LOGIN, loginPackage);
            return User.userFromJSONString(response.toString());
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable deleteAccount( User user ) {
        return Completable.fromAction(()->{
            Requests.delete( Urls.API_USER + user.getId() + "/"); })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
