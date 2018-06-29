package com.example.admin.jungle;

public class Main {

    public static void main(String[] args) {
        Jungle jungle=new Jungle();

        Tiger tiger1=new Tiger(jungle);
        Tiger tiger2=new Tiger(jungle);
        jungle.soundOff();
        tiger1.eatFood(new Grain(jungle));
        Monkey monkey1=new Monkey(jungle);
        monkey1.sleep();
        System.out.println("The monkey is playing");
        monkey1.play();
        System.out.println("Tiger 1 knows there are "+tiger1.getNumSpecies()+" Tigers in the jungle");
        jungle.soundOff();
        Snake snake1=new Snake(jungle);
        System.out.print("Snake: ");
        snake1.makeSound();

    }
}
