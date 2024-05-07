package Fantasy_Arena.core;

// Brawler class representing a brawler character
public class Brawler extends Champion {

    private double defence;

    public Brawler(String name, int health, int mana) {
        super(name, health, mana);
        defence = 1.2;
    }

    // Overriding the attack method for brawler
    @Override
    public void attack(int i, Champion target, Champion[] team) {
        switch (i){
            case 1:
                System.out.println(name + " executes a regular strike.");  
                break;
            case 2:
                if(mana < 30){
                    break;
                }
                System.out.println(name + " executes a combo attack");
                for(int j = 0; Math.random() > 0.5 && j < 5; j++){
                    System.out.print(j + "!! ");
                    target.ReceiveDamage(10, 0);
                }
                mana -= 30;
                break;
        }
    }

    @Override
    public int damagePhysical() {
        return 20;
    }

    @Override
    public int damageMagical() {
        return 0;
    }

    public String toString(){
        return "Brawler " + name + " " + health + " " + mana;
    }
}