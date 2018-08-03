package com.accuragroup.eg.VirAdmin.models.Entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ${sayed} on 10/4/2017.
 */

public class Item implements Serializable
{

    @SerializedName("product_id")
    @Expose
    private int productId;
    @SerializedName("qty")
    @Expose
    private int qty;

    private static final long serialVersionUID = 1L;//or some long


    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getQty() {
        return qty;
    }

    public int getProductId() {
        return productId;
    }
}
