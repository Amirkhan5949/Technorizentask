package com.codeinger.technorizentask.repository;


import androidx.lifecycle.LiveData;

import com.codeinger.technorizentask.data.room.UserDao;
import com.codeinger.technorizentask.model.UserModel;

import java.util.List;

import javax.inject.Inject;

public class UserRepo {

    private final UserDao userDao;

    @Inject
    public UserRepo(UserDao userDao) {
        this.userDao = userDao;
    }


    public long addUser(UserModel userModels) {
        return userDao.addtUser(userModels);
    }

    public void updateUser(UserModel... userModels) {
        userDao.updateUser(userModels);
    }

    public void deleteUser(UserModel... userModels) {
        userDao.deleteUser(userModels);
    }

    public LiveData<List<UserModel>> getAllUSers(){
        return userDao.getAllUSers();
    }

   public List<UserModel> getUserById(long userIds) {
        return userDao.getUserById(userIds);
    }
   public List<UserModel> getEmailAndPass(String email,String pass) {
        return userDao.getEmailAndPass(email,pass);
    }


    public List<UserModel> getEmail(String email) {
        return userDao.getEmail(email);
    }


}
