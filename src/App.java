/**
 * @author Joakim Wirén jowi1137
 */

public class App {
    public static void main(String[] args) throws Exception {

        Assignment assignment = new Assignment();

        /*
         * Dog dog1 = new Dog("Leo", "Pudel", 4, 6); Dog dog2 = new Dog("Roffe",
         * "Bulldog", 8, 10);
         * 
         * Owner owner1 = new Owner("Joakim"); Owner owner2 = new Owner("Pelle");
         */

        assignment.registerNewDog();
        assignment.registerNewDog();
        assignment.registerNewDog();
        assignment.registerNewOwner();
        assignment.registerNewOwner();
        assignment.assignDog();
        assignment.assignDog();
        assignment.assignDog();

    }
}
