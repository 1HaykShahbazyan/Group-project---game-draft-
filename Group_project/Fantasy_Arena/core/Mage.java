package Fantasy_Arena.core;

// Mage class representing a mage character
public class Mage extends Champion {

    private double defence;

    public Mage(){
        super("Mage", 70, 90);
        defence = 0.8;
    }
    public Mage(String name, int health, int mana) {
        super(name, health, mana);
        defence = 0.8;
    }

    // Overriding the attack method for mages
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
                System.out.println(name + " drains opponent's mana");
                target.ReceiveDamage(0,60);
                for(int j = 0; j < team.length; j++){
                    team[j].ReceiveDamage(0, -(60/team.length));
                }
                mana -= 30;
                break;
        }
    }

    @Override
    public int damagePhysical() {
        return 15;
    }

    @Override
    public int damageMagical() {
        return 20;
    }

    @Override
    public Champion copy() {
        return new Mage(this.getName(), (int) this.getHp(), (int) this.getMp());
    }

    public String toString(){
        return "Mage " + name + " " + health + " " + mana;
    }
}