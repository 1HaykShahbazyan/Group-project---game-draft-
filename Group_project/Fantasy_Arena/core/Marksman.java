package Fantasy_Arena.core;

// Marksman class representing a marksman character
public class Marksman extends Champion {

    private double defence;

    public Marksman(){
        super("Marksman", 70, 60);
        defence = 0.75;
    }
    public Marksman(String name, int health, int mana) {
        super(name, health, mana);
        defence = 0.75;
    }

    // Overriding the attack method for marksmen
    @Override
    public void attack(int i, Champion target, Champion[] team) {
        switch (i){
            case 1:
                System.out.println(name + " shoots with precision.");  
                break;
            case 2:
                if(mana < 30){
                    break;
                }
                System.out.println(name + " shatters opponent's armor");
                target.setDefence(target.getDefence()/2);
                mana -= 30;
                break;
        }
    }

    @Override
    public int damagePhysical() {
        return 30;
    }

    @Override
    public int damageMagical() {
        return 0;
    }

    @Override
    public Champion copy() {
        return new Marksman(this.getName(), (int) this.getHp(), (int) this.getMp());
    }

    public String toString(){
        return "Marksman " + name + " " + health + " " + mana;
    }
}