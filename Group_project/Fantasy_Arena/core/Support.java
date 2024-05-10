package Fantasy_Arena.core;

public class Support extends Champion {

    private double defence;

    public Support(){
        super("Support", 90, 70);
        defence = 0.7;
    }
    public Support(String name, int health, int mana) {
        super(name, health, mana);
        defence = 0.7;
    }

    // Overriding the attack method for supports
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
                System.out.println(name + " heals allies");
                for(int j = 0; j < team.length; j++){
                    team[j].ReceiveDamage(-15, 0);
                }
                mana -= 30;
                break;
        }
    }

    @Override
    public int damagePhysical() {
        return 5;
    }

    @Override
    public int damageMagical() {
        return 5;
    }

    @Override
    public Champion copy() {
        return new Support(this.getName(), (int) this.getHp(), (int) this.getMp());
    }

    public String toString(){
        return "Support " + name + " " + health + " " + mana;
    }
}
