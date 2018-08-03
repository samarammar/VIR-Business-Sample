package com.accuragroup.eg.VirAdmin.models.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Apex on 5/9/2018.
 */

public class PaymentRequest extends DefaultRequest{
    @SerializedName("ownerId")
    @Expose
    private int ownerId;

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}
