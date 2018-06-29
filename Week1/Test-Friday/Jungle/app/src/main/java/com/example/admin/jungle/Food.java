package com.example.admin.jungle;

public abstract class Food {
    private String typeOfFood;

    protected Food(Jungle jungle,String typeOfFood) {
        this.typeOfFood = typeOfFood;
    }

    public String getTypeOfFood() {
        return typeOfFood;
    }

    public void setTypeOfFood(String typeOfFood) {
        this.typeOfFood = typeOfFood;
    }
}
