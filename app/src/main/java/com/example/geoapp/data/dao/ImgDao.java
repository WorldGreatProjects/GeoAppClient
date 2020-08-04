package com.example.geoapp.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.geoapp.models.ImgModel;

import java.util.List;

import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
public abstract class ImgDao {

    @Insert( onConflict = IGNORE)
    public abstract long insertImage(ImgModel imgModel);

    @Query("SELECT * FROM app_images")
    public abstract List<ImgModel> getAllImages();

    @Query("SELECT * FROM app_images WHERE name LIKE :name LIMIT 1")
    public abstract ImgModel getImageByName( String name);

    @Update(onConflict = IGNORE)
    public abstract void updateImage (ImgModel image);

    @Query("SELECT COUNT(*) FROM app_images")
    public abstract long getSize();

    @Transaction
    public void updateOrInsertImage( ImgModel imgModel ) {
        long id = insertImage(imgModel);
        if (id == -1) {
            updateImage(imgModel);
        }
    }
}
