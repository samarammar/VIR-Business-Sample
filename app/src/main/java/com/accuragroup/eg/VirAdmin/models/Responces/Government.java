
package com.accuragroup.eg.VirAdmin.models.Responces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Government implements Serializable
{

    @SerializedName("governments_id")
    @Expose
    private String governmentsId;
    @SerializedName("gov_id")
    @Expose
    private String govId;
    @SerializedName("governments_name")
    @Expose
    private String governmentsName;
    @SerializedName("gov_name")
    @Expose
    private String govName;
    @SerializedName("governments_created_at")
    @Expose
    private String governmentsCreatedAt;
    @SerializedName("gov_created_at")
    @Expose
    private String govCreatedAt;
    private final static long serialVersionUID = 8943541616846288107L;

    public String getGovernmentsId() {
        return governmentsId;
    }

    public void setGovernmentsId(String governmentsId) {
        this.governmentsId = governmentsId;
    }

    public String getGovId() {
        return govId;
    }

    public void setGovId(String govId) {
        this.govId = govId;
    }

    public String getGovernmentsName() {
        return governmentsName;
    }

    public void setGovernmentsName(String governmentsName) {
        this.governmentsName = governmentsName;
    }

    public String getGovName() {
        return govName;
    }

    public void setGovName(String govName) {
        this.govName = govName;
    }

    public String getGovernmentsCreatedAt() {
        return governmentsCreatedAt;
    }

    public void setGovernmentsCreatedAt(String governmentsCreatedAt) {
        this.governmentsCreatedAt = governmentsCreatedAt;
    }

    public String getGovCreatedAt() {
        return govCreatedAt;
    }

    public void setGovCreatedAt(String govCreatedAt) {
        this.govCreatedAt = govCreatedAt;
    }

}
