package com.codeinger.technorizentask.data.room;

import androidx.annotation.Keep;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.codeinger.technorizentask.model.ProductModel;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void addtProduct(ProductModel... products);

    @Update
    public void  updateProduct(ProductModel... products);

    @Delete
    public void deleteProduct(ProductModel... products);

    @Query("SELECT * FROM ProductModel WHERE U_id IN (:userIds)")
    List<ProductModel> getProductById(int[] userIds);


    @Query("SELECT * FROM productmodel ORDER BY P_id ASC")
     LiveData<List<ProductModel>> getAllProducts();

}
