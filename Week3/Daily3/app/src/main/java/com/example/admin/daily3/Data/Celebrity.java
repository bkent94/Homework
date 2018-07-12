package com.example.admin.daily3.Data;

import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

public class Celebrity {

    @PrimaryKey
    @NonNull
    private String name;
    private String details;
    private String image;

    @Override
    public String toString() {
        return "Celebrity{" +
                "name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Celebrity(@NonNull String name, String details, String image) {

        this.name = name;
        this.details = details;
        this.image = image;
    }
}
