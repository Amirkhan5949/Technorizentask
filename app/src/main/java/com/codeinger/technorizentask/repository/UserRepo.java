package com.codeinger.technorizentask.repository;


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


    public void addUser(UserModel... userModels) {
        userDao.addtUser(userModels);
    }

    public void updateUser(UserModel... userModels) {
        userDao.updateUser(userModels);
    }

    public void deleteUser(UserModel... userModels) {
        userDao.deleteUser(userModels);
    }


   public List<UserModel> getUserById(int[] userIds) {
        return userDao.getUserById(userIds);
    }


}
