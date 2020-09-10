package player;
import bank.*;
import java.util.Scanner;

public class HumanPlayer extends Player {
    public HumanPlayer(Attribute attribute) {
        super(attribute);
    }

    @Override
    public void getCard(Dealer dealer) {
        Card card;
        boolean hit = true;
        Scanner scanner = new Scanner(System.in);

        while (hit){
            card = dealer.deal();
            setPlayerScore(card.getNumber());

            if(overTwentyOne()){  //over, get zero point
                System.out.printf( "翻開了 .... %s %d\n", card.getSuit(), card.getNumber());
                return;
            } else {
                System.out.printf( "翻開了 .... %s %d\n請問 %s 要翻開下一張牌嗎(y/n)? ", card.getSuit(), card.getNumber(), this.getPlayerName());
                hit = scanner.next().equalsIgnoreCase("y");
            }
        }
        return;
    }
}
