public class Auction {
    private Dog dog;
    private int id;

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
}
