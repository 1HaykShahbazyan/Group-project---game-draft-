package Fantasy_Arena.core;


// Tank class representing a tank character
public class Tank extends Champion {

    private double defence;

    public Tank(){
        super("Tank", 180, 20);
        defence = 1.5;
    }
    public Tank(String name, int health, int mana) {
        super(name, health, mana);
        defence = 1.5;
    }

    // Overriding the attack method for tanks
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
                System.out.println(name + " protects allies");
                for(int j = 0; j < team.length; j++){
                    team[j].setDefence(team[j].getDefence() + 0.2);
                }
                mana -= 30;
                break;
        }
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
    public Champion copy() {
        return new Tank(this.getName(), (int) this.getHp(), (int) this.getMp());
    }

    public String toString(){
        return "Tank " + name + " " + health + " " + mana;
    }
}
