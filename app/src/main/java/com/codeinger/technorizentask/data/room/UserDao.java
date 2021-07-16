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
    public Long addtUser(UserModel users);

    @Update
    public void  updateUser(UserModel... users);

    @Delete
    public void deleteUser(UserModel... users);


    @Query("SELECT * FROM UserModel WHERE U_id = (:userIds)")
    List<UserModel> getUserById(long userIds);

    @Query("SELECT * FROM UserModel ORDER BY U_id ASC")
    LiveData<List<UserModel>> getAllUSers();

    @Query("SELECT * FROM UserModel WHERE email = (:email) AND password = (:pass)")
    List<UserModel> getEmailAndPass(String email,String pass);

    @Query("SELECT * FROM UserModel WHERE email = (:email)")
    List<UserModel> getEmail(String email);


}
