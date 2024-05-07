package Fantasy_Arena.core;


// Warrior class representing a warrior character
public class Warrior extends Champion {
    private double defence;

    public Warrior(String name, int health, int mana) {
        super(name, health, mana);
        defence = 1.25;
    }

    // Overriding the attack method for warriors
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
                System.out.println(name + " buffs himself");
                for(int j = 0; j < team.length; j++){
                    defence += 0.25;
                    health += 25;
                }
                mana -= 30;
                break;
        }
    }

    @Override
    public int damagePhysical() {
        return 25;
    }

    @Override
    public int damageMagical() {
        return 0;
    }

    public String toString(){
        return "Warrior " + name + " " + health + " " + mana;
    }
}