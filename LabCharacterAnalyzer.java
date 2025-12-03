import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
class Character{
    int stamina;
    int mana;
    int qi;
    public int staminaLost(int minutes){
        return 0;
    }
    public int staminaRestored(int minutes){
        return 0;
    }
    public int finalStamina(int lostMinutes, int restMinutes){
        int lost = staminaLost(lostMinutes);
        int restored = staminaRestored(restMinutes);
        return stamina - lost + restored;
    }
}
class Monk extends Character{
    public Monk(){
        this.stamina = 120;
        this.mana = 0;
        this.qi = 100;
        
    }

    @Override
   public int staminaLost(int minutes){
        return minutes * 4;
    }
    @Override
    public int staminaRestored(int minutes){
        return minutes * 2;
    }
}

class Hunter extends Character{
    public Hunter(){
        this.stamina = 100;
    }

    @Override
    public int staminaLost(int minutes){
        return minutes * 3;
    }
    @Override
    public int staminaRestored(int minutes){
        return minutes * 3;
    }
}
class Necromancer extends Character{
    public Necromancer(){
        this.stamina = 150;
        this.mana = 150;
    }
    @Override
    public int staminaLost(int minutes){
        int lost = minutes * 2;
        if (minutes % 2 == 1){
            lost += 2;
        }
        return lost;
    }
    @Override
    public int staminaRestored(int minutes){
        return minutes * 4;
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

        System.out.println("How many minutes did you character train?");
        int minutes = input.nextInt();

        System.out.println("How many minutes did they rest?");
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
        int result = c.finalStamina(minutes, rest);
        System.out.println("Your final stamina is: " + result);
    }
}
