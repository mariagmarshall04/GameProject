import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

class Character {
    int stamina;
    int mana;
    int qi;
    //.append is the logArea version of .println  also goes to bottom of log area
    public void character() {
        System.out.println("A character has been created!");
    }
    public void attack() {
        System.out.println("A character has attacked!");
    }
    public void specialAbility() {
        System.out.println("A character has used a special ability!");
    }
    //all overridden
}

class Monk extends Character {
    public Monk() {
        stamina = 120;
        mana = 0;
        qi = 100;
    }
    @Override public void character() { System.out.println("You've made a Monk!"); }
    @Override public void attack() {
        qi -= 3; stamina -= 5;
        BalduringGateMain.staticLogArea.append("The Monk strikes with the palm of his hand!\n");  // \n creates a new line

    }
    @Override public void specialAbility() {
        qi -= 25; stamina -= 25;
        BalduringGateMain.staticLogArea.append("The Monk pummels their target with Furious Punch!\n");
    }
}

class Hunter extends Character {
    public Hunter() {
        stamina = 150;
        mana = 40;
        qi = 0;
    }
    @Override public void character() { System.out.println("You've made a Hunter!"); }
    @Override public void attack() {
        stamina -= 8; mana -= 2;
        BalduringGateMain.staticLogArea.append("The Hunter shoots an arrow!\n");
    }
    @Override public void specialAbility() {
        mana -= 4; stamina -= 20;
        BalduringGateMain.staticLogArea.append("The Hunter pulls back their bow and shoots a volley of arrows!\n");
    }
}

class Necromancer extends Character {
    public Necromancer() {
        stamina = 40;
        mana = 150;
        qi = 0;
    }
    @Override public void character() { System.out.println("You've made a Necromancer!"); }
    @Override public void attack() {
        mana -= 3; stamina -= 5;
        BalduringGateMain.staticLogArea.append("The Necromancer raises their staff and shoots a ball of magic!\n");
    }
    @Override public void specialAbility() {
        mana -= 75;
        BalduringGateMain.staticLogArea.append("The Necromancer uses Summon and three zombified ghouls ATTACK from the ground!!!\n");
    }
}

public class BalduringGateMain extends JFrame {

    private Character player;
    private JLabel staminaLabel, manaLabel, qiLabel;   // updated live
    private JTextArea logArea;                         // shows attack messages
    public static JTextArea staticLogArea;             //uses GUI instead of console to show attacks
    BalduringGateMain() {
        setTitle("Balduring Gate");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);  //center screen

        JPanel mainPanel = new JPanel(new GridBagLayout());   //ensures that button is centered
        JButton selectClassesBtn = new JButton("SELECT CLASS");
        selectClassesBtn.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 20));


        selectClassesBtn.addActionListener(e ->
                openClassSelectionWindow());

        mainPanel.add(selectClassesBtn);    //expression in JPanel () auto centers button
        add(mainPanel);
        setVisible(true);
    }

    void openClassSelectionWindow() {
        JFrame classFrame = new JFrame("Choose Your Class");
        classFrame.setSize(400, 600);
        classFrame.setLocationRelativeTo(this);
        classFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  //emptyborder creates a buffer zone between objects

        JButton monkBtn = new JButton("Monk");
        monkBtn.setFont(new Font("Impact", Font.BOLD, 20));  //formatting for button
        JButton huntBtn = new JButton("Hunter");
        huntBtn.setFont(new Font("Forte", Font.BOLD, 20));
        JButton necroBtn = new JButton("Necromancer");
        necroBtn.setFont(new Font("Jokerman", Font.BOLD, 20));

        monkBtn.addActionListener(e -> {
            player = new Monk();
            classFrame.dispose();
            fightWindow("Monk");});

        huntBtn.addActionListener(e -> {
            player = new Hunter();
            classFrame.dispose();
            fightWindow("Hunter");});

        necroBtn.addActionListener(e-> {
            player = new Necromancer();
            classFrame.dispose();
            fightWindow("Necromancer");});


        panel.add(monkBtn);
        panel.add(huntBtn);
        panel.add(necroBtn);

        classFrame.add(panel);
        classFrame.setVisible(true);
    }

    // replaces openNextStepWindow
    void fightWindow(String selectedClass) {
        JFrame fightFrame = new JFrame("Training Fight - " + selectedClass);
        fightFrame.setSize(600, 700);
        fightFrame.setLocationRelativeTo(null);
        fightFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Title
        JLabel title = new JLabel("You are a " + selectedClass + " – A shadowy figure appears!", SwingConstants.CENTER);
        title.setFont(new Font("Times New Roman", Font.BOLD, 18));


        // Stats
        JPanel statsPanel = new JPanel(new GridLayout(3, 1, 10, 20));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  //emptyborder creates a buffer zone between objects

        staminaLabel = new JLabel("Stamina: "+ player.stamina);   //153-155 auto updates stats in GUI due to these
        manaLabel = new JLabel("Mana: "+ player.mana);
        qiLabel = new JLabel("Qi: "+ player.qi);

        statsPanel.add(staminaLabel);
        statsPanel.add(manaLabel);
        statsPanel.add(qiLabel);
       mainPanel.add(statsPanel, BorderLayout.WEST);  //these components show up the LEFT  side of panel

        // Combat log
        logArea = new JTextArea(20, 40);
        staticLogArea = logArea;  //gives all classes access to area
        logArea.setEditable(false);
        logArea.setFont(new Font("Times New Roman", Font.BOLD, 14));
        mainPanel.add(new JScrollPane(logArea), BorderLayout.CENTER);  //Log area is where messages will be displayed. This puts it in the center and allows for scrolling vert/horz

        // Action buttons
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));  //emptyborder creates a buffer zone between objects

        JButton attackBtn = new JButton("Attack");
        JButton specialBtn = new JButton("Special Ability");
        JButton saveExitBtn = new JButton("Save & Exit");

        attackBtn.addActionListener(e -> {
            player.attack();
            updateStatsAndLog();
        });
        specialBtn.addActionListener(e -> {
            player.specialAbility();
            updateStatsAndLog();
        });
        saveExitBtn.addActionListener(e -> {
            saveToFile();
            fightFrame.dispose();  //closes fight screen
        });

        buttonPanel.add(attackBtn);
        buttonPanel.add(specialBtn);
        buttonPanel.add(saveExitBtn);

       mainPanel.add(buttonPanel, BorderLayout.EAST);   //these components show up on the RIGHT side of panel

        fightFrame.add(mainPanel);
        fightFrame.setVisible(true);

        log("You are a " + selectedClass + "!");
        log("A shadowy figure appears before you!\n");
    }

    private void updateStatsAndLog() {
        staminaLabel.setText("Stamina: "+ player.stamina);
        manaLabel.setText("Mana: "+ player.mana);
        qiLabel.setText("Qi: "+ player.qi);
    }

    private void log(String s) {            //helper code that adds \n to >log whenver called. Adds lines to bottom (append)of log area
        logArea.append(s + "\n");
    }

    private void saveToFile() {
        try (FileWriter fw = new FileWriter("CharacterStats.txt")) {
            fw.write("Stamina: "+player.stamina + "\n");
            fw.write("Mana: "+player.mana + "\n");
            fw.write("Qi: "+player.qi + "\n");
        } catch (IOException ignored){
            //end user won't receive a message of failure. Whole thing just closes.
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BalduringGateMain::new);        //this code is what starts the GUI
    }
}
