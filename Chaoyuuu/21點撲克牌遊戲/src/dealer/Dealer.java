package dealer;

import java.util.ArrayList;

import static utils.Delay.delay;

public class Dealer {
    private ArrayList<Card> cards = new ArrayList<>();

    public Dealer() {
        shuffle();
    }

    private void shuffle() {
        ArrayList<Card> initCards = initCard();
        int size = initCards.size();

        while (size != 0) {
            int random = (int) (Math.random() * size);
            Card card = initCards.remove(random);
            cards.add(card);
            size--;
        }
    }

    private ArrayList<Card> initCard() {
        ArrayList<Card> initCards = new ArrayList<>();

        for (int num = 1; num <= 13; num++) {
            Card cardSpade = new Card(Suit.SPADE, num);
            Card cardHeart = new Card(Suit.HEART, num);
            Card cardDiamond = new Card(Suit.DIAMOND, num);
            Card cardClub = new Card(Suit.CLUB, num);

            initCards.add(cardDiamond);
            initCards.add(cardHeart);
            initCards.add(cardSpade);
            initCards.add(cardClub);
        }
        return initCards;
    }

    public Card deal() {
        if (cards.isEmpty()) {
            System.out.println("**** 沒有牌了，洗牌中請稍後 ****");
            delay(400);
            shuffle();
        }

        int size = cards.size();
        int random = (int) (Math.random() * size);
        return cards.remove(random);
    }

}

