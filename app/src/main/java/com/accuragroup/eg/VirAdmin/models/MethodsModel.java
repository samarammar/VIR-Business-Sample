package com.accuragroup.eg.VirAdmin.models;

/**
 * Created by Apex on 8/30/2017.
 */

public class MethodsModel {

    private int methodtId;
    private String methodName;
    private String methodSetting;

    public MethodsModel(int methodtId, String methodName,String methodSetting) {
        this.methodtId = methodtId;
        this.methodName = methodName.replaceAll("^\"|\"$", "");
        this.methodSetting=methodSetting.replaceAll("^\"|\"$", "");
    }

    public void setMethodSetting(String methodSetting) {
        this.methodSetting = methodSetting;
    }

    public void setMethodtId(int methodtId) {
        this.methodtId = methodtId;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public int getMethodtId() {
        return methodtId;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getMethodSetting() {
        return methodSetting;
    }

    @Override
    public String toString() {
        return methodName;
    }


}
