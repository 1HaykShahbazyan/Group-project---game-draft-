package Fantasy_Arena.core;

// Marksman class representing a marksman character
public class Marksman extends Champion {
    public Marksman(String name, int health, int mana) {
        super(name, health, mana);
    }

    // Overriding the attack method for marksmen
    @Override
    public void attack() {
        System.out.println(name + " shoots with precision.");
    }

    @Override
    public int damagePhysical() {
        return 30;
    }

    @Override
    public int damageMagical() {
        return 0;
    }
}