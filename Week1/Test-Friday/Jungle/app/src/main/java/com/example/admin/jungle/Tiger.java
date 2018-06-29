package com.example.admin.jungle;

import com.example.admin.jungle.Animal;

public class Tiger extends Animal {
    protected Tiger(Jungle jungle) {
        super(jungle, "tiger","Raaaa");
    }

    @Override
    public void sleep() {
        setEnergy(getEnergy()+5);
    }

    @Override
    public void eatFood(Food food) {
        if(food.getTypeOfFood().toUpperCase().equals("GRAIN"))
            System.out.println("Tigers can not eat grain they have sensitive digestive systems");
        else
        super.eatFood(food);
    }
}
