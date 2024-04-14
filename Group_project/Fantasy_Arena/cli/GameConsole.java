package Fantasy_Arena.cli;

import Fantasy_Arena.core.Champion;
import Fantasy_Arena.core.Game;
import Fantasy_Arena.core.Player;
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

        try {
            this.game = new Game(p1,p2);
        } catch (InvalidCharacterChoiceException e) {
            System.err.println("Error: " + e.getMessage());
             return;
        }
        
        
        System.out.println("Let the battle begin!");
        while (true) {

            Player currentPlayer = game.getCurrentPlayer();

            if (currentPlayer.getHealth() <= 0) {
                System.out.println(currentPlayer.getName() + "lost!");
                break;
            } 

            System.out.println("\n" + currentPlayer.getName() + "'s turn:");

            System.out.println("Choose your target.");
             Player target = game.getTargetPlayer();

            game.useSkill(currentPlayer.getChampion(), target.getChampion());

            
            game.changeTurn();
        }

    }
    }
