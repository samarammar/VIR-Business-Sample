
package com.accuragroup.eg.VirAdmin.models.Responces;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OwnerOffersResponce implements Serializable
{

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("sub_message")
    @Expose
    private String subMessage;
    @SerializedName("return")
    @Expose
    private List<OwnerOffers> _return = null;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    private final static long serialVersionUID = 1535698378004497810L;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSubMessage() {
        return subMessage;
    }

    public void setSubMessage(String subMessage) {
        this.subMessage = subMessage;
    }

    public List<OwnerOffers> getReturn() {
        return _return;
    }

    public void setReturn(List<OwnerOffers> _return) {
        this._return = _return;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

}
