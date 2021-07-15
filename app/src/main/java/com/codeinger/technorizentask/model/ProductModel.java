package com.codeinger.technorizentask.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = UserModel.class,
        parentColumns = "U_id",
        childColumns = "U_id",
        onDelete = ForeignKey.CASCADE)

})
public class ProductModel {
    @PrimaryKey(autoGenerate = true)
    private int P_id ;
    private String name,price,description;
    private int U_id ;

    public ProductModel(int p_id, String name, String price, String description, int u_id) {
        P_id = p_id;
        this.name = name;
        this.price = price;
        this.description = description;
        U_id = u_id;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "P_id=" + P_id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                ", U_id=" + U_id +
                '}';
    }

    public int getP_id() {
        return P_id;
    }

    public void setP_id(int p_id) {
        P_id = p_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getU_id() {
        return U_id;
    }

    public void setU_id(int u_id) {
        U_id = u_id;
    }
}


