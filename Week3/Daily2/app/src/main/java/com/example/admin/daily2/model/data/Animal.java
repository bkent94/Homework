package com.example.admin.daily2.model.data;

public class Animal {
    private String name;

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", sound='" + sound + '\'' +
                ", details='" + details + '\'' +
                ", weight='" + weight + '\'' +
                '}';
    }

    private String category;
    private String sound;
    private String details;
    private String weight;

    public Animal(String name, String category, String sound, String details, String weight) {
        this.name = name;
        this.category = category;
        this.sound = sound;
        this.details = details;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
