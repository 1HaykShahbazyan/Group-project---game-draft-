package Fantasy_Arena.core;

import Fantasy_Arena.core.exceptions.*;

public class Game {

    private Player player1;
    private Champion[] team1;
    private Player player2;
    private Champion[] team2;
    private Player currentPlayer;


    public Game(int p1, Champion[] team1, int p2, Champion[] team2) throws InvalidCharacterChoiceException, UnfairMatchupException{

        player1 = selectCharacter("Player 1", p1);
        
        player2 = selectCharacter("Player 2", p2);
        
        if(team1.length == team2.length){
            this.team1 = team1;
            this.team2 = team2;
        } else{
            throw new UnfairMatchupException();
        }
        

        currentPlayer = player1;
    }

    private Player selectCharacter(String playerName, int playerID) throws InvalidCharacterChoiceException{
      
        
        switch (playerID) {
            case 1:
                return new Player(playerName, new Warrior(playerName, 100, 50));
            case 2:
                return new Player(playerName, new Mage(playerName, 80, 100));
            case 3:
                return new Player(playerName, new Assassin(playerName, 90, 70));
            case 4:
                return new Player(playerName, new Marksman(playerName, 80, 80));
            case 5:
                return new Player(playerName, new Tank(playerName, 120, 40));
            case 6:
                return new Player(playerName, new Support(playerName, 70, 120));
            case 7:
                return new Player(playerName, new Brawler(playerName, 110, 60));
            default:
                throw new InvalidCharacterChoiceException();
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    public Player getTargetPlayer(){
        return (currentPlayer == player1) ? player2 : player1;
    }

    public Champion[] getCurrentTeam() {
        return (currentPlayer == player1) ? team1 : team2;
    }
    public Champion[] getTargetTeam(){
        return (currentPlayer == player1) ? team2 : team1;
    }

    public void changeTurn(){
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public void useSkill(Champion attacker, Champion target){
        attacker.attack();

        target.ReceiveDamage(
            attacker.damagePhysical(),
            attacker.damageMagical()
        );
    }

    public void removeFallenCampions(){
        if(currentPlayer == player1){
            for(int i = 0; i < team2.length; i++){
                if(team2[i].health <= 0){
                    team2 = removeElement(team2, i);
                }
            }
        } else{
            for(int i = 0; i < team1.length; i++){
                if(team1[i].health <= 0){
                    team1 = removeElement(team1, i);
                }
            }
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