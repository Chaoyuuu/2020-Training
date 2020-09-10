package bank;
import java.util.ArrayList;

public class Dealer {
    private ArrayList<Card> cards = new ArrayList<>();

    public Dealer() {
        shuffle();
    }

    private void shuffle(){
        ArrayList<Card> initCards = initCard();
        int size = initCards.size();

        while (size != 0){
            int random = (int)(Math.random() * size);
            Card card = initCards.remove(random);
            cards.add(card);
            size--;
        }
    }

    private ArrayList<Card> initCard(){
        ArrayList<Card> initCards = new ArrayList<>();

        String[] suits = {"spade", "heart", "diamond ", "club"};
        for(String suit:suits){
            for(int num = 1; num <= 13; num++){
                Card card = new Card(suit, num);
                initCards.add(card);
            }
        }
        return initCards;
    }

    //發出一張牌
    public Card deal(){
        if(cards.isEmpty()){
            System.out.println("**** 沒有牌了，洗牌中請稍後 ****");
//            delay(400);
            shuffle();
        }

        int size = cards.size();
        int random = (int) (Math.random() * size);
        return cards.remove(random);

    }

}

