
/**
 * @author Joakim Wirén jowi1137
 */

import java.util.*;

public class Dog {
    private static final double DACHSHUND_TAIL_LENGTH = 3.7;

    private String name;
    private String breed;
    private int age;
    private int weight;
    private Owner owner;

    public Dog(String name, String breed, int age, int weight) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }

    public int getWeight() {
        return weight;
    }

    public double calcTailLength() {
        double tailLength;
        List<String> dachs = Arrays.asList("tax", "dachshund", "mäyräkoira", "teckel");
        if (dachs.contains(this.breed.toLowerCase())) {
            tailLength = DACHSHUND_TAIL_LENGTH;
        } else {
            tailLength = age * weight / 10.0;
        }

        return tailLength;
    }

    public Owner getOwner() {
        return owner;
    }

    public void assignOwner(Owner o) {
        owner = o;

        boolean dogFound = false;

        // kollar om ägaren redan har hunden
        if (o.getDogs() != null) {
            for (Dog dog : o.getDogs()) {
                if (dog == this) {
                    dogFound = true;
                }
            }
        }

        if (!dogFound) {
            o.addDog(this);
        }
    }

    public int updateAge(int newAge) {
        if (newAge < 0) {
            System.out.println("Error: The given age is lower than the current.");
            return this.age;
        } else {
            this.age += newAge;
            return this.age;
        }
    }

    public String toString() {
        if (this.getOwner() != null) {
            return "- " + this.name + " (" + this.breed + ", " + this.age + " years, " + this.weight + " kg, "
                    + calcTailLength() + " cm tail, owned by " + this.getOwner().getName() + ")";
        } else {
            return "- " + this.name + " (" + this.breed + ", " + this.age + " years, " + this.weight + " kg, "
                    + calcTailLength() + " cm tail)";
        }
    }
}
