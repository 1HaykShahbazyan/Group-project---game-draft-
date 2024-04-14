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
        this.health = this.health - physicalDamage;
        this.mana = this.mana - magicalDamage;
    }
}