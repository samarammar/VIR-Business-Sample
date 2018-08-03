
package com.accuragroup.eg.VirAdmin.models.Responces;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User implements Serializable
{

    @SerializedName("user_id")
    @Expose
    private int userId;
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("via")
    @Expose
    private String via;
    @SerializedName("user_image")
    @Expose
    private String userImage;
    @SerializedName("user_image_id")
    @Expose
    private int userImageId;
    private final static long serialVersionUID = 3794593963463964847L;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public int getUserImageId() {
        return userImageId;
    }

    public void setUserImageId(int userImageId) {
        this.userImageId = userImageId;
    }

}
