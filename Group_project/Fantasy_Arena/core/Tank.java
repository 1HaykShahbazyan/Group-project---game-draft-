package Fantasy_Arena.core;


// Tank class representing a tank character
public class Tank extends Champion {
    public Tank(String name, int health, int mana) {
        super(name, health, mana);
    }

    // Overriding the attack method for tanks
    @Override
    public void attack() {
        System.out.println(name + " absorbs enemy attacks.");
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
    public void ReceiveDamage(int physicalDamage, int magicalDamage){
        this.health = this.health - physicalDamage / 2;
        this.mana = this.mana - magicalDamage;
    }
}
