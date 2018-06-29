package com.example.admin.jungle;

public abstract class Animal {
    private int energy=5;
    private String sound="";
    private String species="";
    private Jungle jungle;

   protected Animal(Jungle jungle,String species,String sound){
       setJungle(jungle);
        jungle.getAnimals().add(this);
        setSpecies(species);
        setSound(sound);
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public int getNumSpecies() {
        int count=0;
        for(Animal animal:getJungle().getAnimals()){
            if(getSpecies().toUpperCase().equals(animal.getSpecies().toUpperCase())){
                count++;
            }
        }
        return count ;
    }



    public void makeSound(){
        System.out.println(getSound());
        energy-=3;
    }

    public void eatFood(Food food){
        this.energy+=5;
    }

    public void sleep(){
        energy+=10;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Jungle getJungle() {
        return jungle;
    }

    public void setJungle(Jungle jungle) {
        this.jungle = jungle;
    }
}
