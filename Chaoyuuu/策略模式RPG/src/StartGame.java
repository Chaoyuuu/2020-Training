import gameCharacters.*;
import java.util.ArrayList;

public class StartGame {
       public static void main(String[] args) {
        //set character
        ArrayList<GameCharacters> roles1 = new ArrayList<>();
        roles1.add(new Player());
        roles1.add(new Soldier());
        roles1.add(new Soldier());
        roles1.add(new Soldier());
        roles1.add(new Soldier());
        roles1.add(new Protector());

        //Round 1
        System.out.println("ROUND 1 !!!");
        printAttri(roles1);
        startRPG(roles1);

        //set character
        ArrayList<GameCharacters> roles2 = new ArrayList<>();
        roles2.add(new Player());
        roles2.add(new Soldier());
        roles2.add(new Soldier());
        roles2.add(new Mage());
        roles2.add(new Mage());

        //Round 2
        System.out.println("\n\nROUND 2 ! ! !");
        printAttri(roles2);
        startRPG(roles2);

        //set character
        ArrayList<GameCharacters> roles3 = new ArrayList<>();
        roles3.add(new Player());
        roles3.add(new Boss());

        //Round 3
        System.out.println("\n\nROUND 3 ! ! !");
        printAttri(roles3);
        startRPG(roles3);

    }

    public static void startRPG(ArrayList<GameCharacters> roles){

        while (true){
            int size = roles.size();
            for(int i = 0; i < size; i++){
                GameCharacters role = roles.get(i);

                if(role.getName() == "Player") {                //player
                    role.chooseSkills(roles).attack(role);
                }else if(!isRest(role) && !role.isDead()) {     //active monster
                    role.chooseSkills(roles).attack(role);
                }
            }
            if(gameOver(roles)) break;
            printAttri(roles);
        }
    }

    public static boolean isRest(GameCharacters m){
        int rest = m.getRest();
        if(rest == 0) return false;
        m.setRest(rest - 1);
        System.out.printf("(%s): freeze\n", m.getName());
        return true;
    }

    //print GameCharacter Attributes
    public static void printAttri(ArrayList<GameCharacters> role){
        System.out.println("Index Name      HP   MP  AP  DF  MDF");
        int index = 0;
        for(GameCharacters m: role){
            System.out.printf("  %-3d %-9s %-4d %-3d %-3d %-3d %-3d %s\n", index++, m.getName(), m.getHP(), m.getMP(), m.getAP(), m.getDF(), m.getMDF(), m.isDead()? "X" : " ");
        }
        System.out.println("=======================================");
    }

    //if Game is over or not
    public static boolean gameOver(ArrayList<GameCharacters> roles){
        for(GameCharacters role:roles){
            if(role.getName() == "Player" && role.isDead()){        //check player.isDead()
                System.out.println("* * * * * Player loose * * * * * ");
                return true;
            }else if(role.getName() != "Player" && !role.isDead()){ //check monster.isdead()
                return false;
            }
        }

        System.out.println("* * * * * Player win * * * * * ");
        return true;
    }
}


