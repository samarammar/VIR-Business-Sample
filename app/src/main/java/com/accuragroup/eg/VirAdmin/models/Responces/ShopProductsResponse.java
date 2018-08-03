
package com.accuragroup.eg.VirAdmin.models.Responces;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShopProductsResponse implements Serializable
{

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("sub_message")
    @Expose
    private String subMessage;
    @SerializedName("return")
    @Expose
    private List<ShopProduct> _return = null;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = 670539815853578708L;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSubMessage() {
        return subMessage;
    }

    public void setSubMessage(String subMessage) {
        this.subMessage = subMessage;
    }

    public List<ShopProduct> getReturn() {
        return _return;
    }

    public void setReturn(List<ShopProduct> _return) {
        this._return = _return;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
