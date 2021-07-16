package com.codeinger.technorizentask.ui.product;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.codeinger.technorizentask.model.ProductModel;
import com.codeinger.technorizentask.model.UserModel;
import com.codeinger.technorizentask.repository.ProductRepo;
import com.codeinger.technorizentask.utils.SharedPrefsManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ProductViewModel extends ViewModel {
    private ProductRepo productRepo;
    public LiveData<List<ProductModel>> liveData = new MutableLiveData<>();

    @Inject
    public ProductViewModel(ProductRepo productRepo) {
        UserModel model= SharedPrefsManager.getInstance().getObject("Users_info",UserModel.class);
        this.productRepo = productRepo;
        getProductById(model.getU_id());
    }

    public void addProduct(ProductModel product){
        productRepo.addProduct(product);
    }

    public void deleteProduct(ProductModel product){
        productRepo.deleteProduct(product);
    }

    public void updateProduct(ProductModel product){
        productRepo.updateProduct(product);
    }

    public void getProductById(long userIds) {
        liveData = productRepo.getProductById(userIds);
    }



}
