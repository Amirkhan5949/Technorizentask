package com.codeinger.technorizentask.repository;

import androidx.lifecycle.LiveData;

import com.codeinger.technorizentask.data.room.ProductDao;
import com.codeinger.technorizentask.model.ProductModel;

import java.util.List;

import javax.inject.Inject;

public class ProductRepo {

    private final ProductDao productDao;

    @Inject
    public ProductRepo(ProductDao productDao) {
        this.productDao = productDao;
    }


    public void addProduct(ProductModel... product) {
        productDao.addtProduct(product);
    }

    public void updateProduct(ProductModel... product) {
        productDao.updateProduct(product);
    }

    public void deleteProduct(ProductModel... product) {
        productDao.deleteProduct(product);
    }


   public List<ProductModel> getProductById(int[] userIds) {
        return productDao.getProductById(userIds);
    }

    public LiveData<List<ProductModel>> getallProducts(){
        return productDao.getAllProducts();
    }



}
