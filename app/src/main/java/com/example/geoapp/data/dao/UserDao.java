package com.example.geoapp.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.geoapp.models.User;

import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
public abstract class UserDao {
    @Insert( onConflict = IGNORE)
    public abstract long insertUser( User user);

    @Query("SELECT * FROM user LIMIT 1")
    public abstract User getUser();

    @Update(onConflict = IGNORE)
    public abstract void updateUser (User user);

    @Query("DELETE FROM user")
    public abstract void deleteUser ();

    @Query("SELECT COUNT(*) FROM user")
    public abstract long getSize();

    @Transaction
    public void updateOrInsertUser( User user ) {
        long id = insertUser(user);
        if (id == -1) {
            updateUser(user);
        }
    }
}
