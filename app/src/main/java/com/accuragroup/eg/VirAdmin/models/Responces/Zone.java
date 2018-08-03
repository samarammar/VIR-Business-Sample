
package com.accuragroup.eg.VirAdmin.models.Responces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Zone implements Serializable
{

    @SerializedName("zone_id")
    @Expose
    private String zoneId;
    @SerializedName("zone_name")
    @Expose
    private String zoneName;
    @SerializedName("zone_created_at")
    @Expose
    private String zoneCreatedAt;
    private final static long serialVersionUID = 8471651442367898118L;

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getZoneCreatedAt() {
        return zoneCreatedAt;
    }

    public void setZoneCreatedAt(String zoneCreatedAt) {
        this.zoneCreatedAt = zoneCreatedAt;
    }

}
