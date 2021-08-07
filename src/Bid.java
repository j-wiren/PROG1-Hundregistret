public class Bid {
    private Owner owner;
    private int bid;

    public Bid(Owner owner, int bid) {
        this.owner = owner;
        this.bid = bid;
    }

    public Owner getOwner() {
        return owner;
    }

    public int getBid() {
        return bid;
    }

}
