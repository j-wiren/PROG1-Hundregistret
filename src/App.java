/**
 * @author Joakim Wirén jowi1137
 */

public class App {
    public static void main(String[] args) throws Exception {

        Assignment assignment = new Assignment();

        assignment.registerNewDog();
        assignment.increaseAge();
        assignment.listDogs();
        assignment.removeDog();
        assignment.registerNewDog();
        assignment.registerNewDog();
        assignment.registerNewDog();

        assignment.listDogs();
        assignment.sortDogs();
        assignment.listDogs();

    }
}
