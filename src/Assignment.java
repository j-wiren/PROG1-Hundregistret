// @author Joakim Wirén jowi1137

import java.util.*;

public class Assignment {

    /*
     * Allt eftersom du skriver dina metoder fyller du i deras namn i konstanterna
     * nedan. Testprogrammet använder dessa konstanter för att hitta dina metoder,
     * så det är viktigt att namnen stämmer.
     */
    public static final String REGISTER_NEW_DOG_METHOD = "registerNewDog"; // U7.1
    public static final String LIST_DOGS_METHOD = "listDogs"; // U7.2 och U8.4
    public static final String FIND_DOG_METHOD = "findDog"; // U7.3 - hjälpmetod tänkt att användas i de följande stegen
    public static final String INCREASE_AGE_METHOD = "increaseAge"; // U7.4
    public static final String REMOVE_DOG_METHOD = "removeDog"; // U7.5, U8.6 och U9.6
    public static final String SORT_DOGS_METHOD = "sortDogs"; // U7.6
    public static final String REGISTER_NEW_OWNER_METHOD = "registerNewOwner"; // U8.1
    public static final String FIND_OWNER_METHOD = "findOwner"; // U8.2 - hjälpmetod tänkt att användas i de följande
                                                                // stegen
    public static final String GIVE_DOG_METHOD = "assignDog"; // U8.3 och framåt
    public static final String LIST_OWNERS_METHOD = "listOwners"; // U8.4
    public static final String OWNER_OF_DOG_METHOD = "getOwner"; // U8.5, obs! metoden ska ligga i Owner-klassen
    public static final String REMOVE_OWNER_METHOD = "removeOwner"; // U8.7 och U9.6
    public static final String START_AUCTION_METHOD = "startAuction"; // U9.1 och framåt
    public static final String FIND_AUCTION_METHOD = "findAuction"; // U9.2 - hjälpmetod tänkt att användas i de
                                                                    // följande
    // stegen
    public static final String MAKE_BID_METHOD = "makeBid"; // U9.3 och framåt
    public static final String LIST_BIDS_METHOD = "listBids"; // U9.4 och framåt
    public static final String LIST_AUCTIONS_METHOD = "listAuctions"; // U9.5 och framåt
    public static final String CLOSE_AUCTION_METHOD = "closeAuction"; // U9.6

    /********************************************************************************
     * Här nedanför skriver du dina metoder. Du kommer att kunna lämna in samma
     * fil(er) i samtliga inlämningar, så du behöver inte börja om för varje ny
     * metod.
     ********************************************************************************/

    private static Scanner scanner = new Scanner(System.in);

    private ArrayList<Dog> dogList = new ArrayList<>();

    private ArrayList<Owner> ownerList = new ArrayList<>();

    public void registerNewDog() {
        System.out.print("Name?> ");
        String name = scanner.nextLine();
        System.out.print("Breed?> ");
        String breed = scanner.nextLine();
        System.out.print("Age?> ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Weight?> ");
        int weight = scanner.nextInt();
        scanner.nextLine();

        Dog dog = new Dog(name, breed, age, weight);
        this.dogList.add(dog);

        System.out.println(name + " added to the register");
    }

    public void listDogs() {
        if (this.dogList.isEmpty()) {
            System.out.println("Error: no dogs in register");
        } else {
            System.out.print("Shortest tail length to display?> ");
            double minTailLength = scanner.nextDouble();
            scanner.nextLine();
            int count = 0;

            for (Dog dog : dogList) {
                double tailLength = dog.getTailLength();

                if (tailLength >= minTailLength) {
                    System.out.println(dog);
                    count++;
                }
            }
            if (count == 0) {
                System.out.println("No dogs fit this criteria.");
            }
        }
    }

    public Dog findDog(String searchQuery) {
        for (Dog dog : dogList) {
            if (dog.getName().toLowerCase().equals(searchQuery.toLowerCase())) {
                return dog;
            }
        }
        return null;
    }

    public void increaseAge() {
        System.out.print("Which dog should have its age increased?> ");
        String searchQuery = scanner.nextLine();
        Dog dog = findDog(searchQuery);
        if (dog == null) {
            System.out.println("Error: No dog was found.");
            return;
        }
        dog.updateAge(1);

        System.out.println(dog.getName() + "'s age has been increased.");

    }

    public void removeDog() {
        System.out.print("Which dog should be removed?> ");
        String searchQuery = scanner.nextLine();
        Dog dog = findDog(searchQuery);
        if (dog == null) {
            System.out.println("Error: No dog was found.");
            return;
        }
        dogList.remove(dog);
        System.out.println(dog.getName() + " has been removed.");
    }

    /*
     * Sorteringsalgoritmen är bubble sort -
     * https://www.bbc.co.uk/bitesize/guides/z22wwmn/revision/3 Metoden sortDogs går
     * igenom listan av hundar och jämför två i taget med hjälp av
     * compareDogs-metoden. Om den första är större än den andra byter så byter de
     * plats. Om de är lika stora jämförs namnen. Även här byter de plats om den
     * första kommer efter den andra i alfabetisk ordning. När alla har jämförts
     * startar processen om, så länge några har bytit plats.
     */

    public void sortDogs() {
        boolean hasSwapped = true;
        while (hasSwapped) {
            hasSwapped = false;
            for (int i = 0; i < dogList.size() - 1; i++) {
                if (compareDogs(dogList.get(i), dogList.get(i + 1))) {
                    Collections.swap(dogList, i, i + 1);
                    hasSwapped = true;
                }
            }
        }
    }

    private boolean compareDogs(Dog dogA, Dog dogB) {
        if (dogA.getTailLength() < dogB.getTailLength()) {
            return false;
        } else if (dogA.getTailLength() > dogB.getTailLength()) {
            return true;
        } else {
            return dogA.getName().compareTo(dogB.getName()) > 0;
        }
    }

    public void registerNewOwner() {
        System.out.print("Name?> ");
        String name = scanner.nextLine();

        Owner owner = new Owner(name);
        this.ownerList.add(owner);
    }

    public Owner findOwner(String searchQuery) {
        for (Owner owner : ownerList) {
            if (owner.getName().toLowerCase().equals(searchQuery.toLowerCase())) {
                return owner;
            }
        }
        return null;
    }

    public void assignDog() {
        System.out.print("Enter the dog's name?> ");
        String dogName = scanner.nextLine();
        Dog foundDog = findDog(dogName);

        if (foundDog == null) {
            System.out.println("Error: No dog was found.");
            return;
        }

        if (foundDog.getOwner() != null) {
            System.out.println("Error: " + foundDog.getName() + " already has an owner.");
            return;
        }

        System.out.print("Enter the owner's name?> ");
        String ownerName = scanner.nextLine();
        Owner foundOwner = findOwner(ownerName);

        if (foundOwner == null) {
            System.out.println("Error: No owner was found.");
            return;
        }

        foundDog.assignOwner(foundOwner);
        // foundOwner.addDog(foundDog);
    }

    /*
     * Metoderna nedan är till för att testprogrammet ska sätta upp och kontrollera
     * olika saker. De är INTE tänkta att användas i din egen kod. Du måste fylla i
     * den saknade koden i metoderna allteftersom de behövs av testprogrammet.
     */

    /*
     * Byt ut koden i nedanstående metod så att den väntar på att användaren trycker
     * på return. Du gör detta genom att anropa nextLine-metoden på din scanner.
     * 
     * Om du inte du gjort övningen till F6 där man ska skriva en egen klass för att
     * hantera inmatning så gör den. Den är ett bra exempel på en klass med
     * funktionalitet, och kommer att göra inlämningsuppgifterna enklare eftersom du
     * inte kommer att drabbas av några vanliga fel.
     * 
     * Behövs från U7.5, eventuellt tidigare
     */
    public void waitForUserInput() {
        scanner.nextLine();
    }

    /*
     * Byt ut koden i nedanstående metod så att hunden läggs in i listan av hundar.
     * Kravet i uppgiften är formulerat så att en ArrayList ska användas, men det är
     * okej att använda andra klasser ur Javas Collection-api om du känner till dem.
     * 
     * Behövs från U7.2
     */
    public void addDog(Dog dog) {
        dogList.add(dog);
    }

    /*
     * Byt ut koden i nedanstående metod så att listan på hundar returneras.
     * 
     * Kravet i uppgiften är formulerat så att en ArrayList ska användas, men det är
     * okej att använda andra list-klasser ur Javas Collection-api om du känner till
     * det, och föredrar en annan klass därifrån. Returtypen List gör att det går
     * att skicka tillbaka vilken listtyp som helst.
     * 
     * Denna metod är ENBART till för testprogrammet i steg U7.1 till U9.7. Den ska
     * nästan säkert INTE finnas i det slutgiltiga fullständiga programmet, så
     * använd den inte i din egen kod.
     * 
     * Behövs från U7.1
     */
    public List<Dog> getDogs() {
        return dogList;
    }

    /*
     * Byt ut koden i nedanstående metod så att ägaren läggs in i listan av ägare.
     * Uppgiften har inget specifikt krav på vilken typ av samling du ska använda
     * för detta, utan det får du bestämma själv. Det kan vara en array, en
     * ArrayList, en annan av Javas samlingsklasser, eller något du skrivit själv.
     * 
     * Kravet i uppgiften är formulerat så att en ArrayList ska användas, men det är
     * okej att använda andra klasser ur Javas Collection-api om du känner till det,
     * 
     * Behövs från U8.2
     */
    public void addOwner(Owner o) {
        ownerList.add(o);
    }

    /*
     * Byt ut koden i nedanstående metod så att ägaren läggs in i listan av ägare.
     * Uppgiften har inget specifikt krav på vilken typ av samling du ska använda
     * för detta, utan det får du bestämma själv. Det kan vara en array, en
     * ArrayList, en annan av Javas samlingsklasser, eller något du skrivit själv.
     * 
     * Kravet i uppgiften är formulerat så att en ArrayList ska användas, men det är
     * okej att använda andra klasser ur Javas Collection-api om du känner till dem.
     * 
     * Behövs från U8.1
     */
    public List<Owner> getOwners() {
        return ownerList;
    }

    /*
     * Om du använder en array för att spara ägarna kan nedanstående variant
     * användas istället
     */
    // public Collection<Owner> getOwners() {
    // return Arrays.asList(NAMNET_PÅ_ARRAYEN);
    // }
}