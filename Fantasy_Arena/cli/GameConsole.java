package Fantasy_Arena.cli;

import Fantasy_Arena.core.*;
import java.util.Scanner;
import Fantasy_Arena.core.exceptions.*;

public class GameConsole {
    private Game game;

    public void play() {
        Scanner sc = new Scanner(System.in);
        int p1,p2;

        System.out.println("choose your character:");
        System.out.println("1. Warrior");
        System.out.println("2. Mage");
        System.out.println("3. Assassin");
        System.out.println("4. Marksman");
        System.out.println("5. Tank");
        System.out.println("6. Support");
        System.out.println("7. Brawler");
        System.out.print("Player 1: ");
        System.out.print("Enter your choice: ");
        p1 = sc.nextInt();
        System.out.print("Player 2: ");
        System.out.print("Enter your choice: ");
        p2 = sc.nextInt();

        Champion[] team1 = {
            new Warrior("warrior", 100, 50),
            new Support("support", 70, 120)
        };
        Champion[] team2 = {
            new Warrior("warrior", 100, 50),
            new Support("support", 70, 120)
        };

        try {
            this.game = new Game(p1,team1,p2,team2);
        } catch (InvalidCharacterChoiceException | UnfairMatchupException e) {
            System.err.println("Error: " + e.getMessage());
             return;
        }
        
        
        System.out.println("Let the battle begin!");
        while (true) {

            Player currentPlayer = game.getCurrentPlayer();
            Champion[] currentTeam = game.getCurrentTeam();
            Player targetPlayer = game.getTargetPlayer();
            Champion[] targetTeam = game.getTargetTeam();

            if (currentPlayer.getHealth() <= 0) {
                System.out.println(currentPlayer.getName() + " - lost!");
                break;
            } 

            System.out.println("\n" + currentPlayer.getName() + "'s turn:");

            System.out.println("Choose your target.");

            if(game.getTargetTeam().length != 0){
            for(int i = 0; i <= currentTeam.length; i++){
                for(int j = 0; j < targetTeam.length; j++){
                    System.out.println(j+1 + ". " + targetTeam[j].getName() + "(HP: " + (int) targetTeam[j].getHp() + ", MP: " + (int) targetTeam[j].getMp() + ")");
                }
                
                int targetNumber = sc.nextInt()-1;
                Champion target;
                if(targetNumber<targetTeam.length){
                    target = targetTeam[targetNumber];
                } else {
                    System.out.println("Invalid target number, attacking one by default.");
                    target = targetTeam[0];
                }
                

                System.out.println("Choose your skill."); // skill choosing logic...

                if(i == currentTeam.length){
                    game.useSkill(currentPlayer.getChampion(), target);
                } else{
                    game.useSkill(currentTeam[i], target);
                }
                game.removeFallenCampions();
            }
            } else{
                for(int i = 0; i <= currentTeam.length; i++){
                    System.out.println(1 + ". " + targetPlayer.getName() + "(HP: " + (int) targetPlayer.getChampion().getHp() + ", MP: " + (int) targetPlayer.getChampion().getMp() + ")");

                    int targetNumber = sc.nextInt()-1;
                    Champion target;
                    if(targetNumber == 0){
                        target = targetPlayer.getChampion();
                    }else{
                        System.out.println("Invalid target number, attacking one by default.");
                        target = targetPlayer.getChampion();
                    }
                    

                    System.out.println("Choose your skill."); // skill choosing logic...

                    if(i == currentTeam.length){
                        game.useSkill(currentPlayer.getChampion(), target);
                    } else{
                        game.useSkill(currentTeam[i], target);
                    }
                    game.removeFallenCampions();
                }

            }
            
            game.changeTurn();
        }

    }
    }
