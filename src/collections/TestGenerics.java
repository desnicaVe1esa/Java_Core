package collections;

import java.util.ArrayList;

public class TestGenerics {


    Animal[] animals ={new Dog(), new Cat(), new Dog()};
    Dog[] dogs = {new Dog(), new Dog(), new Dog()};

    public void takeAnimals(Animal[] animals) {
        for (Animal animal : animals) {
            animal.eat();
        }
    }

    public void takeAnimals(ArrayList<Animal> animals) {
        for (Animal animal : animals) {
            animal.eat();
        }
    }

    public void go() {
        System.out.println("Array");
        takeAnimals(animals);
        takeAnimals(dogs);
        System.out.println("\nArrayList");
        ArrayList<Animal> arrayListAnimals = new ArrayList<>();
        arrayListAnimals.add(new Dog());
        arrayListAnimals.add(new Cat());
        arrayListAnimals.add(new Cat());
        takeAnimals(arrayListAnimals);
    }

    public static void main(String[] args) {
        new TestGenerics().go();
    }
}
