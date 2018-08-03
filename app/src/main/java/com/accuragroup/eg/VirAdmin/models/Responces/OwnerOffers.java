
package com.accuragroup.eg.VirAdmin.models.Responces;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OwnerOffers implements Serializable
{

    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("offer_title")
    @Expose
    private String offerTitle;
    @SerializedName("offer_description")
    @Expose
    private String offerDescription;
    @SerializedName("offer_image")
    @Expose
    private String offerImage;
    @SerializedName("offer_image_id")
    @Expose
    private Integer offerImageId;
    @SerializedName("offer_to_date")
    @Expose
    private String offerToDate;
    @SerializedName("offer_from_date")
    @Expose
    private String offerFromDate;
    @SerializedName("from_date")
    @Expose
    private String fromDate;
    @SerializedName("to_date")
    @Expose
    private String toDate;
    @SerializedName("offer_hidden")
    @Expose
    private String offerHidden;
    private final static long serialVersionUID = -3749020577215795963L;

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getOfferTitle() {
        return offerTitle;
    }

    public void setOfferTitle(String offerTitle) {
        this.offerTitle = offerTitle;
    }

    public String getOfferDescription() {
        return offerDescription;
    }

    public void setOfferDescription(String offerDescription) {
        this.offerDescription = offerDescription;
    }

    public String getOfferImage() {
        return offerImage;
    }

    public void setOfferImage(String offerImage) {
        this.offerImage = offerImage;
    }

    public Integer getOfferImageId() {
        return offerImageId;
    }

    public void setOfferImageId(Integer offerImageId) {
        this.offerImageId = offerImageId;
    }

    public String getOfferToDate() {
        return offerToDate;
    }

    public void setOfferToDate(String offerToDate) {
        this.offerToDate = offerToDate;
    }

    public String getOfferFromDate() {
        return offerFromDate;
    }

    public void setOfferFromDate(String offerFromDate) {
        this.offerFromDate = offerFromDate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getOfferHidden() {
        return offerHidden;
    }

    public void setOfferHidden(String offerHidden) {
        this.offerHidden = offerHidden;
    }

}
