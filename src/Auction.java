import java.util.*;

public class Auction {
    private Dog dog;
    private int id;
    private HashMap<Owner, Bid> bids = new HashMap<>();

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

    public void makeBid(Owner owner, Bid bid) {
        bids.put(owner, bid);
    }

    public int getHighestBid() {
        int highestBid = 0;
        for (Bid bid : bids.values()) {
            if (bid.getBid() > highestBid) {
                highestBid = bid.getBid();
            }
        }
        return highestBid;
    }

    public void listBids() {
        if (bids.isEmpty()) {
            System.out.println("There are no bids registered for this auction.");
            return;
        } else {
            System.out.println("These are the registered bids for this auction:");
            ArrayList<Bid> bidList = new ArrayList<>(bids.values());
            sortBids(bidList);
            for (Bid bid : bidList) {
                System.out.println(bid.getOwner().getName() + ": " + bid.getBid());
            }
        }
    }

    // Kopierad sortering från sortDogs
    private void sortBids(ArrayList<Bid> bidList) {
        boolean hasSwapped = true;
        while (hasSwapped) {
            hasSwapped = false;
            for (int i = 0; i < bidList.size() - 1; i++) {
                if (bidList.get(i).getBid() < bidList.get(i + 1).getBid()) {
                    Collections.swap(bidList, i, i + 1);
                    hasSwapped = true;
                }
            }
        }
    }
}
