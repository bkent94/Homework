package com.example.admin.daily2.model.data;

public class Category {
    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                '}';
    }

    private String name;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
