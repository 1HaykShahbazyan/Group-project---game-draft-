package Fantasy_Arena.core;

// Brawler class representing a brawler character
public class Brawler extends Champion {

    private double defence;

    public Brawler(){
        super("Brawler", 120, 40);
        defence = 1.2;
    }
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
                for(int j = 0; Math.random() > 0.3 && j < 5; j++){
                    System.out.print(j+1 + "!! ");
                    target.ReceiveDamage(10, 0);
                    System.out.println();
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

    @Override
    public Champion copy() {
        return new Brawler(this.getName(), (int) this.getHp(), (int) this.getMp());
    }

    public String toString(){
        return "Brawler " + name + " " + health + " " + mana;
    }
}