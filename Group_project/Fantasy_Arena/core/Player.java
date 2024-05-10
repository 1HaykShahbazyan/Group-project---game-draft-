package Fantasy_Arena.core;

// Player class representing a player
public class Player {
    private String name;
    private Champion champion;
    

    public Player(String name, Champion champion) {
        this.name = name;
        this.champion = champion;
    }

    public String getName(){
        return this.name;
    }

    public double getHealth(){
        return champion.health;
    }

    public Champion getChampion(){
        return this.champion;
    }
}