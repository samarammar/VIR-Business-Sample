package com.accuragroup.eg.VirAdmin.models.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Apex on 9/24/2017.
 */

public class DefaultRequest implements Serializable {
    @SerializedName("access_key")
    @Expose
    private String name;

    @SerializedName("access_password")
    @Expose
    private String AccessPassWord;


    @SerializedName("langu")
    @Expose
    private String langu;


    public void setLangu(String langu) {
        this.langu = langu;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccessPassWord(String accessPassWord) {
        this.AccessPassWord = accessPassWord;
    }
}
