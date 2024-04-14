package Fantasy_Arena.core;


// Warrior class representing a warrior character
public class Warrior extends Champion {
    public Warrior(String name, int health, int mana) {
        super(name, health, mana);
    }

    // Overriding the attack method for warriors
    @Override
    public void attack() {
        System.out.println(name + " swings a sword.");
    }
}