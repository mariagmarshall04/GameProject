import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

class Character {
    int stamina;
    int mana;
    int qi;

    public void character(){

    }
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
    public void character() {
        System.out.println("You've made a Monk!");
    }
    @Override
    public void attack(){
        qi -= 3;
        stamina -= 5;
        System.out.println("The Monk strikes with the palm of his hand!");
    }
    @Override
    public void specialAbility() {
        qi -= 25; //calculating the amound of ki lost from using "furiousPunch"
        stamina -= 25;
        System.out.println("The Monk pummels their target with Furious Punch!");
    }
}
class Hunter extends Character {
    public Hunter(){
        this.stamina = 150;
        this.mana = 40;
        this.qi = 0;
    }
    @Override
    public void character() {
        System.out.println("You've made a Hunter!");
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
        System.out.println("The Hunter pulls back their bow and shoots a volley of arrows!");
    }
}
class Necromancer extends Character{
    public Necromancer() {
        this.stamina = 40;
        this.mana = 150;  //evilMana
    }
    @Override
    public void character() {
        System.out.println("You've made a Necromancer!");
    }
    @Override
    public void attack(){
        mana -= 3;
        stamina -= 5;
        System.out.println("The Necromancer raises their staff and shoots a ball of magic!");
    }
    @Override
    public void specialAbility() {
        mana -= 75;    //summons 3 zombies
        System.out.println("The Necromancer uses Summon and three zombified ghouls emerge from the ground...");
    }
}




class BalduringGateMain extends JFrame {

    private Character player;

    BalduringGateMain() {
        setTitle("Balduring Gate");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null); //center screen


        JPanel mainPanel = new JPanel();
        JButton selectClassesBtn = new JButton("Select Class");
        selectClassesBtn.setFont(new Font("Arial", Font.BOLD, 20));
        selectClassesBtn.addActionListener(_ -> openClassSelectionWindow());

        mainPanel.add(selectClassesBtn);
        add(mainPanel);

        setVisible(true);
    }

    void openClassSelectionWindow() {
        JFrame classFrame = new JFrame("Choose Your Class");
        classFrame.setSize(400, 600);
        classFrame.setLocationRelativeTo(this); //centered over main window
        classFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));  //3 rows
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JButton monkBtn = new JButton("Monk");
        JButton huntBtn = new JButton("Hunter");
        JButton necroBtn = new JButton("Necromancer");

        panel.add(monkBtn);
        panel.add(huntBtn);
        panel.add(necroBtn);

        monkBtn.addActionListener(e-> {
            player = new Monk();
            classFrame.dispose();
            openNextStepWindow("Monk");
        });

        huntBtn.addActionListener(e-> {
            player = new Hunter();
            classFrame.dispose();
            openNextStepWindow("Hunter");
        });

        necroBtn.addActionListener(e-> {
            player = new Necromancer();
            classFrame.dispose();
            openNextStepWindow("Necromancer");
        });

        classFrame.add(panel);
        classFrame.setVisible(true); //New window appears
    }

    void openNextStepWindow(String selectedClass){
        JFrame abilityFrame = new JFrame("Character Creation");
        abilityFrame.setSize(400,600);
        abilityFrame.setLocationRelativeTo(null);
        abilityFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 10, 10));

        String className = player.getClass().getSimpleName();

        JLabel title = new JLabel("You are a "+ selectedClass , SwingConstants.CENTER);
        JLabel stats = new JLabel("Stamina: " + player.stamina);
        panel.add(title);
        panel.add(stats);
        abilityFrame.add(panel);
        abilityFrame.add(title);
        abilityFrame.add(stats);
        abilityFrame.setVisible(true);
    }
}


public class Main {
    public static void main(String[] args) throws IOException{
        SwingUtilities.invokeLater(BalduringGateMain::new);
        Scanner input = new  Scanner(System.in);

        System.out.println("What would you like your class to be?(1-3)");
        System.out.println("========Classes=========\n1. Monk\n2. Hunter\n3. Necromancer");

        int choice = input.nextInt();
        input.nextLine();
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
        player.character();
        System.out.println("Welcome to your first fight! Here you will practice you attack or your special ability.\nAnd at the end you will be given the opportunity to rest for a turn or long rest");
        System.out.println("~~~A shadowy figure appears before you~~~");
        System.out.println("What type of attack would you like to do first? Attack or Special Ability?");

        String type = input.nextLine().trim();

        while(!type.equalsIgnoreCase("Done")) {
            if (type.equalsIgnoreCase("attack")) {
                player.attack();
            } else if(type.equalsIgnoreCase("special ability")) {
                player.specialAbility();
            } else{
                System.out.println("Invalid choice.");
            }
            System.out.println("Would you like to use another attack? If not type done.");
            type = input.nextLine().trim();
        }
        System.out.println("Stamina: " + player.stamina + "\n" + "Mana: " + player.mana + "\n" + "Qi: " + player.qi);
        FileWriter file = new FileWriter("CharacterStats.txt");
        file.write(player.stamina + "\n" + player.mana + "\n" + player.qi);
        file.close();
    }
}
