package player;
import bank.*;
import static utils.Delay.delay;

public class AIPlayer extends Player {
    public AIPlayer(Attribute attribute) {
        super(attribute);
    }

    @Override
    public void makeDecision(Dealer dealer) {
        Card card;

        //get one card for the first round
        card = dealer.deal();
        setPlayerScore(card.getNumber());
        System.out.printf( "翻開了 .... %s %d\n請問 %s 要翻開下一張牌嗎(y/n)? ", card.getSuit(), card.getNumber(), this.getPlayerName());

        while (isHit()){
            System.out.println("y");
            card = dealer.deal();
            setPlayerScore(card.getNumber());
            if(isOverTwentyOne()){  //over, get zero point
                System.out.printf( "翻開了 .... %s %d\n", card.getSuit(), card.getNumber());
                return;
            } else {
                System.out.printf( "翻開了 .... %s %d\n請問 %s 要翻開下一張牌嗎(y/n)? ", card.getSuit(), card.getNumber(), this.getPlayerName());
            }
        }
        System.out.println("n");
        return;
    }

    private boolean isHit(){
        int random = (int)(Math.random() * 100);
        int mod = random % 2;
        delay(200);
        return (mod == 1);
    }
}
