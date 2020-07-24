import java.util.*;

public class Simulator {

    static Player player;
    static Player opponent;
    static boolean DEBUG = false;

//    //Your stats
//    static int attackLevel=75;                          //Your attack level
//    static int strengthLevel=84;                          //Your Strength level
//    static int defenseLevel=75;                          //Your defense level
//    static int originalHealthLevel=87;                       //Your Hit points level
//    static int healthLevel = originalHealthLevel;
//    static int attackStyleATK =1;                     //If you're using accurate this should be 3, if controlled 1, if defensive: 0
//    static int attackStyleSTR =1;                     //If you're using accurate this should be 0, if controlled 1, if defensive: 0, if aggressive 3
//    static int attackStyleDEF =1;                     //If you're using accurate this should be 0, if controlled 1, if defensive: 3
//    static Weapon playerWeapon;
    //Opponents stats
    //75 84 75 87
//    static int opponentAttackLevel =75;
//    static int opponentStrengthLevel =83;      //87,92 str lash warning!!!!
//    static int opponentDefenseLevel =70;     //Opponents defense level
//    static int opponentOriginalHealthLevel =3;     //Opponents Hitpoints level
//    static int opponentHealthLevel = opponentOriginalHealthLevel;
//    static int opponentAttackStyleATK =1;
//    static int opponentAttackStyleSTR =1;              //three points for anything but controlled
//    static int opponentAttackStyleDEF =1;              //one point each for controlled**



    static Random r=new Random();
    //Match count
    static double count=100000;
    static double wins=0;

    public static void main(String args[]){
        simulate("","");
    }


    public static void simulate(String playerName, String opponentName) {

        player = PlayerFactory.getPlayer(playerName);
        opponent = PlayerFactory.getPlayer(opponentName);

        //Max hit calc,
        //Max = (5 + C + C*D/64)/10, C=A*B+8, where A is your effective attack level and B is any active prayers
        //C is the "Effective strength", D is your strength bonus. Add 1 to C if using controlled

        int str_eff= player.getStrengthLevel() +8+ player.getAttackStyleSTR();
        int maxHit=(5+str_eff+(str_eff* player.getWeapon().getWeaponStrengthBonus())/64)/10;
        if (DEBUG) {
            System.out.println(maxHit);
        }

        //Max hit opponent
        int str_eff_op= opponent.getStrengthLevel() +8+ opponent.getAttackStyleSTR();
        int opponentMaxHit=(5+str_eff_op+(str_eff_op* opponent.getWeapon().getWeaponStrengthBonus())/64)/10;
        if (DEBUG) {
            System.out.println(opponentMaxHit);
        }

//Combat system;

        //Attack roll
        //Max = a * 64 + a * b where a is 8 + your attack level (+3 if using accurate style, or +1 if using controlled)
        //and b is your equipments total attack bonus in the style you are using

        //Defense roll
        //Max = a * 64 + a * b where a is 8 + your defense level (+3 if using defensive style, or +1 if using controlled)
        //and b is your equipments total defense bonus in the style you are using


        //Works in turns, if your attack roll is higher than the defense roll of your opponent you will hit a percentage of your
        //Max hit, if it's lower you will hit a 0.
        //the same works for your opponent

        for(int i=0; i<count; i++)
        {
            Boolean turn=r.nextBoolean();
            //Randomizes who gets ping
            while(player.getHealthLevel() >0&& opponent.getHealthLevel() >0)
            {
                if(turn)
                {
                    //Attack roll opponent
                    double atk_roll_op=(Math.random())*(64*(8+ opponent.getAttackLevel() + opponent.getAttackStyleATK())+
                            (8+ opponent.getAttackLevel() + opponent.getAttackStyleATK())*(opponent.getWeapon().getWeaponAttackBonus()));

                    double def_roll=(Math.random())*(64*(8+ player.getDefenseLevel() + player.getAttackStyleDEF())+
                            (8+ player.getDefenseLevel() + player.getAttackStyleDEF())*
                                    (player.getWeapon().getWeaponDefenceBonus()));

                    if(atk_roll_op>def_roll)
                    {
                        //Opponent hits
                        int damage =(int)(Math.random()*(opponentMaxHit+1));
                        player.hit(damage);
                        //Checking
                        if (DEBUG) {
                            System.out.println("OP HIT " + damage + " ! HP: " + player.getHealthLevel());
                        }
                        turn=false;
                    }
                    else
                    {
                        //Opponent misses
                        if (DEBUG) {
                            System.out.println("OP MISS !  HP: " + player.getHealthLevel());
                        }
                        turn=false;
                    }
                }
                else
                {
                    //You strike, attack roll
                    double attackRoll=(Math.random())*(64*(8+ player.getAttackLevel() + player.getAttackStyleATK())+
                            (8+ player.getAttackLevel() + player.getAttackStyleATK())*(player.getWeapon().getWeaponAttackBonus()));

                    double defenseRoll=(Math.random())*(64*(8+ opponent.getDefenseLevel() + opponent.getAttackStyleDEF())+
                            (8+ opponent.getDefenseLevel() + opponent.getAttackStyleDEF())*(opponent.getWeapon().getWeaponDefenceBonus()));

                    if(attackRoll>defenseRoll)
                    {
                        //You hit
                        int damage=(int)(Math.random()*(maxHit+1));
                        opponent.hit(damage);
                        //Checking
                        if (DEBUG) {
                            System.out.println("YOU HIT " + damage + " !  OP HP: " + opponent.getHealthLevel());
                        }
                        turn=true;
                    }
                    else
                    {
                        //You miss
                        if (DEBUG) {
                            System.out.println("YOU MISS !  OPHP: " + opponent.getHealthLevel());
                        }
                        turn=true;
                    }
                }
            }
            if(!turn)
            {
                if (DEBUG) {
                    System.out.println("You LOSE");
                }
                player.resetHealth();
                opponent.resetHealth();

            }
            else
            {
                if (DEBUG) {
                    System.out.println("You WIN");
                }
                wins++;
                player.resetHealth();
                opponent.resetHealth();

            }


        }

        double chance=(wins/count);
        System.out.println("You have "+chance*100+" % chance of winning");
        System.out.println("Your max hit: "+maxHit+" OP's max hit: "+opponentMaxHit);
        System.out.println("You had "+count+" matches and won "+wins+" of them");
    }
}