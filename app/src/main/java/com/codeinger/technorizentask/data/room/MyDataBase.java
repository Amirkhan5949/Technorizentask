package com.codeinger.technorizentask.data.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.codeinger.technorizentask.model.ProductModel;
import com.codeinger.technorizentask.model.UserModel;

@Database(entities = {UserModel.class, ProductModel.class}, version = 1)
public abstract class MyDataBase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract ProductDao productDao();
}
