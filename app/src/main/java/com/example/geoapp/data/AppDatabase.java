package com.example.geoapp.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.geoapp.data.dao.ImgDao;
import com.example.geoapp.data.dao.UserDao;
import com.example.geoapp.models.ImgModel;
import com.example.geoapp.models.User;


@Database(entities = { User.class, ImgModel.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    private final static String DB_NAME = "GeoApp_db";
    public abstract UserDao userDao();
    public abstract ImgDao imgDao();

    public static synchronized AppDatabase getInstance( Context context){
        if (INSTANCE == null ){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public synchronized void saveUser( User user){
        userDao().updateOrInsertUser( user );
    }

    public synchronized User loadUser(){
        return userDao().getUser();
    }


    public synchronized void saveImage( ImgModel imgModel){
        imgDao().updateOrInsertImage(imgModel);
    }

}
