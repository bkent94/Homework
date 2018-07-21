
package com.example.admin.daily4.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Params {

    @SerializedName("sane")
    @Expose
    private List<Object> sane = null;
    @SerializedName("q")
    @Expose
    private List<String> q = null;
    @SerializedName("app_key")
    @Expose
    private List<String> appKey = null;
    @SerializedName("app_id")
    @Expose
    private List<String> appId = null;

    public List<Object> getSane() {
        return sane;
    }

    public void setSane(List<Object> sane) {
        this.sane = sane;
    }

    public List<String> getQ() {
        return q;
    }

    public void setQ(List<String> q) {
        this.q = q;
    }

    public List<String> getAppKey() {
        return appKey;
    }

    public void setAppKey(List<String> appKey) {
        this.appKey = appKey;
    }

    public List<String> getAppId() {
        return appId;
    }

    public void setAppId(List<String> appId) {
        this.appId = appId;
    }

}
