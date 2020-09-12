package dealer;

public class Card {
    private Suit suit;
    private int number;

    public Card(Suit suit, int number) {
        this.suit = suit;
        this.number = number;
    }

    public String getSuit() {
        return suit.name();
    }

    public int getNumber() {
        return number;
    }

}
