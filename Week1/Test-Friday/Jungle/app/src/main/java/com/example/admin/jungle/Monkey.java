package com.example.admin.jungle;

public class Monkey extends Animal {
    protected Monkey(Jungle jungle) {
        super(jungle, "monkey","Eeeee");
    }

    @Override
    public void eatFood(Food food) {
        setEnergy(getEnergy()+2);
    }

    @Override
    public void makeSound() {
        System.out.println(getSound());
        setEnergy(getEnergy()-2);
    }

    public void play(){
        if(getEnergy()>=8){
            System.out.println("Oooo Oooo Oooo");
            setEnergy(getEnergy()-8);
        }
        else{
            System.out.println("Monkey is too tired");
        }
    }
}
