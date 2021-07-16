 package com.codeinger.technorizentask.ui.user;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.codeinger.technorizentask.model.ProductModel;
import com.codeinger.technorizentask.model.UserModel;
import com.codeinger.technorizentask.repository.UserRepo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class UserViewModel extends ViewModel {

    private UserRepo userRepo;
    private MutableLiveData<ArrayList<UserModel>> mutableLiveData = new MutableLiveData<>();
    private LiveData<List<UserModel>> liveData = null;

    @Inject
    public UserViewModel(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public long addUser(UserModel userModel){
        return userRepo.addUser(userModel);
    }
    public void deleteUser(UserModel userModel){
        userRepo.deleteUser(userModel);
    }

   public void updateUser(UserModel userModel){
        userRepo.updateUser(userModel);
   }

    public LiveData<List<UserModel>> getallUsers() {
        return userRepo.getAllUSers();
    }

   public List<UserModel> getEmailAndPass(String email,String pass){
        return userRepo.getEmailAndPass(email,pass);
   }

    public List<UserModel> getEmail(String email) {
        return  userRepo.getEmail(email);
    }



    public List<UserModel> getUserById(long userIds) {
        return  userRepo.getUserById(userIds);
    }

}
