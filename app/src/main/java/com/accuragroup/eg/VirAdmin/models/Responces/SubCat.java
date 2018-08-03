
package com.accuragroup.eg.VirAdmin.models.Responces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SubCat implements Serializable
{

    @SerializedName("cat_parent")
    @Expose
    private int catParent;
    @SerializedName("cat_id")
    @Expose
    private int catId;
    @SerializedName("cat_name")
    @Expose
    private String catName;
    private final static long serialVersionUID = -3013877041763391698L;

    public int getCatParent() {
        return catParent;
    }

    public void setCatParent(int catParent) {
        this.catParent = catParent;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

}
