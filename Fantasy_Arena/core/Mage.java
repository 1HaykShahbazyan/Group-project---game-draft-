package Fantasy_Arena.core;

// Mage class representing a mage character
public class Mage extends Champion {
    public Mage(String name, int health, int mana) {
        super(name, health, mana);
    }

    // Overriding the attack method for mages
    @Override
    public void attack() {
        System.out.println(name + " casts a spell.");
    }

    @Override
    public int damagePhysical() {
        return 15;
    }

    @Override
    public int damageMagical() {
        return 20;
    }
}