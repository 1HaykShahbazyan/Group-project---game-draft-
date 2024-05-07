package Fantasy_Arena.core;

// change the name
public abstract class Champion implements Attack{
    protected double health;
    protected double mana;
    protected String name;
    protected double defence;

    public Champion(String name, int health, int mana) {
        this.name = name;
        this.health = health;
        this.mana = mana;
        defence = 1;
    }

    public String getName(){
        return this.name;
    }
    public double getHp(){
        return this.health;
    }
    public double getMp(){
        return this.mana;
    }
    public double getDefence(){
        return this.defence;
    }
    public void setDefence(double newDefence){
        defence = newDefence;
    }

    public abstract void attack(int i, Champion target, Champion[] team);


    @Override
    public int damagePhysical() {
        return 10;
    }

    @Override
    public int damageMagical() {
        return 0;
    }

    public void ReceiveDamage(int physicalDamage, int magicalDamage){
        this.health -= physicalDamage/defence;
        this.mana -= (magicalDamage < mana) ? magicalDamage : mana;
    }

    public abstract String toString();

    public static Champion generateFromString(String str){
        String[]data = str.split(" ");
        String name = data[1];
        int health = Integer.parseInt(data[2]);
        int mana = Integer.parseInt(data[2]);
                
        switch (data[0].toUpperCase()) {
            case "ASSASSIN":
                return new Assassin(name, health, mana);
            case "BRAWLER":
                return new Brawler(name, health, mana);
            case "MAGE":
                return new Mage(name, health, mana);
            case "MARKSMAN":
                return new Marksman(name, health, mana);
            case "SUPPORT":
                return new Support(name, health, mana);
            case "TANK":
                return new Tank(name, health, mana);
            case "WARRIOR":
                return new Warrior(name, health, mana);
            default:
            return null;
        }         
    }

    public static Champion[] removeElement(Champion[] array, int indexToRemove) {
        if (indexToRemove < 0 || indexToRemove >= array.length) {
            return array;
        }
        
        Champion[] newArray = new Champion[array.length - 1];
    
        int newIndex = 0;
        for (int i = 0; i < array.length; i++) {
            if (i != indexToRemove) {
                newArray[newIndex++] = array[i];
            }
        }
    
        return newArray;
    }
}