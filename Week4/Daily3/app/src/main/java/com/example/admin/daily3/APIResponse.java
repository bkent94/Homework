
package com.example.admin.daily3;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.adapter.rxjava2.Result;

public class APIResponse {

    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    @SerializedName("info")
    @Expose
    private Info info;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

}
