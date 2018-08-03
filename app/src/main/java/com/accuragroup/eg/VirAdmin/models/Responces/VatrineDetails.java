
package com.accuragroup.eg.VirAdmin.models.Responces;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VatrineDetails implements Serializable
{

    @SerializedName("product_category")
    @Expose
    private String productCategory;
    @SerializedName("product_parent_category")
    @Expose
    private String productParentCategory;
    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("product_image")
    @Expose
    private String productImage;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("sale_price")
    @Expose
    private String salePrice;
    @SerializedName("product_owner")
    @Expose
    private String productOwner;
    @SerializedName("products_delivery")
    @Expose
    private String productsDelivery;
    @SerializedName("can_reserve")
    @Expose
    private int canReserve;
    @SerializedName("product_description")
    @Expose
    private String productDescription;
    @SerializedName("product_rate")
    @Expose
    private int productRate;
    @SerializedName("product_reviews")
    @Expose
    private int productReviews;
    @SerializedName("product_likes")
    @Expose
    private int productLikes;
    @SerializedName("gov")
    @Expose
    private String gov;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("zone")
    @Expose
    private String zone;
    private final static long serialVersionUID = 3725819418257585354L;

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductParentCategory() {
        return productParentCategory;
    }

    public void setProductParentCategory(String productParentCategory) {
        this.productParentCategory = productParentCategory;
    }

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getProductOwner() {
        return productOwner;
    }

    public void setProductOwner(String productOwner) {
        this.productOwner = productOwner;
    }

    public String getProductsDelivery() {
        return productsDelivery;
    }

    public void setProductsDelivery(String productsDelivery) {
        this.productsDelivery = productsDelivery;
    }

    public int getCanReserve() {
        return canReserve;
    }

    public void setCanReserve(int canReserve) {
        this.canReserve = canReserve;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getProductRate() {
        return productRate;
    }

    public void setProductRate(int productRate) {
        this.productRate = productRate;
    }

    public int getProductReviews() {
        return productReviews;
    }

    public void setProductReviews(int productReviews) {
        this.productReviews = productReviews;
    }

    public int getProductLikes() {
        return productLikes;
    }

    public void setProductLikes(int productLikes) {
        this.productLikes = productLikes;
    }

    public String getGov() {
        return gov;
    }

    public void setGov(String gov) {
        this.gov = gov;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

}
