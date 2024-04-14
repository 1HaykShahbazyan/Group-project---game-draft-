package Fantasy_Arena.core;

// Assassin class representing an assassin character
public class Assassin extends Champion  implements Attack{
    public Assassin(String name, int health, int mana) {
        super(name, health, mana);
    }

    // Overriding the attack method for assassins
    @Override
    public void attack() {
        System.out.println(name + " executes a lethal strike.");
    }

    @Override
    public int damagePhysical() {
        return 25;
    }

    @Override
    public int damageMagical() {
        return 5;
    }
}