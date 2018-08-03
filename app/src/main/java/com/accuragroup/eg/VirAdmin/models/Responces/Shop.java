
package com.accuragroup.eg.VirAdmin.models.Responces;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Shop implements Serializable
{

    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("shop_name")
    @Expose
    private String shopName;
    @SerializedName("shop_image")
    @Expose
    private String shopImage;
    @SerializedName("shop_rate")
    @Expose
    private int shopRate;
    @SerializedName("shop_categories")
    @Expose
    private List<String> shopCategories = null;
    @SerializedName("shop_city")
    @Expose
    private String shopCity;
    @SerializedName("shop_gov")
    @Expose
    private String shopGov;
    private final static long serialVersionUID = -1673694500149379604L;

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopImage() {
        return shopImage;
    }

    public void setShopImage(String shopImage) {
        this.shopImage = shopImage;
    }

    public int getShopRate() {
        return shopRate;
    }

    public void setShopRate(int shopRate) {
        this.shopRate = shopRate;
    }

    public List<String> getShopCategories() {
        return shopCategories;
    }

    public void setShopCategories(List<String> shopCategories) {
        this.shopCategories = shopCategories;
    }

    public String getShopCity() {
        return shopCity;
    }

    public void setShopCity(String shopCity) {
        this.shopCity = shopCity;
    }

    public String getShopGov() {
        return shopGov;
    }

    public void setShopGov(String shopGov) {
        this.shopGov = shopGov;
    }

}
