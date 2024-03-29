
/**
 * @author Joakim Wirén jowi1137
 */

import java.util.*;

public class Program {

    private Scanner scanner = new Scanner(System.in);

    private ArrayList<Dog> dogList = new ArrayList<>();
    private ArrayList<Owner> ownerList = new ArrayList<>();
    private ArrayList<Auction> auctionList = new ArrayList<>();

    private int auctionIdCounter = 1;

    public Scanner getScanner() {
        return this.scanner;
    }

    private String inputString(String prompt) {
        System.out.print(prompt + "?> ");
        String stringInput = scanner.nextLine().trim();
        return stringInput;
    }

    private int inputInt(String prompt) {
        System.out.print(prompt + "?> ");
        int intInput = scanner.nextInt();
        scanner.nextLine();
        return intInput;
    }

    private double inputDouble(String prompt) {
        System.out.print(prompt + "?> ");
        double doubleInput = scanner.nextDouble();
        scanner.nextLine();
        return doubleInput;
    }

    private String inputName(String prompt) {
        String textInput = "";
        while (textInput.isEmpty()) {
            textInput = inputString(prompt).toLowerCase();
            if (textInput.isEmpty()) {
                System.out.println("Error: The name can't be empty.");
            }
        }

        // Gör att strängen börjar med stor bokstav
        textInput = textInput.substring(0, 1).toUpperCase() + textInput.substring(1);
        return textInput;
    }

    public void registerNewDog() {
        String name = inputName("Name");
        String breed = inputName("Breed");
        int age = inputInt("Age");
        int weight = inputInt("Weight");

        Dog dog = new Dog(name, breed, age, weight);
        this.dogList.add(dog);

        System.out.println(name + " added to the register");
    }

    public void removeDog() {
        String searchQuery = inputString("Which dog should be removed").toLowerCase();
        Dog dog = findDog(searchQuery);
        if (dog == null) {
            System.out.println("Error: No dog was found.");
            return;
        }
        dogList.remove(dog);
        if (dog.getOwner() != null) {
            dog.getOwner().removeDog(dog);
        }
        Auction auction = findAuction(dog);
        if (auction != null) {
            auctionList.remove(auction);
        }
        System.out.println(dog.getName() + " has been removed from the register");
    }

    public void listDogs() {
        if (this.dogList.isEmpty()) {
            System.out.println("Error: no dogs in register");
            return;
        }
        double minTailLength = inputDouble("Shortest tail length to display");
        int count = 0;

        sortDogs();

        for (Dog dog : dogList) {
            double tailLength = dog.calcTailLength();

            if (tailLength >= minTailLength) {
                System.out.println(dog);
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No dogs fit this criteria.");
        }
    }

    private Dog findDog(String searchQuery) {
        for (Dog dog : dogList) {
            if (dog.getName().toLowerCase().equals(searchQuery.toLowerCase())) {
                return dog;
            }
        }
        return null;
    }

    /*
     * Sorteringsalgoritm: Bubble Sort
     * https://www.bbc.co.uk/bitesize/guides/z22wwmn/revision/3 Metoden sortDogs går
     * igenom listan av hundar och jämför två i taget med hjälp av
     * compareDogs-metoden. Om den första är större än den andra byter så byter de
     * plats. Om de är lika stora jämförs namnen. Även här byter de plats om den
     * första kommer efter den andra i alfabetisk ordning. När alla har jämförts
     * startar processen om, så länge några har bytt plats.
     */

    private void sortDogs() {
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
        if (dogA.calcTailLength() < dogB.calcTailLength()) {
            return false;
        } else if (dogA.calcTailLength() > dogB.calcTailLength()) {
            return true;
        } else {
            return dogA.getName().compareTo(dogB.getName()) > 0;
        }
    }

    private Dog inputDog() {
        String dogName = inputString("Enter the dog's name");
        Dog foundDog = findDog(dogName);

        if (foundDog == null) {
            System.out.println("Error: No dog was found.");
            return null;
        }

        return foundDog;
    }

    public void increaseAge() {
        String searchQuery = inputString("Which dog should have its age increased");
        Dog dog = findDog(searchQuery);
        if (dog == null) {
            System.out.println("Error: No dog was found.");
            return;
        }
        dog.updateAge(1);

        System.out.println(dog.getName() + "'s age has been increased.");

    }

    public void assignDog() {
        Dog foundDog = inputDog();

        if (foundDog == null) {
            return;
        }

        if (foundDog.getOwner() != null) {
            System.out.println("Error: " + foundDog.getName() + " already has an owner.");
            return;
        }

        Owner foundOwner = inputOwner();
        if (foundOwner == null) {
            return;
        }

        foundOwner.addDog(foundDog);

        Auction foundAuction = findAuction(foundDog);
        if (foundAuction != null) {
            removeAuction(foundAuction);
        }
    }

    public void registerNewOwner() {
        String name = inputName("Name");

        Owner owner = new Owner(name);
        this.ownerList.add(owner);

        System.out.println(name + " added to the register.");
    }

    public void removeOwner() {
        String searchQuery = inputString("Which owner should be removed");
        Owner owner = findOwner(searchQuery);
        if (owner == null) {
            System.out.println("Error: No owner was found.");
        } else {
            ownerList.remove(owner);

            if (owner.getDogs() != null) {
                for (Dog dog : owner.getDogs()) {
                    dogList.remove(dog);
                }
                owner.removeAllDogs();
            }

            for (Auction auction : auctionList) {
                auction.removeBid(owner);
            }

            System.out.println(owner.getName() + " has been removed from the register");
        }
    }

    public void listOwners() {
        if (ownerList.isEmpty()) {
            System.out.println("Error: There are no owners in the register");
        } else {
            for (Owner owner : ownerList) {
                if (owner.getDogs() == null) {
                    System.out.println(owner.getName() + " []");
                } else {
                    ArrayList<String> ownersDogs = new ArrayList<>();
                    for (Dog dog : owner.getDogs()) {
                        ownersDogs.add(dog.getName());
                    }
                    System.out.println(owner.getName() + " " + ownersDogs);
                }
            }
        }
    }

    private Owner findOwner(String searchQuery) {
        for (Owner owner : ownerList) {
            if (owner.getName().toLowerCase().equals(searchQuery.toLowerCase())) {
                return owner;
            }
        }
        return null;
    }

    private Owner inputOwner() {
        String ownerName = inputString("Enter the owner's name");
        Owner foundOwner = findOwner(ownerName);

        if (foundOwner == null) {
            System.out.println("Error: No owner was found.");
            return null;
        }

        return foundOwner;
    }

    public void startAuction() {
        String dogName = inputString("Enter the dog's name");
        Dog foundDog = findDog(dogName);

        if (foundDog == null) {
            System.out.println("Error: No dog was found.");
            return;
        }

        if (foundDog.getOwner() != null) {
            System.out.println("Error: " + foundDog.getName() + " already has an owner.");
            return;
        }

        if (findAuction(foundDog) != null) {
            System.out.println("Error: " + foundDog.getName() + " is already up for auction.");
            return;
        }

        Auction auction = new Auction(foundDog, auctionIdCounter++);
        auctionList.add(auction);

        System.out.println(foundDog.getName() + " has been put up for auction in auction #" + auction.getID() + ".");

    }

    public void listAuctions() {
        if (auctionList.isEmpty()) {
            System.out.println("Error: There are no auctions in progress.");
            return;
        }
        for (Auction auction : auctionList) {
            System.out.println(auction);
        }
    }

    public void closeAuction() {
        Dog foundDog = inputDog();
        if (foundDog == null) {
            return;
        }

        Auction foundAuction = findAuction(foundDog);
        if (foundAuction == null) {
            System.out.println("Error: " + foundDog.getName() + " is not up for auction.");
            return;
        }

        Bid highestBid = foundAuction.getHighestBid();

        System.out.print("The auction is closed. ");

        if (highestBid != null) {
            highestBid.getOwner().addDog(foundDog);

            System.out.printf("The winning bid for %s was %dkr and was made by %s.\n", foundDog.getName(),
                    highestBid.getBid(), highestBid.getOwner().getName());
        } else {
            System.out.println("No bids were made for " + foundDog.getName() + ".");
        }

        removeAuction(foundAuction);

    }

    private void removeAuction(Auction auction) {
        auctionList.remove(auction);
    }

    private Auction findAuction(Dog dog) {
        for (Auction auction : auctionList) {
            if (auction.getDog() == dog) {
                return auction;
            }
        }
        return null;
    }

    public void makeBid() {
        Owner foundOwner = inputOwner();
        if (foundOwner == null) {
            return;
        }

        Dog foundDog = inputDog();
        if (foundDog == null) {
            return;
        }

        Auction foundAuction = findAuction(foundDog);
        if (foundAuction == null) {
            System.out.println("Error: " + foundDog.getName() + " is not up for auction.");
            return;
        }

        int bid = inputBid(foundAuction);

        foundAuction.makeBid(new Bid(foundOwner, bid));

        System.out.println("Your bid has been made.");

    }

    private int inputBid(Auction auction) {
        Bid highestBid = auction.getHighestBid();
        int highestBidAmount = 0;
        if (highestBid != null) {
            highestBidAmount = highestBid.getBid();
        }
        int bid = 0;
        while (bid < highestBidAmount + 1) {
            bid = inputInt("Enter your bid (min " + (highestBidAmount + 1) + ")");
            if (bid < highestBidAmount + 1) {
                System.out.println("Error: Your bid is too low.");
            }
        }
        return bid;
    }

    public void listBids() {
        Dog foundDog = inputDog();
        if (foundDog == null) {
            return;
        }
        Auction foundAuction = findAuction(foundDog);
        if (foundAuction == null) {
            System.out.println("Error: " + foundDog.getName() + " is not up for auction.");
            return;
        }

        foundAuction.listBids();
    }

}