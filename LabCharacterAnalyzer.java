import java.util.Scanner;
class Character {
    int stamina;
    int mana;
    int qi;

    public void specialAbility(){
    }
    public void attack(){
    }
}
//public int staminaLost(int turns){
//   return 0;
//}
// public int staminaRestored(int turns){
//  return 0;
//}
//   public int finalStamina(int lostTurns, int restTurns){
//int lost = staminaLost(lostTurns);
// int restored = staminaRestored(restTurns);
//   return stamina - lost + restored;
//}
//}
class Monk extends Character {

    public void furiousPunch() {
        qi -= 25; //calculating the amound of ki lost from using "furiousPunch"
    }
    public Monk() {
        this.stamina = 120;
        this.mana = 0;
        this.qi = 100;
    }
}
class Hunter extends Character {
    public Hunter(){
        this.stamina = 150;
        this.mana = 40;
        this.qi = 0;
    }
    @Override
    public void specialAbility() {
        mana -= 4;
        stamina -= 20;  //sprays 4 arrows simultaneously
    }
}
class Necromancer extends Character{
    public Necromancer() {
        this.stamina = 40;
        this.mana = 150;  //evilMana
    }
    @Override
    public void specialAbility() {
        mana -= 75;    //summons 3 zombies
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner input = new  Scanner(System.in);

        System.out.println("What would you like your class to be?(1-3)");
        System.out.println("========Classes=========\n1. Monk\n2. Hunter\n3. Necromancer");

        int choice = input.nextInt();
        while (choice < 1 || choice > 3) {
            System.out.println("Invalid choice.");
            choice = input.nextInt();
        }
        Character player = null;
        switch (choice){
            case 1:
                player = new Monk();
                break;
            case 2:
                player = new Hunter();
                break;
            case 3:
                player = new Necromancer();
                break;
                default:
                    System.out.println("Invalid choice.");
        }
        player.specialAbility();

    }
}
