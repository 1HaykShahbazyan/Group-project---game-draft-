package Fantasy_Arena.core;

import java.util.Arrays;

import Fantasy_Arena.core.exceptions.*;

public class Game {

    private Player player1;
    private Champion[] team1;
    private Player player2;
    private Champion[] team2;
    private Player currentPlayer;
    private Champion[] currentTeam;


    public Game(Player player1, Champion[] team1, Player player2, Champion[] team2) throws InvalidCharacterChoiceException, UnfairMatchupException{

        this.player1 = player1;
        
        this.player2 = player2;
        
        if(team1.length == team2.length){
            this.team1 = team1;
            this.team2 = team2;
        } else{
            throw new UnfairMatchupException();
        }
        

        currentPlayer = player1;
        currentTeam = team1;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    public Player getTargetPlayer(){
        return (currentPlayer == player1) ? player2 : player1;
    }

    public Champion[] getCurrentTeam() {
        return currentTeam;
    }
    public Champion[] getTargetTeam(){
        return (currentTeam == team1) ? team2 : team1;
    }

    public void changeTurn(){
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
        currentTeam = (currentTeam == team1) ? team2 : team1;

        for(int i = 0; i < team1.length; i++){
            team1[i].mana += 10;
        }
        for(int i = 0; i < team2.length; i++){
            team2[i].mana += 10;
        }
        player1.getChampion().mana += 10;
        player2.getChampion().mana += 10;
    }

    public void useSkill(Champion attacker, Champion target, int i){
        attacker.attack(i, target, currentTeam);

        target.ReceiveDamage(
            attacker.damagePhysical(),
            attacker.damageMagical()
        );
    }

    public boolean isDifferentTeam(Champion champion1, Champion champion2) {
        if (Arrays.asList(team1).contains(champion1) || player1.getChampion() == champion1) {
            return !(Arrays.asList(team1).contains(champion2) || player1.getChampion() == champion2);
        } else {
            return !(Arrays.asList(team2).contains(champion2) || player2.getChampion() == champion2);
        }
    }

    public void removeFallenChampions(){
        if(currentPlayer == player1){
            for(int i = 0; i < team2.length; i++){
                if(team2[i].health <= 0){
                    team2 = Champion.removeElement(team2, i);
                }
            }
        } else{
            for(int i = 0; i < team1.length; i++){
                if(team1[i].health <= 0){
                    team1 = Champion.removeElement(team1, i);
                }
            }
        }
    }

}