package Fantasy_Arena.core;

import Fantasy_Arena.core.exceptions.InvalidCharacterChoiceException;

public class Game {

    private Player player1;
    private Player player2;
    private Player currentPlayer;


    public Game(int p1, int p2) throws InvalidCharacterChoiceException{

        player1 = selectCharacter("Player 1", p1);
        player2 = selectCharacter("Player 2", p2);

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

}