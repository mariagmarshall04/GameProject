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

    public Monk() {
        this.stamina = 120;
        this.mana = 0;
        this.qi = 100;
    }
    @Override
    public void attack(){
        qi -= 3;
        stamina -= 5;
        System.out.println("The Monk strikes!");
    }
    @Override
    public void specialAbility() {
        qi -= 25; //calculating the amound of ki lost from using "furiousPunch"
        stamina -= 25;
        System.out.println("The Monk uses Furious Punch!");
    }
}
class Hunter extends Character {
    public Hunter(){
        this.stamina = 150;
        this.mana = 40;
        this.qi = 0;
    }
    @Override
    public void attack(){
        stamina -= 8;
        mana -= 2;
        System.out.println("The Hunter shoots an arrow!");
    }
    @Override
    public void specialAbility() {
        mana -= 4;
        stamina -= 20;  //sprays 4 arrows simultaneously
        System.out.println("The Hunter uses Arrow Volley!");
    }
}
class Necromancer extends Character{
    public Necromancer() {
        this.stamina = 40;
        this.mana = 150;  //evilMana
    }
    @Override
    public void attack(){
        mana -= 3;
        stamina -= 5;
        System.out.println("The Necromancer raises his staff and attacks!");
    }
    @Override
    public void specialAbility() {
        mana -= 75;    //summons 3 zombies
        System.out.println("The Necromancer summons three zombified minions!");
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
        player.attack();
        player.specialAbility();
        System.out.println("Stamina: " + player.stamina + "\n" + "Mana: " + player.mana + "\n" + "Qi: " + player.qi);

    }
}
