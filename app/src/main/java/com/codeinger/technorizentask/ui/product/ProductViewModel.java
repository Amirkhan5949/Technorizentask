package com.codeinger.technorizentask.ui.product;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.codeinger.technorizentask.model.ProductModel;
import com.codeinger.technorizentask.repository.ProductRepo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ProductViewModel extends ViewModel {
    private ProductRepo productRepo;
    private MutableLiveData<ArrayList<ProductModel>> mutableLiveData = new MutableLiveData<>();
    private LiveData<List<ProductModel>> liveData = null;

    @Inject
    public ProductViewModel(ProductRepo productRepo) {
        this.productRepo = productRepo;
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

    public LiveData<List<ProductModel>> getProductById(int[] userIds) {
        return (LiveData<List<ProductModel>>) productRepo.getProductById(userIds);
    }

    public LiveData<List<ProductModel>> getallProducts() {
        return productRepo.getallProducts();
    }

}
