package bank;

public class Card {
    private String suit;
    private int number;

    //private or public ??????

    public Card(String suit, int number) {
        this.suit = suit;
        this.number = number;
    }

    public String getSuit() {
        return suit;
    }

    public int getNumber() {
        return number;
    }

}
