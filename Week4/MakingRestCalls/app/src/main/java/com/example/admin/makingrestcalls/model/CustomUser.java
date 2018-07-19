package com.example.admin.makingrestcalls.model;

import com.example.admin.makingrestcalls.model.APIResponses.example.APIResponse;

public class CustomUser {

    public APIResponse getApiResponse() {
        return apiResponse;
    }

    public void setApiResponse(APIResponse apiResponse) {
        this.apiResponse = apiResponse;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "CustomUser{" +
                "apiResponse=" + apiResponse +
                ", department='" + department + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public CustomUser(APIResponse apiResponse, String department, String location) {

        this.apiResponse = apiResponse;
        this.department = department;
        this.location = location;
    }

    APIResponse apiResponse;
    String department;
    String location;

}
