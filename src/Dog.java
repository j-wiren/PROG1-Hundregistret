
/**
 * @author Joakim Wirén jowi1137
 */

import java.util.*;

public class Dog {
    private String name;
    private String breed;
    private int age;
    private int weight;

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

    public double getTailLength() {
        double tailLength;
        double tailLengthDachshund = 3.7;
        List<String> dachs = Arrays.asList("tax", "dachshund", "mäyräkoira", "teckel");
        if (dachs.contains(this.breed.toLowerCase())) {
            tailLength = tailLengthDachshund;
        } else {
            tailLength = age * weight / 10.0;
        }

        return tailLength;
    }

    public int updateAge(int newAge) {
        if (newAge < 0) {
            System.out.println("Den uppdaterade åldern är mindre än den nuvarande.");
            return this.age;
        } else {
            this.age += newAge;
            return this.age;
        }
    }

    public String toString() {
        return "- " + this.name + " (" + this.breed + ", " + this.age + " years, " + this.weight + " kg, "
                + getTailLength() + " cm tail)";
    }
}
