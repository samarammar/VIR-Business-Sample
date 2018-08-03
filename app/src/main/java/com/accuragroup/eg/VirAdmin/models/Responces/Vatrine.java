
package com.accuragroup.eg.VirAdmin.models.Responces;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vatrine implements Serializable
{

    @SerializedName("vatrine_type")
    @Expose
    private String vatrineType;
    @SerializedName("vatrine_id")
    @Expose
    private String vatrineId;
    @SerializedName("vatrine_name")
    @Expose
    private String vatrineName;
    @SerializedName("vatrine_image")
    @Expose
    private String vatrineImage;
    @SerializedName("vatrine_details")
    @Expose
    private VatrineDetails vatrineDetails;
    @SerializedName("vatrine_mixed_data")
    @Expose
    private String vatrineMixedData;
    private final static long serialVersionUID = 1771309363771535647L;

    public String getVatrineType() {
        return vatrineType;
    }

    public void setVatrineType(String vatrineType) {
        this.vatrineType = vatrineType;
    }

    public String getVatrineId() {
        return vatrineId;
    }

    public void setVatrineId(String vatrineId) {
        this.vatrineId = vatrineId;
    }

    public String getVatrineName() {
        return vatrineName;
    }

    public void setVatrineName(String vatrineName) {
        this.vatrineName = vatrineName;
    }

    public String getVatrineImage() {
        return vatrineImage;
    }

    public void setVatrineImage(String vatrineImage) {
        this.vatrineImage = vatrineImage;
    }

    public VatrineDetails getVatrineDetails() {
        return vatrineDetails;
    }

    public void setVatrineDetails(VatrineDetails vatrineDetails) {
        this.vatrineDetails = vatrineDetails;
    }

    public String getVatrineMixedData() {
        return vatrineMixedData;
    }

    public void setVatrineMixedData(String vatrineMixedData) {
        this.vatrineMixedData = vatrineMixedData;
    }

}
