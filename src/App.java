/**
 * @author Joakim Wirén jowi1137
 */

public class App {
    public static void main(String[] args) throws Exception {

        Assignment assignment = new Assignment();

        Dog dog1 = new Dog("Leo", "Pudel", 4, 6);
        Dog dog2 = new Dog("Roffe", "Bulldog", 8, 10);

        Owner owner1 = new Owner("Joakim");
        Owner owner2 = new Owner("Pelle");

        assignment.addDog(dog1);
        assignment.addDog(dog2);
        assignment.addOwner(owner1);
        assignment.addOwner(owner2);

        assignment.startAuction();
        assignment.makeBid();
        assignment.makeBid();
        assignment.makeBid();
        assignment.listAuctions();
    }
}
