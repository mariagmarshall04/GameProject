import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
class Character{
    int stamina;
    int mana;
    int qi;
    public int staminaLost(int turns){
        return 0;
    }
    public int staminaRestored(int turns){
        return 0;
    }
    public int finalStamina(int lostTurns, int restTurns){
        int lost = staminaLost(lostTurns);
        int restored = staminaRestored(restTurns);
        return stamina - lost + restored;
    }
}
class Monk extends Character{
    public Monk(){
        this.stamina = 120;
        this.mana = 0;
        this.qi = 100;
    public void furiousPunch(){   
        qi -= 25; //calculating the amound of ki lost from using "furiousPunch"
    }
    @Override
   public int staminaLost(int turns){
        return turns * 2;
    }
    @Override
    public int staminaRestored(int turns){
        return turns * 3;
    }
}

class Hunter extends Character{
    public Hunter(){
        this.stamina = 150;
        int arrowQuiver = 20;
        public void arrowVolley(){
            arrowVolley -= 4;   //sprays 4 arrows simultaneously
    }

    @Override
    public int staminaLost(int turns){
        return turns * 3;
    }
    @Override
    public int staminaRestored(int turns){
        return turns * 3;
    }
}
class Necromancer extends Character{
    public Necromancer(){
        this.stamina = 150;
        this.mana = 150;  //evilMana
        public void summonUndead(){
        mana -= 75; //summons 3 zombies
    }
    @Override
    public int staminaLost(int turns){
        int lost = turns * 2;
        if (turns % 2 == 1){
            lost += 2;
        }
        return lost;
    }
    @Override
    public int staminaRestored(int turns){
        return turns * 4;
    }
}
public class LabCharacterAnalyzer {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("----Character Menu----\n1.Warrior\n2.Archer\n3.Wizard");
        System.out.println("Please press 1-3 to choose which class you would like to be.");

        int choice = input.nextInt();
        while (choice < 1 || choice > 3) {
            System.out.println("Invalid choice.");
            choice = input.nextInt();
        }

        System.out.println("How many turns of combat was your character in?");
        int turns = input.nextInt();

        System.out.println("How many turns did they rest?");
        int rest =  input.nextInt();

        Character c = null;

        switch (choice){
            case 1:
                c = new Warrior();
                break;
            case 2:
                c = new Archer();
                break;
            case 3:
                c = new Wizard();
                break;
        }
        int result = c.finalStamina(turns, rest);
        System.out.println("Your final stamina is: " + result);
    }
}
