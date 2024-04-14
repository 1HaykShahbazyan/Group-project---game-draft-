package Fantasy_Arena.core;

// change the name
public class Champion implements Attack{
    protected double health;
    protected double mana;
    protected String name;

    public Champion(String name, int health, int mana) {
        this.name = name;
        this.health = health;
        this.mana = mana;
    }

    public String getName(){
        return this.name;
    }
    public double getHp(){
        return this.health;
    }
    public double getMp(){
        return this.mana;
    }

    public void attack() {
        System.out.println(name + " performs a basic attack.");
    }

    @Override
    public int damagePhysical() {
        return 10;
    }

    @Override
    public int damageMagical() {
        return 0;
    }

    public void ReceiveDamage(int physicalDamage, int magicalDamage){
        this.health -= physicalDamage;
        this.mana -= magicalDamage;
    }
}