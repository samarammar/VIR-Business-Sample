package com.accuragroup.eg.VirAdmin.models.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ${sayed} on 11/6/2017.
 */

public class SubCatRequest extends DefaultRequest {


    @SerializedName("catId")
    @Expose
    private int catId;


    public void setCatId(int catId) {
        this.catId = catId;
    }

    public int getCatId() {

        return catId;
    }
}
