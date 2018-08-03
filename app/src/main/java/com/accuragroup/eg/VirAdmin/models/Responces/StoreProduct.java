
package com.accuragroup.eg.VirAdmin.models.Responces;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoreProduct {

    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("product_classifications")
    @Expose
    private List<Integer> productClassifications = null;
    @SerializedName("product_categories")
    @Expose
    private List<Integer> productCategories = null;
    @SerializedName("main_category")
    @Expose
    private int mainCategory;
    @SerializedName("product_category")
    @Expose
    private String productCategory;
    @SerializedName("sub_category")
    @Expose
    private int subCategory;
    @SerializedName("product_subcategory")
    @Expose
    private String productSubcategory;
    @SerializedName("category_path")
    @Expose
    private String categoryPath;
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
    @SerializedName("rate")
    @Expose
    private int rate;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("product_owner")
    @Expose
    private String productOwner;
    @SerializedName("product_owner_id")
    @Expose
    private String productOwnerId;
    @SerializedName("can_reserve")
    @Expose
    private int canReserve;
    @SerializedName("product_gallery")
    @Expose
    private List<String> productGallery = null;
    @SerializedName("product_gallery_ids")
    @Expose
    private String productGalleryIds;
    @SerializedName("is_featured")
    @Expose
    private int isFeatured;
    @SerializedName("is_exists")
    @Expose
    private int isExists;
    @SerializedName("is_hidden")
    @Expose
    private int isHidden;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("gov")
    @Expose
    private String gov;
    @SerializedName("zone")
    @Expose
    private String zone;
    @SerializedName("reserved")
    @Expose
    private int reserved;
    @SerializedName("favorites")
    @Expose
    private int favorites;

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public List<Integer> getProductClassifications() {
        return productClassifications;
    }

    public void setProductClassifications(List<Integer> productClassifications) {
        this.productClassifications = productClassifications;
    }

    public List<Integer> getProductCategories() {
        return productCategories;
    }

    public void setProductCategories(List<Integer> productCategories) {
        this.productCategories = productCategories;
    }

    public int getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(int mainCategory) {
        this.mainCategory = mainCategory;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public int getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(int subCategory) {
        this.subCategory = subCategory;
    }

    public String getProductSubcategory() {
        return productSubcategory;
    }

    public void setProductSubcategory(String productSubcategory) {
        this.productSubcategory = productSubcategory;
    }

    public String getCategoryPath() {
        return categoryPath;
    }

    public void setCategoryPath(String categoryPath) {
        this.categoryPath = categoryPath;
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

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductOwner() {
        return productOwner;
    }

    public void setProductOwner(String productOwner) {
        this.productOwner = productOwner;
    }

    public String getProductOwnerId() {
        return productOwnerId;
    }

    public void setProductOwnerId(String productOwnerId) {
        this.productOwnerId = productOwnerId;
    }

    public int getCanReserve() {
        return canReserve;
    }

    public void setCanReserve(int canReserve) {
        this.canReserve = canReserve;
    }

    public List<String> getProductGallery() {
        return productGallery;
    }

    public void setProductGallery(List<String> productGallery) {
        this.productGallery = productGallery;
    }

    public String getProductGalleryIds() {
        return productGalleryIds;
    }

    public void setProductGalleryIds(String productGalleryIds) {
        this.productGalleryIds = productGalleryIds;
    }

    public int getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(int isFeatured) {
        this.isFeatured = isFeatured;
    }

    public int getIsExists() {
        return isExists;
    }

    public void setIsExists(int isExists) {
        this.isExists = isExists;
    }

    public int getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(int isHidden) {
        this.isHidden = isHidden;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGov() {
        return gov;
    }

    public void setGov(String gov) {
        this.gov = gov;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public int getReserved() {
        return reserved;
    }

    public void setReserved(int reserved) {
        this.reserved = reserved;
    }

    public int getFavorites() {
        return favorites;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }

}
