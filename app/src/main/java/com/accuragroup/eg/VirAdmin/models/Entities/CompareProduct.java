package com.accuragroup.eg.VirAdmin.models.Entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ${sayed} on 11/14/2017.
 */

@Entity
public class CompareProduct implements Serializable {

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


    @SerializedName("desc")
    @Expose
    private String description;


    @SerializedName("owner")
    @Expose
    private String owner;


    @SerializedName("city")
    @Expose
    private String city;


    @SerializedName("gov")
    @Expose
    private String gov;


    @SerializedName("rate")
    @Expose
    private int rate;

    private static final long serialVersionUID = 1L;//or some long


    @SerializedName("reserved")
    @Expose
    private int reserved;


    @Generated(hash = 797485024)
    public CompareProduct(long id, String img, String title, int price,
            String description, String owner, String city, String gov, int rate,
            int reserved) {
        this.id = id;
        this.img = img;
        this.title = title;
        this.price = price;
        this.description = description;
        this.owner = owner;
        this.city = city;
        this.gov = gov;
        this.rate = rate;
        this.reserved = reserved;
    }


    @Generated(hash = 143448601)
    public CompareProduct() {
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


    public String getDescription() {
        return this.description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getOwner() {
        return this.owner;
    }


    public void setOwner(String owner) {
        this.owner = owner;
    }


    public String getCity() {
        return this.city;
    }


    public void setCity(String city) {
        this.city = city;
    }


    public String getGov() {
        return this.gov;
    }


    public void setGov(String gov) {
        this.gov = gov;
    }


    public int getRate() {
        return this.rate;
    }


    public void setRate(int rate) {
        this.rate = rate;
    }


    public int getReserved() {
        return this.reserved;
    }


    public void setReserved(int reserved) {
        this.reserved = reserved;
    }

    
}
