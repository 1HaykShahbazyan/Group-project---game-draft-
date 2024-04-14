package Fantasy_Arena.core;

// Brawler class representing a brawler character
public class Brawler extends Champion {

    public Brawler(String name, int health, int mana) {
        super(name, health, mana);
    }

    // Overriding the attack method for brawler
    @Override
    public void attack() {
        System.out.println(name + " delivers powerful strikes.");
    }

    @Override
    public int damagePhysical() {
        return 20 + (int) Math.random() * 20;
    }

    @Override
    public int damageMagical() {
        return 0;
    }
}