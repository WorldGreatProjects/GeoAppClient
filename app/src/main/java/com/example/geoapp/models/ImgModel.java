package com.example.geoapp.models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "app_images")
public class ImgModel {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name = "";

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public ImgModel (String name , byte[] imgBytes){
        this.image = imgBytes;
        this.name = name;
    }

    public ImgModel(){}

}
