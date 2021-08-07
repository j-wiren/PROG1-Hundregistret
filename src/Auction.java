import java.util.*;

public class Auction {
    private Dog dog;
    private int id;
    private HashMap<Owner, Integer> bids = new HashMap<>();

    public Auction(Dog dog, int id) {
        this.dog = dog;
        this.id = id;
    }

    public Dog getDog() {
        return dog;
    }

    public int getID() {
        return id;
    }

    public void makeBid(Owner owner, int bid) {
        bids.put(owner, bid);
    }

    public int getHighestBid() {
        int highestBid = 0;
        for (int bid : bids.values()) {
            if (bid > highestBid) {
                highestBid = bid;
            }
        }
        return highestBid;
    }
}
