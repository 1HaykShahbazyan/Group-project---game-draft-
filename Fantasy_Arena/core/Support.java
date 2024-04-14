package Fantasy_Arena.core;

public class Support extends Champion {
    public Support(String name, int health, int mana) {
        super(name, health, mana);
    }

    // Overriding the attack method for supports
    @Override
    public void attack() {
        System.out.println(name + " heals allies.");
        this.health += 5;
    }

    @Override
    public int damagePhysical() {
        return 5;
    }

    @Override
    public int damageMagical() {
        return 5;
    }
}
