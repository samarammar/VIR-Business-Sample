package com.accuragroup.eg.VirAdmin.models.Entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * Created by ${sayed} on 10/3/2017.
 */

@Entity
public class CartProducts implements Serializable {

    @Id
    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("img")
    @Expose
    private String img;

    @SerializedName("title")
    @Expose
    private String title;


    @SerializedName("price")
    @Expose
    private int price;


    @SerializedName("quantity")
    @Expose
    private int quantity;


    @SerializedName("total")
    @Expose
    private String total;

    @SerializedName("category")
    @Expose
    private String category;

    private static final long serialVersionUID = 1L;//or some long

    @Generated(hash = 789085541)
    public CartProducts(long id, String img, String title, int price, int quantity,
                        String total, String category) {
        this.id = id;
        this.img = img;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
        this.category = category;
    }

    @Generated(hash = 1521058978)
    public CartProducts() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTotal() {
        return this.total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


}
