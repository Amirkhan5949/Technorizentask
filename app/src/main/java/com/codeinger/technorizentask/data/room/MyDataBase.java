package com.codeinger.technorizentask.data.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

 import com.codeinger.technorizentask.data.room.converter.ImageConverter;
import com.codeinger.technorizentask.model.ProductModel;
import com.codeinger.technorizentask.model.UserModel;


@Database(entities = {UserModel.class, ProductModel.class}, version = 1,exportSchema = false)
@TypeConverters(ImageConverter.class)
public abstract class MyDataBase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract ProductDao productDao();


}
