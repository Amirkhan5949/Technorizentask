package com.codeinger.technorizentask.data.room;

import android.icu.util.ULocale;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.codeinger.technorizentask.model.ProductModel;
import com.codeinger.technorizentask.model.UserModel;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void addtUser(UserModel... users);

    @Update
    public void  updateUser(UserModel... users);

    @Delete
     public void deleteUser(UserModel... users);


    @Query("SELECT * FROM user_table WHERE U_id IN (:userIds)")
    List<UserModel> getUserById(int[] userIds);

//    @Query("SELECT * FROM user_table ORDER BY U_id ASC")
//    LiveData<List<UserModel>> getAllUsers();


}
