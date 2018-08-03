
package com.accuragroup.eg.VirAdmin.models.Responces;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Orders implements Serializable
{

    @SerializedName("order_id")
    @Expose
    private int orderId;
    @SerializedName("order_date")
    @Expose
    private String orderDate;
    @SerializedName("order_shop_name")
    @Expose
    private String orderShopName;
    @SerializedName("order_shop_id")
    @Expose
    private String orderShopId;
    @SerializedName("order_customer_name")
    @Expose
    private String orderCustomerName;
    @SerializedName("order_customer_email")
    @Expose
    private String orderCustomerEmail;
    @SerializedName("order_shop_email")
    @Expose
    private String orderShopEmail;
    @SerializedName("order_customer_phone")
    @Expose
    private String orderCustomerPhone;
    @SerializedName("order_shop_phone")
    @Expose
    private String orderShopPhone;
    @SerializedName("order_total")
    @Expose
    private String orderTotal;
    @SerializedName("order_status")
    @Expose
    private String orderStatus;
    @SerializedName("order_details")
    @Expose
    private List<OrderDetail> orderDetails = null;
    private final static long serialVersionUID = 7451538315103389007L;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderShopName() {
        return orderShopName;
    }

    public void setOrderShopName(String orderShopName) {
        this.orderShopName = orderShopName;
    }

    public String getOrderShopId() {
        return orderShopId;
    }

    public void setOrderShopId(String orderShopId) {
        this.orderShopId = orderShopId;
    }

    public String getOrderCustomerName() {
        return orderCustomerName;
    }

    public void setOrderCustomerName(String orderCustomerName) {
        this.orderCustomerName = orderCustomerName;
    }

    public String getOrderCustomerEmail() {
        return orderCustomerEmail;
    }

    public void setOrderCustomerEmail(String orderCustomerEmail) {
        this.orderCustomerEmail = orderCustomerEmail;
    }

    public String getOrderShopEmail() {
        return orderShopEmail;
    }

    public void setOrderShopEmail(String orderShopEmail) {
        this.orderShopEmail = orderShopEmail;
    }

    public String getOrderCustomerPhone() {
        return orderCustomerPhone;
    }

    public void setOrderCustomerPhone(String orderCustomerPhone) {
        this.orderCustomerPhone = orderCustomerPhone;
    }

    public String getOrderShopPhone() {
        return orderShopPhone;
    }

    public void setOrderShopPhone(String orderShopPhone) {
        this.orderShopPhone = orderShopPhone;
    }

    public String getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(String orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

}
