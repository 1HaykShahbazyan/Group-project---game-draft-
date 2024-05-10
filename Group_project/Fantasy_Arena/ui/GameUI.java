package Fantasy_Arena.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Fantasy_Arena.core.*;

public class GameUI extends JFrame implements ActionListener {
    private Game game;
    private ChampionButton[] team1championButtons;
    private ChampionButton player1Button;
    private ChampionButton[] team2championButtons;
    private ChampionButton player2Button;
    private SkillButton[] skillButtons;
    private ChampionButton attackingChampion;
    private ChampionButton targetChampion;

    public GameUI(Game game) {
        this.game = game;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Fantasy Arena");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        player1Button = new ChampionButton(game.getTargetPlayer().getChampion());
        player1Button.addActionListener(this);
        add(player1Button, createGridBagConstraints(game.getTargetTeam().length / 2, 0));

        team1championButtons = new ChampionButton[game.getTargetTeam().length];
        for (int i = 0; i < team1championButtons.length; i++) {
            Champion champion = game.getTargetTeam()[i];
            team1championButtons[i] = new ChampionButton(champion);
            team1championButtons[i].addActionListener(this);
            add(team1championButtons[i], createGridBagConstraints(i, 1));
        }

        skillButtons = new SkillButton[2];
        
            skillButtons[0] = new SkillButton("Regular Attack");
            skillButtons[0].addActionListener(this);
            add(skillButtons[0], createGridBagConstraints(0, 2));

            skillButtons[1] = new SkillButton("Special Attack");
            skillButtons[1].addActionListener(this);
            add(skillButtons[1], createGridBagConstraints(1, 2));
        

        team2championButtons = new ChampionButton[game.getCurrentTeam().length];
        for (int i = 0; i < team2championButtons.length; i++) {
            Champion champion = game.getCurrentTeam()[i];
            team2championButtons[i] = new ChampionButton(champion);
            team2championButtons[i].addActionListener(this);
            add(team2championButtons[i], createGridBagConstraints(i, 3));
        }

        player2Button = new ChampionButton(game.getCurrentPlayer().getChampion());
        player2Button.addActionListener(this);
        add(player2Button, createGridBagConstraints(game.getTargetTeam().length / 2, 4));

        attackingChampion = new ChampionButton(game.getCurrentTeam()[0]); 
        highlightAttackingChampion(); 

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private GridBagConstraints createGridBagConstraints(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(5, 5, 5, 5);
        return gbc;
    }

    private void highlightAttackingChampion() {
        for (ChampionButton button : team1championButtons) {
            if(button.getChampion() == attackingChampion.getChampion()){
                button.highlight();
            } else{
                button.unhighlight();
            }
        }
        for (ChampionButton button : team2championButtons) {
            if(button.getChampion() == attackingChampion.getChampion()){
                button.highlight();
            } else{
                button.unhighlight();
            }
        }
        if(attackingChampion.getChampion() == player1Button.getChampion()){
            player1Button.highlight();
        } else{
            player1Button.unhighlight();
        }
        if(attackingChampion.getChampion() == player2Button.getChampion()){
            player2Button.highlight();
        } else{
            player2Button.unhighlight();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source instanceof ChampionButton) {
            ChampionButton championButton = (ChampionButton) source;
            if(targetChampion != null){
                targetChampion.unhighlight();
            }
            if (game.isDifferentTeam(attackingChampion.getChampion(), championButton.getChampion())) {
                if(championButton.getChampion() == game.getTargetPlayer().getChampion() &&
                 game.getTargetTeam().length > 0){
                    return;
                }
                targetChampion = championButton;
                targetChampion.highlight(Color.RED);
            }
            return;
        }

        for (int i = 0; i < skillButtons.length; i++) {
            if (source == skillButtons[i]) {
                if(targetChampion == null){
                    return;
                }
                game.useSkill(attackingChampion.getChampion(), targetChampion.getChampion(), i + 1); 
                checkGameOver();
                updateGameStatus();
                targetChampion.unhighlight();
                if(targetChampion.getChampion().getHp() <= 0){
                    removeDeadChampionButton(targetChampion.getChampion());
                }
                targetChampion = null;
                game.removeFallenChampions(); 
                if(attackingChampion.getChampion() == game.getCurrentPlayer().getChampion()){
                    game.changeTurn();
                    updateGameStatus();
                }
                nextAttackingChampion(); 
                highlightAttackingChampion(); 
                return;
            }
        }
    }

    private void nextAttackingChampion() {
        int currentIndex = -1;
        for (int i = 0; i < game.getCurrentTeam().length; i++) {
            if (game.getCurrentTeam()[i] == attackingChampion.getChampion()) {
                currentIndex = i;
                break;
            }
        } 
        for (int i = 0; i < game.getTargetTeam().length; i++) {
            if (game.getTargetTeam()[i] == attackingChampion.getChampion() ||
             game.getTargetPlayer().getChampion() == attackingChampion.getChampion()) {
                if(game.getCurrentTeam().length > 0){
                    attackingChampion = new ChampionButton(game.getCurrentTeam()[0]);
                } else {
                    attackingChampion = new ChampionButton(game.getCurrentPlayer().getChampion());
                }
                return;
            }
        } 
        if (currentIndex != -1 && currentIndex<game.getCurrentTeam().length-1) {
            attackingChampion = new ChampionButton(game.getCurrentTeam()[(currentIndex + 1)]);
        } else{
            attackingChampion = new ChampionButton(game.getCurrentPlayer().getChampion());
        }
    }

    private void removeDeadChampionButton(Champion deadChampion) {
        for (int i = 0; i < team1championButtons.length; i++) {
            if (team1championButtons[i].getChampion() == deadChampion) {
                remove(team1championButtons[i]);
                break;
            }
        }
    
        for (int i = 0; i < team2championButtons.length; i++) {
            if (team2championButtons[i].getChampion() == deadChampion) {
                remove(team2championButtons[i]);
                break;
            }
        }
    
        revalidate();
        repaint();
    }
    
    private void checkGameOver() {
        if (player1Button.getChampion().getHp() <= 0) {
            JOptionPane.showMessageDialog(this, "Game Over! Player 1 Wins!");
            dispose();
        } else if (player2Button.getChampion().getHp() <= 0) {
            JOptionPane.showMessageDialog(this, "Game Over! Player 2 Wins!");
            dispose();
        }
    }

    private void updateGameStatus(){
        for(int i = 0; i < team1championButtons.length; i++){
            team1championButtons[i].updateStatus();
        }
        for(int i = 0; i < team2championButtons.length; i++){
            team2championButtons[i].updateStatus();
        }
        player1Button.updateStatus();
        player2Button.updateStatus();
    }
}
