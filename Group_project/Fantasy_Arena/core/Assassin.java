package Fantasy_Arena.core;

// Assassin class representing an assassin character
public class Assassin extends Champion{

    private double defence;

    public Assassin(){
        super("Assassin", 80, 60);
        defence = 1;
    }
    public Assassin(String name, int health, int mana) {
        super(name, health, mana);
        defence = 1;
    }

    // Overriding the attack method for assassins
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
                System.out.println(name + " executes a lethal attack");
                if(Math.random() > 0.5){
                    System.out.println("Critical hit!");
                    target.ReceiveDamage(20, 0);
                }
                mana -= 30;
                break;
        }
    }

    @Override
    public int damagePhysical() {
        int damage = 20;
        return damage;
    }

    @Override
    public int damageMagical() {
        return 5;
    }

    @Override
    public Champion copy() {
        return new Assassin(this.getName(), (int) this.getHp(), (int) this.getMp());
    }

    public String toString(){
        return "Assassin " + name + " " + health + " " + mana;
    }
}