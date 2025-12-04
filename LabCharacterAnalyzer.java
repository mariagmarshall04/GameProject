class Character {
    int stamina;
    int mana;
    int qi;
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
    int arrowQuiver = 20;
    public void arrowVolley() {
        arrowQuiver -= 4;   //sprays 4 arrows simultaneously
    }
    public Hunter() {
        this.stamina = 150;
    }
}

    class Necromancer extends Character{
    public void summonUndead() {
        mana -= 75; //summons 3 zombies
    }
        public Necromancer() {
            this.stamina = 150;
            this.mana = 150;  //evilMana
        }
}
    // ================= Main Program =================
    public class Main {
        public static void main(String[] args) {

            
        }
    }
