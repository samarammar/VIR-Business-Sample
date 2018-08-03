
package com.accuragroup.eg.VirAdmin.models.Responces;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShopsResponse implements Serializable
{

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("sub_message")
    @Expose
    private String subMessage;
    @SerializedName("return")
    @Expose
    private List<Shop> _return = null;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("total_page")
    @Expose
    private int totalPage;
    @SerializedName("total_shops")
    @Expose
    private String totalShops;
    private final static long serialVersionUID = -4586759205655080081L;

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

    public List<Shop> getReturn() {
        return _return;
    }

    public void setReturn(List<Shop> _return) {
        this._return = _return;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public String getTotalShops() {
        return totalShops;
    }

    public void setTotalShops(String totalShops) {
        this.totalShops = totalShops;
    }

}
