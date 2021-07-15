package com.codeinger.technorizentask.di;

import android.app.Application;

import androidx.room.Room;

import com.codeinger.technorizentask.data.room.MyDataBase;
import com.codeinger.technorizentask.data.room.ProductDao;
import com.codeinger.technorizentask.data.room.UserDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
 import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public static UserDao userDao  (MyDataBase myDataBase){
        return myDataBase.userDao();
    }

    @Provides
    @Singleton
    public static ProductDao productDao  (MyDataBase myDataBase){
        return myDataBase.productDao();
    }

    @Provides
    @Singleton
    public static MyDataBase myDataBase  (Application application){
        return Room.databaseBuilder(application,MyDataBase.class,"Favorite Database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }


}
