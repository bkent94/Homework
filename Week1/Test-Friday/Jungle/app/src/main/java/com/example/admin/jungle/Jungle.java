package com.example.admin.jungle;

import com.example.admin.jungle.Animal;
import java.util.ArrayList;
import java.util.List;

public final class Jungle {
    private  List<Animal> animals;
    private  List<Food> food;

    public Jungle() {
        animals=new ArrayList<>();
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    protected void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    /**
     * Each animal in the jungle must make a sound
     */
    public void soundOff(){
        for(Animal animal:animals){
            System.out.print("A "+ animal.getSpecies()+ " made the sound: ");
            animal.makeSound();
            System.out.println("It has "+animal.getEnergy()+" energy left");
        }
    }

    public List<Food> getFood() {
        return food;
    }

    protected void setFood(List<Food> food) {
        this.food = food;
    }
}
